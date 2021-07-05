package cn.seecoder.courselearning.po.course;

import cn.seecoder.courselearning.vo.course.CourseVO;
import lombok.NonNull;

import java.util.Date;

public class Course {
    private Integer id;

    private String name;

    private String type;

    private String intro;

    private String picture;

    private String school;

    private Date createTime;

    private Date deleteTime;

    private Integer cost;

    private Integer teacherId;

    private String teacherName;

    // 课程点赞数
    private Integer likes;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro == null ? null : intro.trim();
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture == null ? null : picture.trim();
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school == null ? null : school.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getDeleteTime() {
        return deleteTime;
    }

    public void setDeleteTime(Date deleteTime) {
        this.deleteTime = deleteTime;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName == null ? null : teacherName.trim();
    }

    public Course() {
    }

    public Course(@NonNull CourseVO courseVO){
        this.id = courseVO.getId();
        this.name = courseVO.getName();
        this.type = courseVO.getType();
        this.intro = courseVO.getIntro();
        this.picture = courseVO.getPicture();
        this.school = courseVO.getSchool();
        this.createTime = courseVO.getCreateTime();
        this.cost = courseVO.getCost();
        this.teacherId = courseVO.getTeacherId();
        this.teacherName = courseVO.getTeacherName();
    }
}