package cn.seecoder.courselearning.serviceimpl.course;

import cn.seecoder.courselearning.mapperservice.course.CourseWareMapper;
import cn.seecoder.courselearning.po.order.CourseOrder;
import cn.seecoder.courselearning.po.course.CourseWare;
import cn.seecoder.courselearning.service.course.CourseWareService;
import cn.seecoder.courselearning.service.order.QueryOrderService;
import cn.seecoder.courselearning.util.Constant;
import cn.seecoder.courselearning.vo.course.CourseWareVO;
import cn.seecoder.courselearning.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CourseWareServiceImpl implements CourseWareService {
    @Resource
    private CourseWareMapper courseWareMapper;

    private QueryOrderService orderService;

    @Autowired
    public void setOrderService(QueryOrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public CourseWareVO getCourseWare(Integer uid, Integer courseWareId) {
        CourseWare courseWare = courseWareMapper.selectByPrimaryKey(courseWareId);
        Integer courseId = courseWare.getCourseId();
        boolean bought = false;
        if (uid != null && uid > 0){
            CourseOrder order = orderService.queryMostRecentOrder(uid, courseId);
            if(order != null)
                bought = order.getStatus().equals(Constant.ORDER_STATUS_SUCCESS);
        }
        return new CourseWareVO(courseWare, bought);
    }

    @Override
    public List<CourseWareVO> getAllCourseWare(Integer uid, Integer courseId) {
        boolean bought = false;
        if (uid != null && uid > 0){
            CourseOrder order = orderService.queryMostRecentOrder(uid, courseId);
            if(order != null)
                bought = order.getStatus().equals(Constant.ORDER_STATUS_SUCCESS);
        }
        List<CourseWare> tempList = courseWareMapper.selectByCourseId(courseId);
        List<CourseWareVO> ret = new ArrayList<>();
        for(CourseWare courseWare: tempList){
            ret.add(new CourseWareVO(courseWare, bought));
        }
        return ret;
    }

    @Override
    public ResultVO<CourseWareVO> createCourseWare(CourseWareVO courseWareVO) {
        courseWareVO.setUploadTime(new Date());
        CourseWare courseWare = new CourseWare(courseWareVO);
        if(courseWareMapper.insert(courseWare) > 0)
            return new ResultVO<>(Constant.REQUEST_SUCCESS, "课件创建成功", new CourseWareVO(courseWare));
        return new ResultVO<>(Constant.REQUEST_FAIL, "课件创建失败");
    }

    @Override
    public ResultVO<CourseWareVO> updateCourseWare(CourseWareVO courseWareVO) {
        courseWareVO.setUploadTime(new Date());
        CourseWare courseWare = new CourseWare(courseWareVO);
        if(courseWareMapper.updateByPrimaryKey(courseWare) > 0)
            return new ResultVO<>(Constant.REQUEST_SUCCESS, "课件更新成功", new CourseWareVO(courseWare));
        return new ResultVO<>(Constant.REQUEST_FAIL, "课件更新失败");
    }

    @Override
    public ResultVO<String> deleteCourseWare(Integer courseWareId) {
        if(courseWareMapper.selectByPrimaryKey(courseWareId) == null)
            return new ResultVO<>(Constant.REQUEST_FAIL, "课件不存在！");
        else
            courseWareMapper.deleteByPrimaryKey(courseWareId);
        return new ResultVO<>(Constant.REQUEST_SUCCESS, "课件删除成功");
    }
}
