package com.lion.controller;

import com.lion.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author DJ
 * @date 2017/9/26.
 */

@Controller
@RequestMapping(path={"/user"})
public class LoginController {

//    @Autowired
//    UserService userService;
    @RequestMapping(path={"/reg"},method = {RequestMethod.GET})
    public String reg(){
        return "";
    }

    @RequestMapping(path={"/login"},method = {RequestMethod.GET})
    public String login(){
        return "user/login";
    }

}
