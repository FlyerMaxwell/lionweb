package com.lion.controller;

import com.lion.entity.User;
import com.lion.entity.UserLoginLog;
import com.lion.service.UserLoginLogService;
import com.lion.service.UserService;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

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

    @RequestMapping(value="memberInfo",method = RequestMethod.GET)
    public String memberInfo(String admin,HttpServletRequest request){
        List<User> users=userService.listAllUser();
        request.setAttribute("users",users);
        request.setAttribute("admin",admin);
        return "admin/memberInfo";
    }

    @RequestMapping(value="memberAdd",method = RequestMethod.GET)
    public String memberAdd(String admin,HttpServletRequest request){
        request.setAttribute("admin",admin);
        return "admin/memberAdd";
    }

    @RequestMapping(value="memberAddInfo",method = RequestMethod.POST)
    public String memberAddInfo(String adminName, String userName, String userEmail,
                                Integer userSex, String userPhone, Integer userType,
                                Integer userState, HttpServletRequest request,
                                RedirectAttributes redirectAttributes){
        User newUser=new User();
        User admin=userService.getUserByUserName(adminName);
        newUser.setUserName(userName);
        newUser.setAdminId(admin.getAdminId());
        newUser.setAdminName(adminName);
        newUser.setUserEmail(userEmail);
        newUser.setUserSex(userSex);
        newUser.setUserPhone(userPhone);
        newUser.setUserType(userType);
        newUser.setUserState(userState);
        if(newUser!=null) {
            try{
                String ip=request.getRemoteAddr();

                //判断用户名是否已注册，未注册则进入后续流程
                if(userService.getUserByUserName(newUser.getUserName())==null){
                    //添加用户
                    newUser.setLastIp(ip);
                    Timestamp createTime=new Timestamp(new Date().getTime());
                    newUser.setCreateTime(createTime);
                    newUser.setLastLoginTime(createTime);
                    userService.addUser(newUser);

                    //添加用户登录日志
                    UserLoginLog userLoginLog=new UserLoginLog();
                    userLoginLog.setUserName(userName);
                    userLoginLog.setLoginDatetime(createTime);
                    userLoginLog.setLoginIp(ip);
                    userLoginLogService.addUserLoginLog(userLoginLog);

                    redirectAttributes.addAttribute("admin",adminName);
                    return "redirect:/admin/memberInfo";
                }else{
                    request.setAttribute("Msg","Username has been registered!");
                    return "error";
                }
            }catch (Exception e){
                e.printStackTrace();
                request.setAttribute("Msg","Unknown Error!");
                return "error";
            }
        }
        request.setAttribute("Msg","Null User Info!");
        return "error";
    }
}
