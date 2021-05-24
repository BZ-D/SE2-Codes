package cn.seecoder.courselearning.util;

import cn.seecoder.courselearning.po.coupon.Coupon;

public class CouponValidator {
    public static boolean isInvalid(Coupon coupon) {
        if (coupon.getStartTime() != null && coupon.getEndTime() != null
                && coupon.getStartTime().isAfter(coupon.getEndTime()))
            return true;
        return false;
    }
}
