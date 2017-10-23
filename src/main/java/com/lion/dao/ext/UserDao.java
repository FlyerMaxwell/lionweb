package com.lion.dao.ext;

import com.lion.dao.gen.UserMapper;
import com.lion.entity.User;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author DJ
 * @date 2017/9/26.
 */
//不添加@Repository如何注入到bean?
@Repository
public interface UserDao extends UserMapper{
    public User findUserByUserName(String username);

    public User findUserByUserId(int id);

    public void addUser(User user);

    public void deleteUserByUserName(String username);

    public void updateUserByUserName(User user);

    public String getUserPasswordByUserName(String username);

    public List<User> getAllUserInfo();

    public User selectByUserName(String username);
}

