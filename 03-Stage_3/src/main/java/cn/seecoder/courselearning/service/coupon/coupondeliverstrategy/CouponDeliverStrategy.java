package cn.seecoder.courselearning.service.coupon.coupondeliverstrategy;

import cn.seecoder.courselearning.po.coupon.Coupon;
import cn.seecoder.courselearning.po.user.User;

import java.util.Map;

/**
 * 优惠券发放策略
 */
public interface CouponDeliverStrategy {

    /**
     * 判断该用户是否是优惠券发放对象
     * @param user 用户
     * @param coupon 优惠券
     * @param extra 额外的判断数据
     * @return
     */
    boolean canDeliver(User user, Coupon coupon, Map<String, String> extra);
}
