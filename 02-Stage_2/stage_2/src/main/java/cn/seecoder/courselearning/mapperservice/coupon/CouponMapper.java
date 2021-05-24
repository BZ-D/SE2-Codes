package cn.seecoder.courselearning.mapperservice.coupon;

import cn.seecoder.courselearning.po.coupon.Coupon;
import java.util.List;

public interface CouponMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Coupon record);

    Coupon selectByPrimaryKey(Integer id);

    List<Coupon> selectAll();

    int updateByPrimaryKey(Coupon record);

    // 根据用户Id查找优惠券
    List<Coupon> selectByUserId(Integer userId);

    // 根据订单id查找订单已使用优惠券
    List<Coupon> selectByOrderId(Integer orderId);
}