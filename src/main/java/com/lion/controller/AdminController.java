package com.lion.controller;

import com.lion.entity.User;
import com.lion.entity.UserLoginLog;
import com.lion.service.UserLoginLogService;
import com.lion.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.Date;

/**
 * @author DJ
 * @date 2017/10/18.
 */
@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    @Autowired
    UserService userService;

    @Autowired
    UserLoginLogService userLoginLogService;

    @RequestMapping(value = "addUser",method = RequestMethod.POST)
    public String addUser(User newUser, HttpServletRequest request){
        User user=newUser;
        if(user!=null){
            try{
                String userName=user.getUserName();
                String ip=request.getRemoteAddr();

                //判断用户名是否已注册,未注册则进入后续流程
                if(userService.getUserByUserName(userName)==null) {

                    //添加用户
                    user.setLastIp(ip);
                    Timestamp createTime = new Timestamp(new Date().getTime());
                    user.setCreateTime(createTime); //字段default值为now()有类似功能
                    user.setLastLoginTime(createTime);
                    userService.addUser(user);

                    //添加登录日志
                    UserLoginLog userLoginLog = new UserLoginLog();
                    userLoginLog.setUserName(userName);
                    userLoginLog.setLoginIp(ip);
                    userLoginLog.setLoginDatetime(createTime);
                    userLoginLogService.addUserLoginLog(userLoginLog);

                    //注册成功跳转到主界面
                    request.setAttribute("username", userName);
                    return "redirect:/index";

                }else{
                    request.setAttribute("Msg","用户名已被占用!");
                    return "error";
                }
            }catch (Exception e){
                e.printStackTrace();
                request.setAttribute("Msg","发生未知错误！");
                return "error";
            }
        }
        request.setAttribute("Msg","未传入有效用户信息");
        return "error";
    }

}
