package cn.seecoder.courselearning.util;

/**
 * 定义一些常量
 */
public class Constant {
    // 一个展示课程的页面内包含的课程数量
    public static final Integer COURSE_PAGE_SIZE = 3;
    // 一个展示课件的页面内包含的课件数量
    public static final Integer COURSE_WARE_PAGE_SIZE = 10;
    // 请求操作成功返回码
    public static final Integer REQUEST_SUCCESS = 1;
    // 请求操作失败返回码
    public static final Integer REQUEST_FAIL = 0;
    // 订单已完成交易（支付并已发货）的状态码
    public static final Integer ORDER_STATUS_SUCCESS = 1;
    // 订单尚未支付的状态码
    public static final Integer ORDER_STATUS_UNPAID = 2;
    // 订单已支付等待发货的状态码
    public static final Integer ORDER_STATUS_WAIT = 3;
    // 订单已取消的状态码
    public static final Integer ORDER_STATUS_CANCEL = 4;
}
