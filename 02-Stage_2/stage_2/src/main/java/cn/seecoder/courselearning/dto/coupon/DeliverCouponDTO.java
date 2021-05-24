package cn.seecoder.courselearning.dto.coupon;

import cn.seecoder.courselearning.enums.CouponDeliver;
import lombok.Data;

import java.util.Map;

@Data
public class DeliverCouponDTO {
    /**
     * 要发放的优惠券id
     */
    private Integer couponId;

    /**
     * 优惠券发放对象类型
     */
    private CouponDeliver deliver;

    /**
     * 判断发放类型时额外的判断数据
     * 如发放类型为基于某课程的点赞数，这里则传递课程id，以及该点赞数的发放阈值
     */
    private Map<String, String> extra;

    /**
     * 是否可以发放给已经发放过该优惠券的用户
     */
    private Boolean canDuplicate;
    
    // 发放数量
    private Integer nums;
}
