package cn.seecoder.courselearning.controller;

import cn.seecoder.courselearning.service.course.CourseWareService;
import cn.seecoder.courselearning.vo.course.CourseWareVO;
import cn.seecoder.courselearning.vo.ResultVO;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/course_ware")
public class CourseWareController {
    @Resource
    private CourseWareService courseWareService;

    /**
     * 获取单个课件的信息
     * @param uid 用户id
     * @param courseWareId 课件id
     */
    @GetMapping("/{courseWareId}")
    public CourseWareVO getCourseWare(@RequestParam(required = false, defaultValue = "") Integer uid,
                                            @PathVariable Integer courseWareId) {
        return courseWareService.getCourseWare(uid, courseWareId);
    }

    /**
     * 获取课件列表
     * 如果带参数uid，则需要判断该用户是否购买过课程
     * @param uid 用户id
     * @param courseId 课程id
     */
    @GetMapping("/course/{courseId}")
    public List<CourseWareVO> getCourseWares(@RequestParam(required = false, defaultValue = "") Integer uid,
                                            @PathVariable Integer courseId) {
        return courseWareService.getAllCourseWare(uid, courseId);
    }

    /**
     * 教师创建课件
     * @param courseWare 课件VO
     */
    @PostMapping("/create")
    public ResultVO<CourseWareVO> createCourseWare(@RequestBody CourseWareVO courseWare){
        return courseWareService.createCourseWare(courseWare);
    }

    /**
     * 教师更新课件
     * @param courseWare 课件VO
     */
    @PostMapping("/update")
    public ResultVO<CourseWareVO> updateCourseWare(@RequestBody CourseWareVO courseWare){
        return courseWareService.updateCourseWare(courseWare);
    }

    /**
     * 教师删除课件
     * @param courseWareId 课件Id
     */
    @PostMapping("/delete/{courseWareId}")
    public ResultVO<String> deleteCourseWare(@PathVariable Integer courseWareId){
        return courseWareService.deleteCourseWare(courseWareId);
    }
}
