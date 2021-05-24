package cn.seecoder.courselearning.serviceimpl.order;

import cn.seecoder.courselearning.po.order.CourseOrder;
import cn.seecoder.courselearning.service.order.QueryOrderService;
import org.springframework.stereotype.Service;


@Service
public class QueryOrderServiceImpl implements QueryOrderService {

    @Override
    public CourseOrder queryMostRecentOrder(Integer uid, Integer courseId) {
        return null;
    }

    @Override
    public CourseOrder getByPrimaryKey(Integer orderId) {
        return null;
    }
}
