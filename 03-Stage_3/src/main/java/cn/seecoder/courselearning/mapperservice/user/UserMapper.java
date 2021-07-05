package cn.seecoder.courselearning.mapperservice.user;

import cn.seecoder.courselearning.po.user.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    User selectByPrimaryKey(Integer id);

    List<User> selectAll();

    int updateByPrimaryKey(User record);

    void increaseBalance(@Param(value = "id") Integer id, @Param(value = "delta")Integer delta);

    void decreaseBalance(@Param(value = "id") Integer id, @Param(value = "delta")Integer delta);

    User selectByPhone(String phone);
}