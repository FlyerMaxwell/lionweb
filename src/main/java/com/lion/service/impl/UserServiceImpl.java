package com.lion.service.impl;

import com.lion.dao.ext.UserDao;
import com.lion.entity.User;
import com.lion.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author DJ
 * @date 2017/9/26.
 */

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;


    public List<User> listAllUser() {
        return userDao.selectAllUser();
    }

    public User getUserByUserName(String name) {
        return userDao.selectByUserName(name);
    }

    public User getUserByUserId(Long id) {
        return userDao.selectByPrimaryKey(id);
    }

    public User getUserByEmail(String email) {
        return userDao.selectByEmail(email);
    }

    public int deleteUser(Long id) {
        return userDao.deleteByPrimaryKey(id);
    }

    public void updateUserByUserId(User user) {
        userDao.updateByPrimaryKeySelective(user);

    }

    public void addUser(User user) {
        userDao.insertSelective(user);
    }


}
