package cn.seecoder.courselearning.po.coupon;

import cn.seecoder.courselearning.enums.CouponScope;
import cn.seecoder.courselearning.enums.CouponType;
import cn.seecoder.courselearning.vo.coupon.CouponVO;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class Coupon {
    /**
     * 优惠券id
     */
    private Integer id;

    /**
     * 优惠券类型
     */
    private CouponType type;

    /**
     * 优惠券使用范围
     */
    private CouponScope scope;

    /**
     * 优惠券名称
     */
    private String name;

    /**
     * 优惠券描述
     */
    private String description;

    /**
     * 优惠券元数据，对不同的优惠券类型，优惠券发放策略，优惠券使用范围不同，格式为JSON字符串
     * 如优惠券类型为满减，这里的元数据可以是{"threshold": 100, "cutDown": 20}表示满100减20的优惠券
     * 如优惠券类型为折扣，这里的元数据可以是{"discount": 0.8}表示打八折
     * 格式很自由，可以自定义数据，如自定义优惠券为满200减50，低于200打95折，这里则存储着优惠券类型会使用到的数据
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

    public Coupon(CouponVO couponVO) {
        this.id = couponVO.getId();
        this.type = CouponType.valueOf(couponVO.getType());
        this.scope = CouponScope.valueOf(couponVO.getScope());
        this.name = couponVO.getName();
        this.description = couponVO.getDescription();
        this.metadata = couponVO.getMetadata();
        this.startTime = couponVO.getStartTime();
        this.endTime = couponVO.getEndTime();
        this.valid = couponVO.isValid();
        this.sharable = couponVO.isSharable();
    }
}
