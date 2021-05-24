package cn.seecoder.courselearning.service.order;

import cn.seecoder.courselearning.po.order.CourseOrder;

public interface QueryOrderService {

    CourseOrder queryMostRecentOrder(Integer uid, Integer courseId);

    CourseOrder getByPrimaryKey(Integer orderId);
}
