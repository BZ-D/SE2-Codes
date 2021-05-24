package cn.seecoder.courselearning.vo.coupon;

import cn.seecoder.courselearning.po.coupon.Coupon;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class CouponVO {
    /**
     * 优惠券id
     */
    private Integer id;

    /**
     * 优惠券类型（折扣型、减价型）
     */
    private String type;

    /**
     * 优惠券作用范围（单个课程、某类老师的课程，某个学校的课程）
     */
    private String scope;

    /**
     * 优惠券名称
     */
    private String name;

    /**
     * 优惠券描述
     */
    private String description;

    /**
     * 优惠券元信息
     * @see Coupon 类的metadata属性
     */
    private String metadata;

    /**
     * 生效时间
     */
    private LocalDateTime startTime;

    /**
     * 失效时间
     */
    private LocalDateTime endTime;

    /**
     * 优惠券是否可用 true可用 false失效（一次性的券使用后就失效 或 网站关闭活动）
     */
    @Getter(AccessLevel.NONE)
    private Boolean valid;

    /**
     * 能否与其他优惠券同时使用 true可同时使用 false不能同时使用
     */
    @Getter(AccessLevel.NONE)
    private Boolean sharable;

    public Boolean isValid() {
        return valid;
    }

    public Boolean isSharable() {
        return sharable;
    }

    public CouponVO(Coupon coupon) {
        this.id = coupon.getId();
        this.type = coupon.getType().toString();
        this.scope = coupon.getScope().toString();
        this.name = coupon.getName();
        this.description = coupon.getDescription();
        this.metadata = coupon.getMetadata();
        this.startTime = coupon.getStartTime();
        this.endTime = coupon.getEndTime();
        this.valid = coupon.isValid();
        this.sharable = coupon.isSharable();
    }
}
