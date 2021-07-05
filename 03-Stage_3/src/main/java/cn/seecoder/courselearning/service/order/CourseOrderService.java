package cn.seecoder.courselearning.service.order;

import cn.seecoder.courselearning.vo.order.CourseOrderVO;
import cn.seecoder.courselearning.vo.ResultVO;

import java.util.List;


public interface CourseOrderService {
    // 更新订单
    ResultVO<CourseOrderVO> updateCourseOrder(Integer orderId, Integer orderStatus);
    // 根据用户id获取该用户的所有订单
    List<CourseOrderVO> getCourseOrders(Integer uid);
    // 使用优惠券/活动
    ResultVO<Boolean> useCoupon(CourseOrderVO orderVO, Integer couponId);
    // 取消使用优惠券/活动
    ResultVO<Boolean> cancelCoupon(CourseOrderVO orderVO, Integer couponId);


    ResultVO<CourseOrderVO> getCourseOrderById(Integer orderId);
    // 完成付款
    ResultVO<Boolean> payOrder(Integer orderId);
    // 创建订单
    ResultVO<CourseOrderVO> createCourseOrder(Integer courseId, Integer userId);
}
