package cn.seecoder.courselearning.serviceimpl.coupon;

import cn.seecoder.courselearning.dto.coupon.CouponDTO;
import cn.seecoder.courselearning.dto.coupon.DeliverCouponDTO;
import cn.seecoder.courselearning.mapperservice.coupon.CouponMapper;
import cn.seecoder.courselearning.mapperservice.coupon.UserCouponMapper;
import cn.seecoder.courselearning.po.coupon.Coupon;
import cn.seecoder.courselearning.po.coupon.UserCoupon;
import cn.seecoder.courselearning.po.course.Course;
import cn.seecoder.courselearning.po.order.CourseOrder;
import cn.seecoder.courselearning.po.user.User;
import cn.seecoder.courselearning.service.coupon.coupondeliverstrategy.CouponDeliverStrategy;
import cn.seecoder.courselearning.service.coupon.CouponService;
import cn.seecoder.courselearning.service.course.CourseService;
import cn.seecoder.courselearning.service.order.QueryOrderService;
import cn.seecoder.courselearning.service.user.UserService;
import cn.seecoder.courselearning.util.Constant;
import cn.seecoder.courselearning.util.CouponValidator;
import cn.seecoder.courselearning.vo.coupon.CouponVO;
import cn.seecoder.courselearning.vo.order.CourseOrderVO;
import cn.seecoder.courselearning.vo.ResultVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CouponServiceImpl implements CouponService {
    @Resource
    private CouponMapper couponMapper;

    @Resource
    private UserCouponMapper userCouponMapper;

    private UserService userService;

    private CourseService courseService;

    private QueryOrderService orderService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setCourseService(CourseService courseService) {
        this.courseService = courseService;
    }

    @Autowired
    public void setOrderService(QueryOrderService orderService) {
        this.orderService = orderService;
    }

    /**
     * 直接创建优惠券并发放
     * @param couponDTO
     * @return
     */
    public ResultVO<CouponVO> createCoupon(CouponDTO couponDTO) {
        System.out.println(couponDTO);
        Coupon coupon = new Coupon();
        BeanUtils.copyProperties(couponDTO, coupon);
        coupon.setValid(true);
        if (CouponValidator.isInvalid(coupon)) {
            return new ResultVO<>(Constant.REQUEST_FAIL, "优惠信息填写有误！");
        }
        if (couponMapper.insert(coupon) <= 0) {
            return new ResultVO<>(Constant.REQUEST_FAIL, "优惠活动创建失败");
        }
        ResultVO<CouponVO> ret = new ResultVO<>(Constant.REQUEST_SUCCESS, "优惠活动创建成功", new CouponVO(coupon));
        if (couponDTO.getDeliverCouponDTO() != null) {
            // 异步发放优惠券
            DeliverCouponDTO deliverCouponDTO = couponDTO.getDeliverCouponDTO();
            deliverCouponDTO.setCouponId(coupon.getId());
            deliverCouponToUsers(deliverCouponDTO);
        }
        return ret;
    }

    @Override
    public ResultVO<Boolean> deliverCoupon(DeliverCouponDTO courseCouponDTO) {
        deliverCouponToUsers(courseCouponDTO);
        return new ResultVO<>(Constant.REQUEST_SUCCESS, "发放成功");
    }

    @Override
    public ResultVO<List<CouponVO>> getAllAvailableCoupons() {
        List<Coupon> coupons = couponMapper.selectAll();
        LocalDateTime now = LocalDateTime.now();
        // 获取还未结束或还未开始的可发放的优惠券
        List<CouponVO> result = coupons.stream().filter(coupon -> coupon.getEndTime().isAfter(now) && coupon.isValid()).map(CouponVO::new).collect(Collectors.toList());
        return new ResultVO<>(Constant.REQUEST_SUCCESS, "获取成功", result);
    }

    @Override
    public ResultVO<List<CouponVO>> getAllAvailableCouponsForOrder(Integer orderId) {
        CourseOrder courseOrder = orderService.getByPrimaryKey(orderId);
        if (orderId == null) {
            return new ResultVO<>(Constant.REQUEST_FAIL, "查询失败，订单不存在！", Collections.EMPTY_LIST);
        }
        CourseOrderVO orderVO = new CourseOrderVO(courseOrder);
        // 获取已经使用的优惠券
        List<Coupon> usedCoupons = couponMapper.selectByOrderId(orderVO.getId());
        boolean sharable = true;
        // 获取对应的优惠券
        for (Coupon usedCoupon : usedCoupons) {
            if (!usedCoupon.isSharable()) {
                sharable = false;
                break;
            }
        }
        if (sharable) {
            // 查询可用优惠券
            List<Coupon> userCoupons = couponMapper.selectByUserId(orderVO.getUserId());
            List<Coupon> availableCoupons = new ArrayList<>();
            Course course = courseService.getByPrimaryKey(orderVO.getCourseId());
            for (Coupon coupon : userCoupons) {
                if (coupon.getType().getCouponStrategy().canUse(orderVO, coupon) && coupon.getScope().canUse(course, coupon)) {
                    // 再去除当前订单使用的
                    Coupon usedCoupon = usedCoupons.stream().filter(used -> used.getId().equals(coupon.getId())).findFirst().orElse(null);
                    if (usedCoupon == null) {
                        availableCoupons.add(coupon);
                    }
                }
            }
            return new ResultVO<>(Constant.REQUEST_SUCCESS, "查询成功", availableCoupons.stream().map(CouponVO::new).collect(Collectors.toList()));
        }
        return new ResultVO<>(Constant.REQUEST_SUCCESS, "查询成功", Collections.EMPTY_LIST);
    }

    @Override
    public List<Coupon> getByOrderId(Integer orderId) {
        return couponMapper.selectByOrderId(orderId);
    }

    @Override
    public Coupon getByPrimaryKey(Integer couponId) {
        return couponMapper.selectByPrimaryKey(couponId);
    }

    @Override
    public List<UserCoupon> getUserCouponsByUserId(Integer userId) {
        return userCouponMapper.selectByUserId(userId);
    }

    @Override
    public void deleteUserCoupon(Integer userId, Integer couponId) {
        userCouponMapper.deleteByUserIdAndCouponId(userId, couponId);
    }

    @Override
    public void updateUserCoupon(UserCoupon userCoupon) {
        userCouponMapper.update(userCoupon);
    }

    @Override
    public UserCoupon getUserCouponByCouponIdAndUserId(Integer couponId, Integer userId) {
        return userCouponMapper.selectByCouponIdAndUserId(couponId, userId);
    }

    @Async("taskExecutor")
    protected void deliverCouponToUsers(DeliverCouponDTO deliverCouponDTO) {
        List<User> users = userService.getAll();
        Coupon coupon = couponMapper.selectByPrimaryKey(deliverCouponDTO.getCouponId());
        if (coupon == null || users.isEmpty()) return;
        List<User> targetUsers = new ArrayList<>();
        CouponDeliverStrategy deliverStrategy = deliverCouponDTO.getDeliver().getDeliverStrategy();
        for (User user : users) {
            if (deliverStrategy.canDeliver(user, coupon, deliverCouponDTO.getExtra())) {
                if (deliverCouponDTO.getCanDuplicate()) {
                    targetUsers.add(user);
                } else {
                    UserCoupon userCoupon = userCouponMapper.selectByCouponIdAndUserId(deliverCouponDTO.getCouponId(), user.getId());
                    if (userCoupon == null) {
                        targetUsers.add(user);
                    }
                }
            }
        }
        for (User user : targetUsers) {
            UserCoupon userCoupon = userCouponMapper.selectByCouponIdAndUserId(deliverCouponDTO.getCouponId(), user.getId());
            if (userCoupon != null) {
                userCoupon.setNums(userCoupon.getNums() + deliverCouponDTO.getNums());
                userCouponMapper.update(userCoupon);
            } else {
                userCoupon = new UserCoupon(deliverCouponDTO.getCouponId(), user.getId(), deliverCouponDTO.getNums());
                userCouponMapper.insert(userCoupon);
            }
        }
    }
}
