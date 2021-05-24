package cn.seecoder.courselearning.service.order;

import cn.seecoder.courselearning.vo.order.RechargeOrderVO;
import cn.seecoder.courselearning.vo.ResultVO;

public interface RechargeService {
    // 账号充值
    ResultVO<RechargeOrderVO> rechargeAccount(RechargeOrderVO rechargeOrderVO);
}
