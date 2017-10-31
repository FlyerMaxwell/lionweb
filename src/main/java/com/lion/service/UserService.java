package com.lion.service;

import com.lion.entity.User;

import java.util.List;

/**
 * @author DJ
 * @date 2017/10/16.
 */
public interface UserService {

    List<User> listAllUser();

    User getUserByUserName(String name);

    void updateUserByUserId(User user);

    void addUser(User user);
}
