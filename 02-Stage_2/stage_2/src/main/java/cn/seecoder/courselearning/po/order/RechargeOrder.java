package cn.seecoder.courselearning.po.order;

import cn.seecoder.courselearning.vo.order.RechargeOrderVO;

import java.util.Date;

public class RechargeOrder {
    private Integer orderId;

    private Integer userId;

    private Integer value;

    private Date createTime;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public RechargeOrder() {
    }

    public RechargeOrder(RechargeOrderVO rechargeOrder) {
        this.orderId = rechargeOrder.getOrderId();
        this.userId = rechargeOrder.getUserId();
        this.value = rechargeOrder.getValue();
        this.createTime = rechargeOrder.getCreateTime();
    }
}