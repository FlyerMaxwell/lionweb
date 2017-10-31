package com.lion.controller;

import com.lion.entity.User;
import com.lion.entity.UserLoginLog;
import com.lion.service.UserLoginLogService;
import com.lion.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.Date;

/**
 * @author DJ
 * @date 2017/9/26.
 */

@Controller
@RequestMapping(value={"/user"})
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    UserLoginLogService loginLogService;


    @RequestMapping(value={""},method = {RequestMethod.GET})
    public String user(){
        return "user/user";
    }

    // 用户登录
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(User loginUser, HttpServletRequest request, RedirectAttributes redirect) {
        // 通过用户名查找User对象
        User user = userService.getUserByUserName(loginUser.getUserName());
        String password = "";
        if (user != null) {
            password = user.getPassword();
        }

        // 判断登录信息是否正确
        if (user != null && loginUser.getPassword().equals(password)) {

            // 获取登录基本信息
            String lastIp = request.getRemoteAddr();
            String userName = user.getUserName();
            Timestamp lastLoginTime = new Timestamp(new Date().getTime());

            // 更新用户信息
            user.setLastIp(lastIp);
            user.setLastLoginTime(lastLoginTime);
            userService.updateUserByUserId(user);

            // 更新用户登录日志
            UserLoginLog userLoginLog = new UserLoginLog();
            userLoginLog.setUserName(userName);
            userLoginLog.setLoginIp(lastIp);
            userLoginLog.setLoginDatetime(lastLoginTime);
            loginLogService.addUserLoginLog(userLoginLog);

            // 登陆成功，跳转到主页
            request.getSession().setAttribute("username", userName);
            return "redirect:/index";
        }

        // 登录失败，跳转页面
        request.setAttribute("Msg", "Login Fail!");
        return "error";
    }

    //显示用户profile界面
    @RequestMapping(value = "userProfile")
    public String displayUserProfile(String username,HttpServletRequest request){
        User user=userService.getUserByUserName(username);
        request.setAttribute("user",user);
        return "user/userProfile";
    }

    //显示用户信息
    @RequestMapping(value = "userInfo")
    public String displayUserInfo(String username,HttpServletRequest request){
        User user=userService.getUserByUserName(username);
        request.setAttribute("user",user);
        return "user/userInfo";
    }

    //编辑用户个人信息页面
    @RequestMapping(value = "editUser",method = RequestMethod.GET)
    public String updateUserInfoPage(String username, HttpServletRequest request){
        //TODO 用户名更改？
        User user=userService.getUserByUserName(username);
        request.setAttribute("user",user);
        return "user/userEdit";
    }

    //用户信息编辑提交
    @RequestMapping(value = "editUserInfo",method = RequestMethod.POST)
    public String updateUserInfo(User user,RedirectAttributes redirectAttributes){
        userService.updateUserByUserId(user);
        redirectAttributes.addAttribute("username", user.getUserName());
        return "redirect:/user/userInfo";
    }

    // 用户登录注销
    @RequestMapping(value = "/loginOut")
    public String loginOut(HttpServletRequest request) {
        request.getSession().removeAttribute("username");
        return "redirect:/index";
    }

    //添加Publications
//    @RequestMapping(value = "/addPublication",method = RequestMethod.POST)
//    public String insertPublication(User user,HttpServletRequest request){
//
//    }

}
