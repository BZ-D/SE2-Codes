package cn.seecoder.courselearning.service.coupon;

import cn.seecoder.courselearning.dto.coupon.CouponDTO;
import cn.seecoder.courselearning.dto.coupon.DeliverCouponDTO;
import cn.seecoder.courselearning.po.coupon.Coupon;
import cn.seecoder.courselearning.po.coupon.UserCoupon;
import cn.seecoder.courselearning.vo.coupon.CouponVO;
import cn.seecoder.courselearning.vo.ResultVO;

import java.util.List;

public interface CouponService {
    // 创建并发放优惠券
    ResultVO<CouponVO> createCoupon(CouponDTO couponDTO);
    // 发放现有的优惠券
    ResultVO<Boolean> deliverCoupon(DeliverCouponDTO courseCouponDTO);
    // 网站经理获取所有可发放优惠券（用于选择优惠券并发放）
    ResultVO<List<CouponVO>> getAllAvailableCoupons();
    // 查看某用户购买此课程可用的所有优惠
    ResultVO<List<CouponVO>> getAllAvailableCouponsForOrder(Integer orderId);

    List<Coupon> getByOrderId(Integer orderId);

    Coupon getByPrimaryKey(Integer couponId);

    List<UserCoupon> getUserCouponsByUserId(Integer userId);

    void deleteUserCoupon(Integer userId, Integer couponId);

    void updateUserCoupon(UserCoupon userCoupon);

    UserCoupon getUserCouponByCouponIdAndUserId(Integer couponId, Integer userId);

}
