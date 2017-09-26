package com.lion.web.controller;

import com.lion.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author DJ
 * @date 2017/9/26.
 */

@RequestMapping(value="/user")
public class LoginController {

    @Autowired
    UserService userService;

    @RequestMapping(value="/reg",method = {RequestMethod.POST})
    public String register(){
        return "";
    }
}
