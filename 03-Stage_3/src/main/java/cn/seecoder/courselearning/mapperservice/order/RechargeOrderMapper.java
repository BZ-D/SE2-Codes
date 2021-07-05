package cn.seecoder.courselearning.mapperservice.order;

import cn.seecoder.courselearning.po.order.RechargeOrder;
import java.util.List;

public interface RechargeOrderMapper {
    int deleteByPrimaryKey(Integer orderId);

    int insert(RechargeOrder record);

    RechargeOrder selectByPrimaryKey(Integer orderId);

    List<RechargeOrder> selectAll();

    int updateByPrimaryKey(RechargeOrder record);
}