package cn.seecoder.courselearning.serviceimpl.coupon.couponstrategy;

import cn.seecoder.courselearning.mapperservice.coupon.UserCouponMapper;
import cn.seecoder.courselearning.po.coupon.Coupon;
import cn.seecoder.courselearning.po.course.Course;
import cn.seecoder.courselearning.po.coupon.UserCoupon;
import cn.seecoder.courselearning.service.coupon.couponstrategy.CouponStrategy;
import cn.seecoder.courselearning.service.course.CourseService;
import cn.seecoder.courselearning.vo.order.CourseOrderVO;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

/**
 * 基础的优惠策略，在这里可以进行优惠能否使用的基础判断
 */
public abstract class AbstractCouponStrategy implements CouponStrategy {

    @Autowired
    protected UserCouponMapper userCouponMapper;

    @Autowired
    protected CourseService courseService;

    @Override
    public boolean canUse(CourseOrderVO orderVO, Coupon coupon) {
        // 判断用户有没有该优惠券
        UserCoupon userCoupon = userCouponMapper.selectByCouponIdAndUserId(coupon.getId(),orderVO.getUserId());
        if (userCoupon == null) {
            return false;
        }
        // 判断是否过期，合法以及在使用时间内
        LocalDateTime now = LocalDateTime.now();
        if ((coupon.isValid()
                && (coupon.getStartTime()==null || now.isAfter(coupon.getStartTime()))
                && (coupon.getEndTime()==null || now.isBefore(coupon.getEndTime()))) == false) {
            return false;
        }
        // 根据类型判断是否能使用在课程上
        Course course = courseService.getByPrimaryKey(orderVO.getCourseId());
        if (course == null) {
            // 无效订单
            return false;
        }
        if (!coupon.getScope().canUse(course, coupon)) {
            return false;
        }
        return true;
    }
}
