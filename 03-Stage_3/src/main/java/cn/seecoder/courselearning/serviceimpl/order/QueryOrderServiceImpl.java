package cn.seecoder.courselearning.serviceimpl.order;


import cn.seecoder.courselearning.mapperservice.order.CourseOrderMapper;
import cn.seecoder.courselearning.po.order.CourseOrder;
import cn.seecoder.courselearning.service.order.QueryOrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class QueryOrderServiceImpl implements QueryOrderService {
    @Resource
    private CourseOrderMapper courseOrderMapper;
    @Override
    public CourseOrder queryMostRecentOrder(Integer uid, Integer courseId) {
        CourseOrder courseOrder=courseOrderMapper.queryMostRecentOrder(uid,courseId);
        return courseOrder;
    }

    @Override
    public CourseOrder getByPrimaryKey(Integer orderId) {
        CourseOrder courseOrder=courseOrderMapper.selectByPrimaryKey(orderId);
        return courseOrder;
    }
}
