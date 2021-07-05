package cn.seecoder.courselearning.vo.user;

import cn.seecoder.courselearning.enums.UserRole;
import cn.seecoder.courselearning.po.user.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NonNull;

import java.util.Date;

@Data
public class UserVO {
    private Integer id;

    private String uname;

    private String phone;

    private String password;

    private String picture;

    private Integer balance;

    private UserRole userRole;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    public UserVO() {
    }

    public UserVO(@NonNull User user) {
        id = user.getId();
        uname = user.getUname();
        phone = user.getPhone();
        picture = user.getPicture();
        balance = user.getBalance();
        userRole = user.getUserRole();
        createTime = user.getCreateTime();
    }
}
