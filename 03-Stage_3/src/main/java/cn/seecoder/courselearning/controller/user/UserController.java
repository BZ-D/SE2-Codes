package cn.seecoder.courselearning.controller.user;

import cn.seecoder.courselearning.service.user.UserService;
import cn.seecoder.courselearning.vo.ResultVO;
import cn.seecoder.courselearning.vo.user.UserFormVO;
import cn.seecoder.courselearning.vo.user.UserVO;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    @PostMapping("/register")
    public ResultVO<UserVO> register(@RequestBody UserVO user){
        return userService.userRegister(user);
    }

    @PostMapping("/login")
    public ResultVO<UserVO> login(@RequestBody UserFormVO userForm){
        return userService.userLogin(userForm.getPhone(), userForm.getPassword());
    }

    @GetMapping("/{uid}")
    public UserVO getUser(@PathVariable Integer uid){
        return userService.getUser(uid);
    }

//    @PostMapping("/recharge")
//    public ResultVO<UserVO> recharge(@RequestBody RechargeOrderVO rechargeOrderVO){
//        return userService.rechargeAccount(rechargeOrderVO);
//    }
}
