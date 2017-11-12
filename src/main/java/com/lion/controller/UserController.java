package com.lion.controller;

import com.lion.entity.User;
import com.lion.entity.UserLoginLog;
import com.lion.service.UserLoginLogService;
import com.lion.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
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
            // 存在Session中,将在会话有效期内一直在服务器内存中维护这个值
            // 如果使用request.setAttribute再次添加将会导致session中值失效
            request.getSession().setAttribute("username", userName);
            request.getSession().setAttribute("userType",user.getUserType());
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
    @RequestMapping(value="userDetail")
    public String displayUserDetail(String username,HttpServletRequest request){
        User user=userService.getUserByUserName(username);
        request.setAttribute("user",user);
        return "user/userDetail";
    }

    //用户编辑个人信息页面
    @RequestMapping(value="editUser")
    public String editUser(String username,HttpServletRequest request){
        User user=userService.getUserByUserName(username);
        request.setAttribute("user",user);
        return "user/userEdit";
    }

    @RequestMapping(value="editUserInfo")
    public String editUserInfo(String username,
                               @RequestParam(value="title") String title,
                               @RequestParam(value="description") String description,
                               @RequestParam(value="detail") String detail,
                               @RequestParam(value = "image",required = false) MultipartFile image,
                               HttpServletRequest request,
                               RedirectAttributes redirectAttributes){
        //TODO
        return "redirect:/user/userDetail";

    }

    // 用户登录注销
    @RequestMapping(value = "/loginOut")
    public String loginOut(HttpServletRequest request) {
        request.getSession().removeAttribute("username");
        request.getSession().removeAttribute("userType");
        return "redirect:/index";
    }


}
