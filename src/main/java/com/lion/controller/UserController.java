package com.lion.controller;

import com.lion.entity.Project;
import com.lion.entity.Publication;
import com.lion.entity.User;
import com.lion.entity.UserLoginLog;
import com.lion.service.ProjectService;
import com.lion.service.PublicationService;
import com.lion.service.UserLoginLogService;
import com.lion.service.UserService;
import com.lion.util.FileHandler;
import com.lion.util.TextHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * @author DJ
 * @date 2017/9/26.
 */

@Controller
@RequestMapping(value = {"/user"})
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    PublicationService publicationService;
    @Autowired
    ProjectService projectService;
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
        if (user != null &&loginUser.getUserName().equals(user.getUserName())&&loginUser.getPassword().equals(password)) {

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
            request.getSession().setAttribute("userType", user.getUserType());
            return "redirect:/index";
        }

        // 登录失败，跳转页面
        request.setAttribute("Msg", "Login Fail(Incorrect username/password)!");
        return "error";
    }

    //显示用户profile界面
    @RequestMapping(value = "userProfile")
    public String displayUserProfile(String username, HttpServletRequest request) {
        User user = userService.getUserByUserName(username);
        request.setAttribute("user", user);
        List<Publication> publications=publicationService.listPublicationByUserId(user.getId());
        request.setAttribute("publications",publications);
        List<Project> projects=projectService.listProjectByUserId(user.getId());
        request.setAttribute("projects",projects);
        return "user/userProfile";
    }

    //用户编辑个人信息页面
    @RequestMapping(value = "editUser")
    public String editUser(String username, HttpServletRequest request) {
        User user = userService.getUserByUserName(username);
        request.setAttribute("user", user);
        return "user/userEdit";
    }

    //编辑用户信息提交
    @RequestMapping(value = "editUserInfo", method = RequestMethod.POST)
    public String editUserInfo(String username,
                               @RequestParam(value = "realName") String realName,
                               @RequestParam(value = "email") String userEmail,
                               @RequestParam(value = "phone",required = false) String userPhone,
                               @RequestParam(value = "description",required=false) String description,
                               @RequestParam(value = "gender") Integer userSex,
                               @RequestParam(value = "role") Integer userRole,
                               @RequestParam(value = "detail",required = false) String detail,
                               @RequestParam(value = "web",required = false) String web,
                               @RequestParam(value = "prospect",required = false) String prospect,
                               @RequestParam(value = "cv",required = false) MultipartFile cv,
                               HttpServletRequest request, RedirectAttributes redirectAttributes) {
        if(realName.trim().length()==0||userEmail.trim().length()==0||description.trim().length()==0
                ||userSex==null||userRole==null||detail.trim().length()==0){
            request.setAttribute("Msg","You should fill in all fields with *!");
            return "error";
        }
        User oldUser=userService.getUserByEmail(userEmail);
        if(oldUser!=null&&!oldUser.getUserName().equals(username)){
            request.setAttribute("Msg","Email address has been registered!");
            return "error";
        }
        User updateUser = userService.getUserByUserName(username);


        //TODO 路径配置
        String basePath = "D:/lion/members";
        try {
            if (cv != null && !cv.isEmpty()) {
                FileHandler.deleteFile(updateUser.getCvUrl());
                String filePath1 = FileHandler.uploadFile(basePath + "/"+username+"/cv", cv, request);
                updateUser.setCvUrl(filePath1);

            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("Msg", "File Upload Failed!");
            return "error";
        }
        updateUser.setRealName(realName);
        updateUser.setUserEmail(userEmail);
        updateUser.setUserPhone(userPhone);
        updateUser.setUserSex(userSex);
        updateUser.setUserRole(userRole);
        updateUser.setDescription(TextHandler.toHtml(description));
        updateUser.setDetail(TextHandler.toHtml(detail));
        updateUser.setWebUrl(web);
        updateUser.setProspect(TextHandler.toHtml(prospect));
        userService.updateUserByUserId(updateUser);

        redirectAttributes.addAttribute("username", username);
        return "redirect:/user/userProfile";
    }

    //显示照片修改页面
    @RequestMapping(value = "/editPhoto")
    public String editPhoto(String username,HttpServletRequest request){
        User user=userService.getUserByUserName(username);
        request.setAttribute("imgUrl",user.getImageUrl());
        return "user/photoEdit";
    }

    //提交照片修改
    @RequestMapping(value = "/editPhotoInfo",method = RequestMethod.POST)
    public String editPhotoInfo(String username,
                                @RequestParam(value = "image") MultipartFile image,
                                HttpServletRequest request,
                                RedirectAttributes redirectAttributes){
        User updateUser=userService.getUserByUserName(username);
        String basePath = "D:/lion/members";
        try {
            if (image != null && !image.isEmpty()) {
                FileHandler.deleteFile(updateUser.getImageUrl());
                String filePath1 = FileHandler.uploadFile(basePath + "/"+username+"/image", image, request);
                updateUser.setImageUrl(filePath1);

            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("Msg", "File Upload Failed!");
            return "error";
        }
        userService.updateUserByUserId(updateUser);
        redirectAttributes.addAttribute("username", username);
        return "redirect:/user/userProfile";
    }

    //显示密码修改页面
    @RequestMapping(value = "/editPassword")
    public String editPassword(String username,HttpServletRequest request){
        User user=userService.getUserByUserName(username);
        request.setAttribute("user",user);
        return "user/passwordEdit";
    }

    //删除照片
    @RequestMapping(value = "/deletePhotoInfo")
    public String deletePhotoInfo(String username,RedirectAttributes redirectAttributes){
        User user=userService.getUserByUserName(username);
        if(user.getImageUrl().trim().length()!=0){
            FileHandler.deleteFile(user.getImageUrl());
            user.setImageUrl("");
            userService.updateUserByUserId(user);
        }
        redirectAttributes.addAttribute("username",username);
        return "redirect:/user/userProfile";
    }

    //删除CV
    @RequestMapping(value = "/deleteCVInfo")
    public String deleteCVInfo(String username,RedirectAttributes redirectAttributes){
        User user=userService.getUserByUserName(username);
        if(user.getCvUrl().trim().length()==0){
            FileHandler.deleteFile(user.getCvUrl());
            user.setCvUrl("");
            userService.updateUserByUserId(user);
        }
        redirectAttributes.addAttribute("username",username);
        return "redirect:/user/userProfile";
    }

    //提交密码修改
    @RequestMapping(value = "/editPasswordInfo",method = RequestMethod.POST)
    public String editPasswordInfo(String username,String oldPass,String newPass0,String newPass1,
            HttpServletRequest request,RedirectAttributes redirectAttributes){
        User user=userService.getUserByUserName(username);
        if(!(oldPass.trim().equals(user.getPassword()))){
            request.setAttribute("Msg","Please input correct old password!");
            return "error";
        }
        if(!(newPass0.trim().equals(newPass1.trim()))){
            request.setAttribute("Msg","Two inputs of new password is inconsistent!");
            return "error";
        }
        user.setPassword(newPass0.trim());
        userService.updateUserByUserId(user);
        redirectAttributes.addAttribute("username", username);
        return "redirect:/user/userProfile";
    }

    // 用户登录注销
    @RequestMapping(value = "/loginOut")
    public String loginOut(HttpServletRequest request) {
        request.getSession().removeAttribute("username");
        request.getSession().removeAttribute("userType");
        return "redirect:/index";
    }


}
