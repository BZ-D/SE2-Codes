package cn.seecoder.courselearning.vo.order;

import cn.seecoder.courselearning.po.order.RechargeOrder;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class RechargeOrderVO {
    private Integer orderId;

    private Integer userId;

    private Integer value;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    public RechargeOrderVO() {
    }

    public RechargeOrderVO(RechargeOrder rechargeOrder) {
        this.orderId = rechargeOrder.getOrderId();
        this.userId = rechargeOrder.getUserId();
        this.value = rechargeOrder.getValue();
        this.createTime = rechargeOrder.getCreateTime();
    }
}