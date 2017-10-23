package com.lion.service;

import com.lion.entity.User;

/**
 * @author DJ
 * @date 2017/10/16.
 */
public interface UserService {

    User getUserByUserName(String name);

    void updateUserByUserId(User user);

    void addUser(User user);
}
