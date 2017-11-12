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

    //编辑用户页面
    @RequestMapping(value = "editMember")
    public String editMember(String username,HttpServletRequest request){
        User user=userService.getUserByUserName(username);
        request.setAttribute("user",user);
        return "member/memberEdit";
    }

    //编辑用户信息提交
    @RequestMapping(value ="editMemberInfo",method = RequestMethod.POST)
    public String editMemberInfo(String username,
                                 @RequestParam(value = "email") String userEmail,
                                 @RequestParam(value = "phone") String userPhone,
                                 @RequestParam(value = "gender") Integer userSex,
                                 @RequestParam(value = "type") Integer userType,
                                 @RequestParam(value = "state") Integer userState,
                                 @RequestParam(value = "description") String description,
                                 @RequestParam(value = "detail",required = false) String detail,
                                 @RequestParam(value = "image",required = false) MultipartFile image,
                                 HttpServletRequest request){
        User updateUser=userService.getUserByUserName(username);
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

        return "redirect:/member";
    }
}
