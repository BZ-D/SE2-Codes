package cn.seecoder.courselearning.enums;

import cn.seecoder.courselearning.po.coupon.Coupon;
import cn.seecoder.courselearning.po.course.Course;
import cn.seecoder.courselearning.util.JSONHelper;
import java.util.function.BiFunction;

/**
 * 优惠券使用范围
 * 可以分为对某一个课程使用SINGLE
 * 也可以分为对某一类课程使用如TEACHER，表明优惠券可以用在老师上
 */
public enum CouponScope {


    SINGLE("单个课程", (course,coupon) -> course.getId().equals((Integer)JSONHelper.getByProperty(coupon.getMetadata(), "courseId"))),
    TEACHER("某个老师的课程", (course, coupon) -> course.getId().equals((Integer)JSONHelper.getByProperty(coupon.getMetadata(), "teacherId"))),
    SCHOOL("某个学校的课程", (course, coupon) -> course.getId().equals((Integer)JSONHelper.getByProperty(coupon.getMetadata(), "schoolId"))),
    COMMON("通用，全站课程皆可使用", (course, coupon) -> true);

    private final String value;

    /**
     * 用来判断course是否满足coupon的使用范围
     */
    private final BiFunction<Course, Coupon, Boolean> couponScopeStrategy;

    CouponScope(String value, BiFunction<Course, Coupon, Boolean> couponScopeStrategy) {
        this.value = value;
        this.couponScopeStrategy = couponScopeStrategy;
    }

    @Override
    public String toString() {
        return value;
    }


    public boolean canUse(Course course, Coupon coupon) {
        return this.couponScopeStrategy.apply(course, coupon);
    }
}
