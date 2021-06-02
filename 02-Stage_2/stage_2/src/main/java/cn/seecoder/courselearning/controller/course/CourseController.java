package cn.seecoder.courselearning.controller.course;

import cn.seecoder.courselearning.service.course.CourseService;
import cn.seecoder.courselearning.util.Constant;
import cn.seecoder.courselearning.vo.course.CourseVO;
import cn.seecoder.courselearning.vo.ResultVO;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/course")
public class CourseController {
    @Resource
    private CourseService courseService;

    /**
     * 查询单一课程
     * @param uid 用户id
     * @param courseId 课程id
     */
    @GetMapping("/{courseId}")
    public CourseVO getCourseById(@RequestParam(required = false, defaultValue = "") Integer uid, @PathVariable Integer courseId) {
        return courseService.getCourse(courseId, uid);
    }

    /**
     * 获取课程列表
     * 如果带参数uid，则需要判断该用户是否购买过课程
     * 如果带参数key，则根据关键字返回分页查询结果
     * @param uid 用户id
     * @param key 关键字
     */
    @GetMapping("/all/{page}")
    public PageInfo<CourseVO> getCoursesByKey(@RequestParam(required = false, defaultValue = "") Integer uid,
                                                @RequestParam(required = false, defaultValue = "") String key,
                                                @PathVariable Integer page) {
        return courseService.getCourses(page, Constant.COURSE_PAGE_SIZE, uid, key);
    }

    /**
     * 根据分类标签，获取课程列表
     * 如果带参数uid，则需要判断该用户是否购买过课程、该用户是否为课程的教师
     * @param uid 用户id
     * @param type 课程类别
     */
    @GetMapping("/type/{page}")
    public PageInfo<CourseVO> getCoursesByType(@RequestParam(required = false, defaultValue = "")  Integer uid,
                                         @RequestParam String type,
                                         @PathVariable Integer page) {
        return courseService.getCoursesByType(page, Constant.COURSE_PAGE_SIZE, uid, type);
    }

    /**
     * 分页获取热门课程列表
     * @param page 页码数
     */
    @GetMapping("/hot/{page}")
    public PageInfo<CourseVO> getHotCourses(@PathVariable Integer page, Integer uid) {
        return courseService.getHotCourses(page, Constant.COURSE_PAGE_SIZE,uid);
    }

    /**
     * 获取用户已购买的课程列表
     * @param sid 学生id
     */
    @GetMapping("/sid/{sid}")
    public List<CourseVO> getBoughtCourses(@PathVariable Integer sid) {
        return courseService.getBoughtCourses(sid);
    }

    /**
     * 获取教师可管理的课程列表
     * @param tid 教师id
     */
    @GetMapping("/tid/{tid}")
    public List<CourseVO> getManageableCourses(@PathVariable Integer tid) {
        return courseService.getManageableCourses(tid);
    }

    /**
     * 教师创建课程
     * @param course 课程VO
     */
    @PostMapping("/create")
    public ResultVO<CourseVO> createCourse(@RequestBody CourseVO course) {
        return courseService.createCourse(course);
    }

    /**
     * 用户点赞课程
     * @param uid 用户id
     * @param courseId 课程id
     */
    @PostMapping("/like/{courseId}")
    public ResultVO<CourseVO> setCourseLike(@RequestParam Integer uid, @PathVariable Integer courseId) {
        return courseService.setCourseLike(uid, courseId);
    }

    /**
     * 用户取消点赞课程
     * @param uid 用户id
     * @param courseId 课程id
     */
    @PostMapping("/cancel_like/{courseId}")
    public ResultVO<CourseVO> cancelCourseLike(@RequestParam Integer uid, @PathVariable Integer courseId) {
        return courseService.cancelCourseLike(uid, courseId);
    }
}
