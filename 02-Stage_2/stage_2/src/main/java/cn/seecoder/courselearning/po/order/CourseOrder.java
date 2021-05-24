package cn.seecoder.courselearning.po.order;

import cn.seecoder.courselearning.vo.order.CourseOrderVO;
import lombok.NonNull;

import java.util.Date;

public class CourseOrder {
    private Integer id;

    private Integer origin;

    private Integer cost;

    private Integer courseId;

    private String courseName;

    private Date createTime;

    private Integer userId;

    private Integer status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrigin() {
        return origin;
    }

    public void setOrigin(Integer origin) {
        this.origin = origin;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName == null ? null : courseName.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public CourseOrder() {
    }

    public CourseOrder(@NonNull CourseOrderVO orderVO){
        this.id = orderVO.getId();
        this.origin = orderVO.getOrigin();
        this.cost = orderVO.getCost();
        this.courseId = orderVO.getCourseId();
        this.courseName = orderVO.getCourseName();
        this.createTime = orderVO.getCreateTime()==null ? new Date():orderVO.getCreateTime();
        this.userId = orderVO.getUserId();
        this.status = orderVO.getStatus();
    }
}