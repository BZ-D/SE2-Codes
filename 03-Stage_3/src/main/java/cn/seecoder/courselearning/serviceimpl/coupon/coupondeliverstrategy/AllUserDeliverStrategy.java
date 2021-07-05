package cn.seecoder.courselearning.serviceimpl.coupon.coupondeliverstrategy;

import cn.seecoder.courselearning.po.coupon.Coupon;
import cn.seecoder.courselearning.po.user.User;
import cn.seecoder.courselearning.service.coupon.coupondeliverstrategy.CouponDeliverStrategy;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class AllUserDeliverStrategy implements CouponDeliverStrategy {
    @Override
    public boolean canDeliver(User user, Coupon coupon, Map<String, String> extra) {
        return true;
    }
}
