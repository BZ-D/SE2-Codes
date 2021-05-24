package cn.seecoder.courselearning.serviceimpl.user;

import cn.seecoder.courselearning.mapperservice.user.UserMapper;
import cn.seecoder.courselearning.po.user.User;
import cn.seecoder.courselearning.service.user.UserService;
import cn.seecoder.courselearning.util.Constant;
import cn.seecoder.courselearning.vo.ResultVO;
import cn.seecoder.courselearning.vo.user.UserVO;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;


@Service
public class UserServiceImpl implements UserService {
    @Resource
    UserMapper userMapper;

    @Override
    public ResultVO<UserVO> userRegister(UserVO vo) {
        String phone = vo.getPhone();
        String uname = vo.getUname();
        String password = vo.getPassword();
        if(userMapper.selectByPhone(phone) == null){
            if(StringUtils.hasText(uname) && StringUtils.hasText(password)){
                vo.setCreateTime(new Date());
                vo.setBalance(0);
                User user = new User(vo);
                userMapper.insert(user);
                System.out.println(new UserVO(user));
                return new ResultVO<>(Constant.REQUEST_SUCCESS, "账号注册成功！", new UserVO(user));
            }else{
                return new ResultVO<>(Constant.REQUEST_FAIL, "用户名或密码不得为空！");
            }
        }
        return new ResultVO<>(Constant.REQUEST_FAIL, "这个手机号已经注册过账号。");
    }

    @Override
    public ResultVO<UserVO> userLogin(String phone, String password) {
        User user = userMapper.selectByPhone(phone);
        if(user == null){
            return new ResultVO<>(Constant.REQUEST_FAIL, "这个手机号尚未注册过账号。");
        }else{
            if(!user.getPassword().equals(password))
                return new ResultVO<>(Constant.REQUEST_FAIL, "账号或密码错误！");
        }
        return new ResultVO<>(Constant.REQUEST_SUCCESS, "账号登陆成功！", new UserVO(userMapper.selectByPhone(phone)));
    }

    @Override
    public UserVO getUser(Integer uid) {
        User userFromDB = userMapper.selectByPrimaryKey(uid);
        if(userFromDB == null){
            return new UserVO();
        }else{
            return new UserVO(userFromDB);
        }
    }

    @Override
    public void increaseBalance(Integer id, Integer delta) {
        userMapper.increaseBalance(id, delta);
    }

    @Override
    public void decreaseBalance(Integer id, Integer delta) {
        userMapper.decreaseBalance(id, delta);
    }

    @Override
    public List<User> getAll() {
        return userMapper.selectAll();
    }
}
