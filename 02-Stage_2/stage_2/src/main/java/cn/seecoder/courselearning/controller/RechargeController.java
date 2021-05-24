package cn.seecoder.courselearning.controller;

import cn.seecoder.courselearning.service.order.RechargeService;
import cn.seecoder.courselearning.vo.order.RechargeOrderVO;
import cn.seecoder.courselearning.vo.ResultVO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/recharge")
public class RechargeController {
    @Resource
    RechargeService rechargeService;

    @PostMapping("")
    public ResultVO<RechargeOrderVO> recharge(@RequestBody RechargeOrderVO rechargeOrderVO){
        return rechargeService.rechargeAccount(rechargeOrderVO);
    }
}
