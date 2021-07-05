package cn.seecoder.courselearning.po.coupon;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 表明某优惠券被哪个订单使用
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseOrderCoupon {
    /**
     * 订单id
     */
    private Integer orderId;

    /**
     * 优惠券id
     */
    private Integer couponId;

}
