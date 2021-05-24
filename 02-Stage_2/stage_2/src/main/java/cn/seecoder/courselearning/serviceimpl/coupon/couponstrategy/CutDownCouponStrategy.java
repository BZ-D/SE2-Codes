package cn.seecoder.courselearning.serviceimpl.coupon.couponstrategy;

import cn.seecoder.courselearning.po.coupon.Coupon;
import cn.seecoder.courselearning.util.JSONHelper;
import cn.seecoder.courselearning.vo.order.CourseOrderVO;
import org.springframework.stereotype.Component;

/**
 * 满减策略
 * 注意这里的满减策略的使用判断是按原价
 * 即有多张优惠券叠加使用的话，只要都过了门槛，都能使用
 * 而如果需求是使用过一张优惠券之后再按使用后的金额进行满减计算，则可以再定义一个满减策略实现这样的逻辑
 */
@Component
public class CutDownCouponStrategy extends AbstractCouponStrategy {
    @Override
    public boolean canUse(CourseOrderVO orderVO, Coupon coupon) {
        Object threshold = JSONHelper.getByProperty(coupon.getMetadata(), "threshold");
        if (threshold == null) {
            // 无效优惠券
            return false;
        }
        // 判断满减策略
        return super.canUse(orderVO, coupon) && orderVO.getOrigin() >= (Integer)threshold;
    }

    @Override
    public int useCoupon(CourseOrderVO orderVO, Coupon coupon) {
        Integer cutDown = (Integer)JSONHelper.getByProperty(coupon.getMetadata(), "cutDown");
        int initialCost = orderVO.getCost() == null ? orderVO.getOrigin() : orderVO.getCost();
        if (initialCost - cutDown > 0) {
            return initialCost - cutDown;
        } else return 0;
    }
}
