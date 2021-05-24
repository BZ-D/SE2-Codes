package cn.seecoder.courselearning.mapperservice.course;

import cn.seecoder.courselearning.po.course.CourseWare;
import java.util.List;

public interface CourseWareMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CourseWare record);

    CourseWare selectByPrimaryKey(Integer id);

    List<CourseWare> selectAll();

    int updateByPrimaryKey(CourseWare record);

    List<CourseWare> selectByCourseId(Integer courseId);
}