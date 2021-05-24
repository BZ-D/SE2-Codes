package cn.seecoder.courselearning.mapperservice.coupon;

import cn.seecoder.courselearning.po.coupon.CourseOrderCoupon;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CourseOrderCouponMapper {
    int insert(CourseOrderCoupon courseOrderCoupon);

    List<CourseOrderCoupon> selectByOrderId(Integer orderId);

    CourseOrderCoupon selectByOrderIdAndCouponId(@Param(value = "orderId") Integer orderId, @Param(value = "couponId") Integer couponId);

    int deleteByOrderIdAndCouponId(@Param(value = "orderId") Integer orderId, @Param(value = "couponId") Integer couponId);
}
