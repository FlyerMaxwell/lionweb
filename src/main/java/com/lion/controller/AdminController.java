package com.lion.controller;

import com.lion.entity.ProjectUser;
import com.lion.entity.User;
import com.lion.entity.UserLoginLog;
import com.lion.service.ProjectUserService;
import com.lion.service.PublicationUserService;
import com.lion.service.UserLoginLogService;
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
    PublicationUserService publicationUserService;

    @Autowired
    ProjectUserService projectUserService;

    @Autowired
    UserLoginLogService userLoginLogService;

    @RequestMapping(value="memberInfo",method = RequestMethod.GET)
    public String memberInfo(HttpServletRequest request){
        List<User> users=userService.listAllUser();
        request.setAttribute("users",users);
        return "admin/memberInfo";
    }

    @RequestMapping(value="addMember",method = RequestMethod.GET)
    public String addMember(){
        return "admin/memberAdd";
    }

    @RequestMapping(value="addMemberInfo",method = RequestMethod.POST)
    public String addMemberInfo(String adminName,
                                @RequestParam(value = "username") String username,
                                @RequestParam(value = "email") String userEmail,
                                @RequestParam(value = "phone") String userPhone,
                                @RequestParam(value = "gender") Integer userSex,
                                @RequestParam(value = "type") Integer userType,
                                @RequestParam(value = "state") Integer userState,
                                @RequestParam(value = "description") String description,
                                @RequestParam(value = "detail",required = false) String detail,
                                @RequestParam(value = "image",required = false) MultipartFile image,
                                HttpServletRequest request,
                                RedirectAttributes redirectAttributes){
        User newUser=new User();

        if(newUser!=null) {
            try{
                String ip=request.getRemoteAddr();

                //判断用户名是否已注册，未注册则进入后续流程
                if(userService.getUserByUserName(username)==null){
                    //判断邮箱是否注册
                    if(userService.getUserByEmail(userEmail)==null) {
                        //添加用户
                        //TODO 路径配置
                        String basePath = "D:/lion/member";
                        try {
                            if (image != null && !image.isEmpty()) {
                                String filePath1 = FileHandler.uploadFile(basePath + "/image", image, request);
                                newUser.setImageUrl(filePath1);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            request.setAttribute("Msg", "File Upload Failed!");
                            return "error";
                        }
                        User admin=userService.getUserByUserName(adminName);
                        newUser.setUserName(username);
                        newUser.setAdminId(admin.getAdminId());
                        newUser.setAdminName(adminName);
                        newUser.setUserEmail(userEmail);
                        newUser.setUserSex(userSex);
                        newUser.setUserPhone(userPhone);
                        newUser.setUserType(userType);
                        newUser.setUserState(userState);
                        newUser.setDescription(description);
                        if(detail!=null){
                            newUser.setDetail(detail);
                        }


                        newUser.setLastIp(ip);
                        Timestamp createTime = new Timestamp(new Date().getTime());
                        newUser.setCreateTime(createTime);
                        newUser.setLastLoginTime(createTime);
                        userService.addUser(newUser);

                        //添加用户登录日志
                        UserLoginLog userLoginLog = new UserLoginLog();
                        userLoginLog.setUserName(username);
                        userLoginLog.setLoginDatetime(createTime);
                        userLoginLog.setLoginIp(ip);
                        userLoginLogService.addUserLoginLog(userLoginLog);
                        redirectAttributes.addAttribute("admin", adminName);
                        return "redirect:/admin/memberInfo";
                    }else{
                        request.setAttribute("Msg","Email address has been registered!");
                        return "error";
                    }
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

    @RequestMapping(value="editMember",method = RequestMethod.GET)
    public String editMember(Long id,HttpServletRequest request){
        User user=userService.getUserByUserId(id);
        request.setAttribute("user",user);
        return "admin/memberEdit";
    }

    @RequestMapping(value="editMemberInfo",method = RequestMethod.POST)
    public String editMemberInfo(String adminName,Long id,
                                 @RequestParam(value = "email") String userEmail,
                                 @RequestParam(value = "phone") String userPhone,
                                 @RequestParam(value = "gender") Integer userSex,
                                 @RequestParam(value = "type") Integer userType,
                                 @RequestParam(value = "state") Integer userState,
                                 @RequestParam(value = "description") String description,
                                 @RequestParam(value = "detail",required = false) String detail,
                                 @RequestParam(value = "image",required = false) MultipartFile image,
                                 HttpServletRequest request){
        User updateUser=userService.getUserByUserId(id);
        //TODO 路径配置
        String basePath = "D:/lion/members";
        try {
            if (image != null && !image.isEmpty()) {
                FileHandler.deleteFile(updateUser.getImageUrl());
                String filePath1 = FileHandler.uploadFile(basePath + "/image", image, request);
                updateUser.setImageUrl(filePath1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("Msg", "File Upload Failed!");
            return "error";
        }
        updateUser.setUserEmail(userEmail);
        updateUser.setUserPhone(userPhone);
        updateUser.setUserSex(userSex);
        updateUser.setUserType(userType);
        updateUser.setUserState(userState);
        updateUser.setDescription(description);
        updateUser.setDetail(detail);
        userService.updateUserByUserId(updateUser);

        return "redirect:/admin/memberInfo";
    }

    //删除member
    @RequestMapping(value = "deleteMemberInfo",method = RequestMethod.GET)
    public String deleteMemberInfo(@RequestParam(value = "admin") String adminName, Long id,
                                   HttpServletRequest request){
        User admin=userService.getUserByUserName(adminName);
        User user=userService.getUserByUserId(id);
        if(admin.getId()!=id) {
            FileHandler.deleteFile(user.getImageUrl());
            userService.deleteUser(id);
            publicationUserService.deleteRecordByUserId(id);
            projectUserService.deleteRecordByUserId(id);
            return "redirect:/admin/memberInfo";
        }
        request.setAttribute("Msg","You can't delete yourself!");
        return "error";
    }

    //member详情
    @RequestMapping(value = "memberDetail",method = RequestMethod.GET)
    public String memberDetail(Long id,HttpServletRequest request){
        User user=userService.getUserByUserId(id);
        request.setAttribute("user",user);
        return "admin/memberDetail";
    }
}
