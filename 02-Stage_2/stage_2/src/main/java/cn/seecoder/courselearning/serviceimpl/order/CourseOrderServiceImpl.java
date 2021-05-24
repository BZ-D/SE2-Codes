package cn.seecoder.courselearning.serviceimpl.order;

import cn.seecoder.courselearning.mapperservice.coupon.CourseOrderCouponMapper;
import cn.seecoder.courselearning.mapperservice.order.CourseOrderMapper;
import cn.seecoder.courselearning.po.coupon.Coupon;
import cn.seecoder.courselearning.po.coupon.CourseOrderCoupon;
import cn.seecoder.courselearning.po.coupon.UserCoupon;
import cn.seecoder.courselearning.po.course.Course;
import cn.seecoder.courselearning.po.order.CourseOrder;
import cn.seecoder.courselearning.service.coupon.CouponService;
import cn.seecoder.courselearning.service.course.CourseService;
import cn.seecoder.courselearning.service.order.CourseOrderService;
import cn.seecoder.courselearning.service.user.UserService;
import cn.seecoder.courselearning.util.Constant;
import cn.seecoder.courselearning.util.IntHolder;
import cn.seecoder.courselearning.vo.*;
import cn.seecoder.courselearning.vo.coupon.CouponVO;
import cn.seecoder.courselearning.vo.course.CourseVO;
import cn.seecoder.courselearning.vo.order.CourseOrderVO;
import cn.seecoder.courselearning.vo.user.UserVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CourseOrderServiceImpl implements CourseOrderService {
    @Resource
    CourseOrderMapper orderMapper;
    @Resource
    CourseOrderCouponMapper courseOrderCouponMapper;

    UserService userService;

    CouponService couponService;

    CourseService courseService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setCouponService(CouponService couponService) {
        this.couponService = couponService;
    }

    @Autowired
    public void setCourseService(CourseService courseService) {
        this.courseService = courseService;
    }


    @Override
    public ResultVO<CourseOrderVO> updateCourseOrder(Integer orderId, Integer orderStatus) {
        CourseOrder order = orderMapper.selectByPrimaryKey(orderId);
        if(order == null) return new ResultVO<>(Constant.REQUEST_FAIL, "找不到当前订单！");
        if(order.getStatus().equals(Constant.ORDER_STATUS_SUCCESS))
            return new ResultVO<>(Constant.REQUEST_FAIL, "当前订单已完成交易，不支持再次修改订单状态！", new CourseOrderVO(order));
        if((orderStatus.equals(Constant.ORDER_STATUS_SUCCESS)||orderStatus.equals(Constant.ORDER_STATUS_WAIT)) &&
                order.getStatus().equals(Constant.ORDER_STATUS_UNPAID)){
            // 当订单原始状态为待支付 并且此时为支付成功时，需扣除用户余额
            UserVO user = userService.getUser(order.getUserId());
            if(user.getBalance()>=order.getCost()){
                userService.decreaseBalance(user.getId(), order.getCost());
                updateUserCoupon(orderId, user.getId());
            }else
                return new ResultVO<>(Constant.REQUEST_FAIL, "用户余额不足");
        }
        order.setStatus(orderStatus);
        orderMapper.updateByPrimaryKey(order);
        return new ResultVO<>(Constant.REQUEST_SUCCESS, "课程购买成功", new CourseOrderVO(order));
    }

    /**
     * 订单完成时更新用户优惠券
     * @param orderId
     * @param userId
     */
    public void updateUserCoupon(Integer orderId, Integer userId) {
        // 更新用户优惠券状态
        List<Coupon> orderCoupons = couponService.getByOrderId(orderId);
        List<UserCoupon> userCouponList = couponService.getUserCouponsByUserId(userId);
        for (UserCoupon userCoupon : userCouponList) {
            if (orderCoupons.stream().anyMatch(coupon -> coupon.getId().equals(userCoupon.getCouponId()))) {
                if (userCoupon.getNums() == 1) {
                    couponService.deleteUserCoupon(userId, userCoupon.getCouponId());
                } else if (userCoupon.getNums() > 1) {
                    userCoupon.setNums(userCoupon.getNums() - 1);
                    couponService.updateUserCoupon(userCoupon);
                }
            }
        }
    }

    @Override
    public List<CourseOrderVO> getCourseOrders(Integer uid) {
        List<CourseOrderVO> ret = new ArrayList<>();
        List<CourseOrder> orderList = orderMapper.selectByUserId(uid);
        LocalDateTime now = LocalDateTime.now();
        for(CourseOrder order: orderList){
            // 获取订单对应的优惠券
            List<Coupon> courseOrderCoupons = couponService.getByOrderId(order.getId());
            List<Coupon> coupons = new ArrayList<>();
            boolean updated = false;
            // 查询的时候更新一下，以防优惠券在这个时候过期了
            for (Coupon courseOrderCoupon : courseOrderCoupons) {
                if (!(courseOrderCoupon.isValid() && courseOrderCoupon.getStartTime().isBefore(now) && courseOrderCoupon.getEndTime().isAfter(now))) {
                    updated = true;
                    courseOrderCouponMapper.deleteByOrderIdAndCouponId(order.getId(), courseOrderCoupon.getId());
                } else {
                    coupons.add(courseOrderCoupon);
                }
            }
            if (updated) {
                updateBestCost(new CourseOrderVO(order));
                order = orderMapper.selectByPrimaryKey(order.getId());
            }
            CourseOrderVO orderVO = new CourseOrderVO(order);
            orderVO.setUsedCoupons(coupons.stream().map(CouponVO::new).collect(Collectors.toList()));
            ret.add(orderVO);
        }
        return ret;
    }

    /**
     * 当前订单使用该优惠券
     * @param orderVO
     * @param couponId
     * @return
     */
    @Override
    public ResultVO<Boolean> useCoupon(CourseOrderVO orderVO, Integer couponId) {
        Coupon coupon = couponService.getByPrimaryKey(couponId);
        if (coupon == null) {
            return new ResultVO<>(Constant.REQUEST_FAIL, "错误的优惠券");
        }
        LocalDateTime now = LocalDateTime.now();
        if (!(coupon.isValid() && coupon.getStartTime().isBefore(now) && coupon.getEndTime().isAfter(now))) {
            return new ResultVO<>(Constant.REQUEST_FAIL, "优惠券过期或还未能使用");
        }
        // 判断用户有没有该优惠券
        UserCoupon userCoupon = couponService.getUserCouponByCouponIdAndUserId(couponId, orderVO.getUserId());
        if (userCoupon == null) {
            return new ResultVO<>(Constant.REQUEST_FAIL, "尚未拥有该优惠券");
        }
        // 判断能否使用
        Course course = courseService.getByPrimaryKey(orderVO.getCourseId());
        if (!coupon.getScope().canUse(course, coupon)) {
            return new ResultVO<>(Constant.REQUEST_FAIL, "该课程无法使用该优惠券");
        }
        // 判断有没有达到使用的门槛
        if (!coupon.getType().getCouponStrategy().canUse(orderVO, coupon)) {
            return new ResultVO<>(Constant.REQUEST_FAIL, "没有达到使用门槛！");
        }
        // 判断是否已经用过一张了
        CourseOrderCoupon courseOrderCoupon = courseOrderCouponMapper.selectByOrderIdAndCouponId(orderVO.getId(), couponId);
        if (courseOrderCoupon != null) {
            return new ResultVO<>(Constant.REQUEST_FAIL, "已经使用过优惠券！");
        }
        courseOrderCouponMapper.insert(new CourseOrderCoupon(orderVO.getId(), couponId));
        updateBestCost(orderVO);
        return new ResultVO<>(Constant.REQUEST_SUCCESS, "使用成功");
    }

    @Override
    public ResultVO<Boolean> cancelCoupon(CourseOrderVO orderVO, Integer couponId) {
        courseOrderCouponMapper.deleteByOrderIdAndCouponId(orderVO.getId(), couponId);
        updateBestCost(orderVO);
        return new ResultVO<>(Constant.REQUEST_SUCCESS, "取消使用成功！");
    }

    @Override
    public ResultVO<CourseOrderVO> getCourseOrderById(Integer orderId) {
        CourseOrder order = orderMapper.selectByPrimaryKey(orderId);
        CourseOrderVO orderVO = new CourseOrderVO();
        BeanUtils.copyProperties(order,orderVO);
        return new ResultVO<>(Constant.REQUEST_SUCCESS,"获取订单信息成功",orderVO);
    }

    @Override
    public ResultVO<Boolean> payOrder(Integer orderId) {
        CourseOrder order = orderMapper.selectByPrimaryKey(orderId);
        // 当订单状态为未支付时，检查用户余额是否足够
        UserVO userVO = userService.getUser(order.getUserId());

        if(userVO.getBalance()>=order.getCost()){
            userService.decreaseBalance(userVO.getId(),order.getCost());
            // 支付成功，将订单存入数据库
            ResultVO<CourseOrderVO> resultVO = updateCourseOrder(orderId,Constant.ORDER_STATUS_SUCCESS);
            if (resultVO.getCode().equals(Constant.REQUEST_SUCCESS)){
                return new ResultVO<>(Constant.REQUEST_SUCCESS,"付款成功");
            }else{
                return new ResultVO<>(Constant.REQUEST_FAIL,resultVO.getMsg());
            }
        }else{
            return new ResultVO<>(Constant.REQUEST_FAIL,"付款失败,余额不足");
        }

    }

    /**
     * 创建订单，没有检查重复
     * @param courseId
     * @param userId
     * @return
     * @author cyx
     */
    @Override
    public ResultVO<CourseOrderVO> createCourseOrder(Integer courseId, Integer userId) {
        List<CourseOrder> courseOrderList = orderMapper.selectByUserId(userId);

        for(CourseOrder order: courseOrderList){
            if(order.getCourseId().equals(courseId)){
                if(order.getStatus() == Constant.ORDER_STATUS_SUCCESS){
                    return new ResultVO<>(Constant.REQUEST_FAIL,"已购买该课程");

                }
                List<Coupon> usedCoupons = couponService.getByOrderId(order.getId());
                List<CouponVO> couponVOS = new ArrayList<>();
                for(Coupon coupon:usedCoupons){
                    couponVOS.add(new CouponVO(coupon));
                }
                CourseOrderVO courseOrderVO = new CourseOrderVO(order);
                courseOrderVO.setUsedCoupons(couponVOS);
                return new ResultVO<>(Constant.REQUEST_SUCCESS,"恢复到未完成订单", courseOrderVO);
            }
        }
        CourseOrder order = new CourseOrder();

        order.setUserId(userId);
        order.setCourseId(courseId);
        CourseVO courseVO = courseService.getCourse(courseId,userId);
        order.setCost(courseVO.getCost());
        order.setStatus(Constant.ORDER_STATUS_UNPAID);
        order.setCreateTime(new Date());
        order.setCourseName(courseVO.getName());
        order.setOrigin(courseVO.getCost());

        if(orderMapper.insert(order) != 1){
            return new ResultVO<>(Constant.REQUEST_FAIL,"创建失败",null);
        }

        CourseOrderVO courseOrderVO = new CourseOrderVO(order);

        return new ResultVO<>(Constant.REQUEST_SUCCESS,"创建成功",courseOrderVO);
    }


    public void updateBestCost(CourseOrderVO orderVO) {
        List<Coupon> usedCoupons = couponService.getByOrderId(orderVO.getId());
        boolean[] used = new boolean[usedCoupons.size()];
        Arrays.fill(used, false);
        // 这里通过dfs算法计算最优价格，而IntHolder为可变的int，用于记录最小值
        // 这里Integer不能使用，因为是Immutable的，每次会创建一个新对象，效果等同于值传递
        IntHolder minCost = new IntHolder(orderVO.getOrigin());
        orderVO.setCost(null);
        getBestCost(orderVO, usedCoupons, used, orderVO.getOrigin(), minCost);
        orderVO.setCost(minCost.value);
        // 保存
        CourseOrder courseOrder = orderMapper.selectByPrimaryKey(orderVO.getId());
        courseOrder.setCost(orderVO.getCost());
        orderMapper.updateByPrimaryKey(courseOrder);
    }

    /**
     * 获得最优的cost
     * @param courseOrderVO
     * @param usedCoupons
     */
    public void getBestCost(CourseOrderVO courseOrderVO, List<Coupon> usedCoupons, boolean[] used, int currentCost, IntHolder minCost) {
        boolean allUsed = true;
        for (int i = 0; i < used.length; i++) {
            if (!used[i]) {
                allUsed = false;
                break;
            }
        }
        if (allUsed) {
            if ( minCost.value > currentCost) {
                minCost.value = currentCost;
            }
        } else {
            for (int i = 0; i < used.length; i++) {
                if (!used[i]) {
                    used[i] = true;
                    Integer oldCost = courseOrderVO.getCost();
                    int newCost = usedCoupons.get(i).getType().getCouponStrategy().useCoupon(courseOrderVO, usedCoupons.get(i));
                    courseOrderVO.setCost(newCost);
                    getBestCost(courseOrderVO, usedCoupons, used, newCost, minCost);
                    courseOrderVO.setCost(oldCost);
                    used[i] = false;
                }
            }
        }
    }
}
