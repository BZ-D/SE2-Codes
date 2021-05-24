package cn.seecoder.courselearning.enums;

import cn.seecoder.courselearning.service.coupon.coupondeliverstrategy.CouponDeliverStrategy;
import cn.seecoder.courselearning.serviceimpl.coupon.coupondeliverstrategy.AllUserDeliverStrategy;
import cn.seecoder.courselearning.serviceimpl.coupon.coupondeliverstrategy.CourseLikeDeliverStrategy;
import cn.seecoder.courselearning.serviceimpl.coupon.coupondeliverstrategy.SingleUserDeliverStrategy;
import lombok.Getter;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 优惠券发放对象类型
 */
@Getter
public enum CouponDeliver {
    SINGLE("发放给单个用户", CouponDeliver.ApplicationContextInjector.applicationContext.getBean(SingleUserDeliverStrategy.class)),
    COURSE_LIKE("发放给某课程点赞的用户", CouponDeliver.ApplicationContextInjector.applicationContext.getBean(CourseLikeDeliverStrategy.class)),
    ALL("发放给全体用户", CouponDeliver.ApplicationContextInjector.applicationContext.getBean(AllUserDeliverStrategy.class));

    private final String value;

    private CouponDeliverStrategy deliverStrategy;

    CouponDeliver(String value, CouponDeliverStrategy deliverStrategy) {
        this.deliverStrategy = deliverStrategy;
        this.value = value;
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
