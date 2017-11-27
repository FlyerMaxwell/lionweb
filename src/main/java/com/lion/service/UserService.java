package com.lion.service;

import com.lion.entity.User;

import java.util.List;

/**
 * @author DJ
 * @date 2017/10/16.
 */
public interface UserService {

    List<User> listAllUser();

    List<User> listUserByRole(Integer role);

    User getUserByUserName(String name);

    User getUserByUserId(Long id);

    User getUserByEmail(String email);

    int deleteUser(Long id);

    void updateUserByUserId(User user);

    void addUser(User user);
}
