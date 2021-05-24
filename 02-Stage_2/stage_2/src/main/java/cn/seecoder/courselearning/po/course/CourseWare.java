package cn.seecoder.courselearning.po.course;

import cn.seecoder.courselearning.vo.course.CourseWareVO;
import lombok.NonNull;

import java.util.Date;

public class CourseWare {
    private Integer id;

    private Integer courseId;

    private Integer number;

    private String title;

    private String fileName;

    private String fileType;

    private String fileSize;

    private Boolean freeFlag;

    private Boolean downloadFlag;

    private Date uploadTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName == null ? null : fileName.trim();
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType == null ? null : fileType.trim();
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize == null ? null : fileSize.trim();
    }

    public Boolean getFreeFlag() {
        return freeFlag;
    }

    public void setFreeFlag(Boolean freeFlag) {
        this.freeFlag = freeFlag;
    }

    public Boolean getDownloadFlag() {
        return downloadFlag;
    }

    public void setDownloadFlag(Boolean downloadFlag) {
        this.downloadFlag = downloadFlag;
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    public CourseWare() {
    }
    
    public CourseWare(@NonNull CourseWareVO courseWareVO){
        id = courseWareVO.getId();
        courseId = courseWareVO.getCourseId();
        number = courseWareVO.getNumber();
        title = courseWareVO.getTitle();
        fileName = courseWareVO.getFileName();
        fileType = courseWareVO.getFileType();
        fileSize = courseWareVO.getFileSize();
        freeFlag = courseWareVO.getFreeFlag();
        downloadFlag = courseWareVO.getDownloadFlag();
        uploadTime = courseWareVO.getUploadTime();
    }
}