package com.lion.service;

import com.lion.entity.User;

import java.util.List;

/**
 * @author DJ
 * @date 2017/10/16.
 */
public interface UserService {

    List<User> listAllUser();

    List<User> listAllProfessor();

    List<User> listAllGraduate();

    List<User> listAllUndergraduate();

    List<User> listAllAlumni();

    User getUserByUserName(String name);

    User getUserByUserId(Long id);

    User getUserByEmail(String email);

    int deleteUser(Long id);

    void updateUserByUserId(User user);

    void addUser(User user);
}
