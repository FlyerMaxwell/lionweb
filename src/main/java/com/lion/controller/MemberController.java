package com.lion.controller;

import com.lion.entity.User;
import com.lion.service.UserService;
import com.lion.util.FileHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author DJ
 * @date 2017/11/1.
 */
@Controller
@RequestMapping(value = "/member")
public class MemberController {

    @Autowired
    UserService userService;

    //显示所有用户信息
    @RequestMapping(value = "")
    public String memberPage(HttpServletRequest request){
        List<User> users=userService.listAllUser();
        request.setAttribute("users",users);
        return "member/memberInfo";
    }

    //显示用户详细信息
    @RequestMapping(value = "memberDetail")
    public String memberDetail(String username,HttpServletRequest request){
        User user=userService.getUserByUserName(username);
        request.setAttribute("user",user);
        return "member/memberDetail";
    }
}
