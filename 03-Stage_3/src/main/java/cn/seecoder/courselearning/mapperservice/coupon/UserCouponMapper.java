package cn.seecoder.courselearning.mapperservice.coupon;

import cn.seecoder.courselearning.po.coupon.UserCoupon;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserCouponMapper {

    int insert(UserCoupon userCoupon);

    int update(UserCoupon userCoupon);

    UserCoupon selectByCouponIdAndUserId(@Param(value = "couponId") Integer couponId, @Param(value = "userId")  Integer userId);

    List<UserCoupon> selectByUserId(Integer userId);

    int deleteByUserIdAndCouponId(@Param(value = "userId") Integer userId, @Param(value = "couponId") Integer couponId);
}
