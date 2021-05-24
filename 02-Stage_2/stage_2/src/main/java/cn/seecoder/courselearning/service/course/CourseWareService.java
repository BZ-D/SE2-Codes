package cn.seecoder.courselearning.service.course;

import cn.seecoder.courselearning.vo.course.CourseWareVO;
import cn.seecoder.courselearning.vo.ResultVO;

import java.util.List;


public interface CourseWareService {
    // 获取单个课件信息
    CourseWareVO getCourseWare(Integer uid, Integer courseWareId);
    // 获取课程所有课件的信息
    List<CourseWareVO> getAllCourseWare(Integer uid, Integer courseId);
    // 创建课件
    ResultVO<CourseWareVO> createCourseWare(CourseWareVO courseWareVO);
    // 更新课件
    ResultVO<CourseWareVO> updateCourseWare(CourseWareVO courseWareVO);
    // 删除课件
    ResultVO<String> deleteCourseWare(Integer courseWareId);
}
