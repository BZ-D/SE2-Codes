package cn.seecoder.courselearning.service.coupon.couponstrategy;

import cn.seecoder.courselearning.po.coupon.Coupon;
import cn.seecoder.courselearning.vo.order.CourseOrderVO;


// 优惠券匹配策略
public interface CouponStrategy {

    /**
     * 判断该订单能否使用该优惠券
     * @param orderVO 课程订单
     * @param coupon 优惠活动 或 优惠券
     */
    boolean canUse(CourseOrderVO orderVO, Coupon coupon);

    /**
     * 使用优惠券
     * @param orderVO
     * @param coupon
     * @return 返回优惠价格
     */
    int useCoupon(CourseOrderVO orderVO, Coupon coupon);
}
