package cn.seecoder.courselearning.vo.order;

import cn.seecoder.courselearning.po.order.CourseOrder;
import cn.seecoder.courselearning.vo.coupon.CouponVO;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NonNull;

import java.util.Date;
import java.util.List;

@Data
public class CourseOrderVO {
    private Integer id;

    // 课程原价
    private Integer origin;

    // 实际支付金额
    private Integer cost;

    private Integer courseId;

    private String courseName;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    private Integer userId;

    private Integer status;

    // 目前已经使用的优惠券
    private List<CouponVO> usedCoupons;

    public CourseOrderVO() {
    }

    public CourseOrderVO(@NonNull CourseOrder order) {
        id = order.getId();
        origin = order.getOrigin();
        cost = order.getCost();
        courseId = order.getCourseId();
        courseName = order.getCourseName();
        createTime = order.getCreateTime();
        userId = order.getUserId();
        status = order.getStatus();
    }
}
