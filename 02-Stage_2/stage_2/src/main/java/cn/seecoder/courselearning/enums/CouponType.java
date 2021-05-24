package cn.seecoder.courselearning.enums;

import cn.seecoder.courselearning.service.coupon.couponstrategy.CouponStrategy;
import cn.seecoder.courselearning.serviceimpl.coupon.couponstrategy.CutDownCouponStrategy;
import cn.seecoder.courselearning.serviceimpl.coupon.couponstrategy.DiscountCouponStrategy;
import lombok.Getter;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Getter
public enum CouponType {
    DISCOUNT("折扣型", ApplicationContextInjector.applicationContext.getBean(DiscountCouponStrategy.class)),
    CUT_DOWN("减价型", ApplicationContextInjector.applicationContext.getBean(CutDownCouponStrategy.class));

    private final String value;

    private final CouponStrategy couponStrategy;

    CouponType(String value, CouponStrategy couponStrategy) {
        this.value = value;
        this.couponStrategy = couponStrategy;
    }

    @Override
    public String toString() {
        return value;
    }

    @Component
    public static class ApplicationContextInjector implements ApplicationContextAware {
        public static ApplicationContext applicationContext;
        @Override
        public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
            this.applicationContext = applicationContext;
        }
    }
}
