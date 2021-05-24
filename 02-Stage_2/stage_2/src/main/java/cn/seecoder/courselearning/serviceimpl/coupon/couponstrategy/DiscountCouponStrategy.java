package cn.seecoder.courselearning.serviceimpl.coupon.couponstrategy;

import cn.seecoder.courselearning.po.coupon.Coupon;
import cn.seecoder.courselearning.util.JSONHelper;
import cn.seecoder.courselearning.vo.order.CourseOrderVO;
import org.springframework.stereotype.Component;

/**
 * 这里的折扣策略不需要满一定数额才能用，可以根据业务需求调整
 */
@Component
public class DiscountCouponStrategy extends AbstractCouponStrategy {
    @Override
    public boolean canUse(CourseOrderVO orderVO, Coupon coupon) {
        Object discount = JSONHelper.getByProperty(coupon.getMetadata(), "discount");
        if (discount == null) {
            // 无效优惠券
            return false;
        }
        // 判断满减策略
        return super.canUse(orderVO, coupon) && (Double)discount > 0 && (Double)discount < 1;
    }
    @Override
    public int useCoupon(CourseOrderVO orderVO, Coupon coupon) {
        Double discount = (Double)JSONHelper.getByProperty(coupon.getMetadata(), "discount");
        int initialCost = orderVO.getCost() == null ? orderVO.getOrigin() : orderVO.getCost();
       return (int)(initialCost * discount);
    }
}
