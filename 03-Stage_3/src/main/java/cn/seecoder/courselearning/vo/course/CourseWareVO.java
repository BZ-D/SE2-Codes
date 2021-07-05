package cn.seecoder.courselearning.vo.course;

import cn.seecoder.courselearning.po.course.CourseWare;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NonNull;

import java.util.Date;

@Data
public class CourseWareVO {
    private Integer id;

    private Integer courseId;

    private Integer number;

    private String title;

    private String fileName;

    private String fileType;

    private String fileSize;

    private Boolean freeFlag;

    private Boolean downloadFlag;

    private Boolean availableFlag;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date uploadTime;

    public CourseWareVO(){
    }

    public CourseWareVO(@NonNull CourseWare courseWare) {
        id = courseWare.getId();
        courseId = courseWare.getCourseId();
        number = courseWare.getNumber();
        title = courseWare.getTitle();
        fileName = courseWare.getFileName();
        fileType = courseWare.getFileType();
        fileSize = courseWare.getFileSize();
        freeFlag = courseWare.getFreeFlag();
        downloadFlag = courseWare.getDownloadFlag();
        availableFlag = freeFlag;
        uploadTime = courseWare.getUploadTime();
    }

    public CourseWareVO(@NonNull CourseWare courseWare, boolean isBought) {
        id = courseWare.getId();
        courseId = courseWare.getCourseId();
        number = courseWare.getNumber();
        title = courseWare.getTitle();
        fileName = courseWare.getFileName();
        fileType = courseWare.getFileType();
        fileSize = courseWare.getFileSize();
        freeFlag = courseWare.getFreeFlag();
        downloadFlag = courseWare.getDownloadFlag();
        availableFlag = isBought?true:freeFlag;
        uploadTime = courseWare.getUploadTime();
    }
}
