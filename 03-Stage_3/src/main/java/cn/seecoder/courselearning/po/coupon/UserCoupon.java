package cn.seecoder.courselearning.po.coupon;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 表明某优惠券被哪个用户拥有
 * 这里不把userId放到Coupon中，是因为防止重复发放
 * 试思考一个场景，假如网站经理批量发放某一类优惠券到某一类用户，这时候倘若有新用户也满足了发放范围
 * 网站经理或定时想发放优惠券给这些新用户时，而老用户不要重复发放时，怎么知道该优惠券已经发放给用户？
 * 用Coupon中的其他属性判断显然是不现实的，并不能保证唯一
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCoupon {
    /**
     * 优惠券id
     */
    private Integer couponId;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 优惠券数量
     */
    private Integer nums;
}
