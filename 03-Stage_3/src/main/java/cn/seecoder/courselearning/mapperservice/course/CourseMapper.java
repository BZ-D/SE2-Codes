package cn.seecoder.courselearning.mapperservice.course;

import cn.seecoder.courselearning.po.course.Course;
import java.util.List;

public interface CourseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Course record);

    Course selectByPrimaryKey(Integer id);

    List<Course> selectAll();

    int updateByPrimaryKey(Course record);

    List<Course> selectByType(String type);

    List<Course> queryAll(String key);

    List<Course> selectByTeacherId(Integer userId);

    List<Course> selectByStudentId(Integer userId);

    // 根据点赞数 返回热门课程列表
    List<Course> selectHotCourses();
}