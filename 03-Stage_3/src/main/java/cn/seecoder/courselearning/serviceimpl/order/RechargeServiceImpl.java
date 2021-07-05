package cn.seecoder.courselearning.serviceimpl.order;

import cn.seecoder.courselearning.mapperservice.order.RechargeOrderMapper;
import cn.seecoder.courselearning.po.order.RechargeOrder;
import cn.seecoder.courselearning.service.order.RechargeService;
import cn.seecoder.courselearning.service.user.UserService;
import cn.seecoder.courselearning.util.Constant;
import cn.seecoder.courselearning.vo.order.RechargeOrderVO;
import cn.seecoder.courselearning.vo.ResultVO;
import cn.seecoder.courselearning.vo.user.UserVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class RechargeServiceImpl implements RechargeService {
    private static final Logger logger = LoggerFactory.getLogger(RechargeServiceImpl.class);

    @Resource
    UserService userService;

    @Resource
    RechargeOrderMapper rechargeOrderMapper;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public ResultVO<RechargeOrderVO> rechargeAccount(RechargeOrderVO rechargeOrderVO) {
        ResultVO<RechargeOrderVO> ret = new ResultVO<>();
        Integer userId = rechargeOrderVO.getUserId();
        if(userId == null || userId <= 0){
            ret.setCode(Constant.REQUEST_FAIL);
            ret.setMsg("userId 参数无效");
        }else{
            UserVO user = userService.getUser(userId);
            if(user != null){
                try{
                    rechargeOrderVO.setCreateTime(new Date());
                    Integer value = rechargeOrderVO.getValue();
                    // 更新用户余额
                    userService.increaseBalance(userId, value);
                    RechargeOrder rechargeOrder = new RechargeOrder(rechargeOrderVO);
                    if(rechargeOrderMapper.insert(rechargeOrder) > 0){
                        ret.setCode(Constant.REQUEST_SUCCESS);
                        ret.setMsg("充值成功");
                        ret.setData(new RechargeOrderVO(rechargeOrder));
                    }else{
                        ret.setCode(Constant.REQUEST_FAIL);
                        ret.setMsg("系统错误");
                    }
                }catch (Exception e){
                    ret.setCode(Constant.REQUEST_FAIL);
                    ret.setMsg("服务器数据库系统出错，请咨询系统管理员");
                    logger.error("用户充值账号时出错", e);
                }
            }
        }
        return ret;
    }
}
