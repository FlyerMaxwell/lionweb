package com.lion.controller;

import com.lion.constant.ConfigConstant;
import com.lion.constant.UserConstant;
import com.lion.entity.*;
import com.lion.service.*;
import com.lion.util.FileHandler;
import com.lion.util.TextHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.Date;
import java.util.LinkedList;
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
    ProjectPublicationService projectPublicationService;

    @Autowired
    UserLoginLogService userLoginLogService;

    @Autowired
    NewsService newsService;

    @Autowired
    ProjectService projectService;

    @Autowired
    PublicationService publicationService;

    @Autowired
    LabelService labelService;

    @RequestMapping(value="memberInfo",method = RequestMethod.GET)
    public String memberInfo(HttpServletRequest request){
        List<User> professorList=userService.listUserByRole(UserConstant.PROFESSOR);
        List<User> graduateList=userService.listUserByRole(UserConstant.GRADUATE);
        List<User> undergraduateList=userService.listUserByRole(UserConstant.UNDERGRADUATE);
        List<User> alumniList=userService.listUserByRole(UserConstant.ALUMNI);
        request.setAttribute("professorList",professorList);
        request.setAttribute("graduateList",graduateList);
        request.setAttribute("undergraduateList",undergraduateList);
        request.setAttribute("alumniList",alumniList);
        return "admin/memberInfo";
    }

    @RequestMapping(value="addMember",method = RequestMethod.GET)
    public String addMember(){
        return "admin/memberAdd";
    }

    @RequestMapping(value="addMemberInfo",method = RequestMethod.POST)
    public String addMemberInfo(String adminName,
                                @RequestParam(value = "username") String username,
                                @RequestParam(value = "realName") String realName,
                                @RequestParam(value = "email") String userEmail,
                                @RequestParam(value = "phone",required = false) String userPhone,
                                @RequestParam(value = "description",required=false) String description,
                                @RequestParam(value = "gender") Integer userSex,
                                @RequestParam(value = "type") Integer userType,
                                @RequestParam(value = "state") Integer userState,
                                @RequestParam(value = "role") Integer userRole,
                                @RequestParam(value = "image",required = false) MultipartFile image,
                                @RequestParam(value = "detail",required = false) String detail,
                                @RequestParam(value = "web",required = false) String web,
                                @RequestParam(value = "prospect",required = false) String prospect,
                                @RequestParam(value = "cv",required = false) MultipartFile cv,
                                HttpServletRequest request,
                                RedirectAttributes redirectAttributes){
        if(username.trim().length()==0||realName.trim().length()==0||userEmail.trim().length()==0||userSex==null||userType==null
                ||userState==null||userRole==null){
            request.setAttribute("Msg","You should fill in all fields with *!");
            return "error";
        }

        User newUser=new User();

        if(newUser!=null) {
            try{
                String ip=request.getRemoteAddr();
                User user=userService.getUserByUserName(username);
                //判断用户名是否已注册，未注册则进入后续流程
                if(user==null||!user.getUserName().equals(username)){
                    //判断邮箱是否注册
                    if(userService.getUserByEmail(userEmail)==null) {
                        //添加用户
                        //TODO 路径配置
                        String basePath = ConfigConstant.RESOURCE_ROOT_PATH+"member";
                        try {
                            if (image != null && !image.isEmpty()) {
                                String filePath1 = FileHandler.uploadFile(basePath + "/"+username+"/image", image, request);
                                newUser.setImageUrl(filePath1);
                            }
                            if (cv != null && !cv.isEmpty()) {
                                String filePath2 = FileHandler.uploadFile(basePath + "/"+username+"/cv", cv, request);
                                newUser.setCvUrl(filePath2);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            request.setAttribute("Msg", "File Upload Failed!");
                            return "error";
                        }
                        User admin=userService.getUserByUserName(adminName);
                        newUser.setRealName(realName);
                        newUser.setUserName(username);
                        newUser.setAdminId(admin.getAdminId());
                        newUser.setAdminName(adminName);
                        newUser.setUserEmail(userEmail);
                        newUser.setUserSex(userSex);
                        newUser.setUserPhone(userPhone);
                        newUser.setUserType(userType);
                        newUser.setUserState(userState);
                        newUser.setUserRole(userRole);
                        newUser.setDescription(TextHandler.toHtml(description));
                        newUser.setWebUrl(web.trim());
                        newUser.setProspect(TextHandler.toHtml(prospect));
                        if(detail!=null){
                            newUser.setDetail(TextHandler.toHtml(detail));
                        }


                        newUser.setLastIp(ip);
                        Timestamp createTime = new Timestamp(new Date().getTime());
                        newUser.setCreateTime(createTime);
                        newUser.setLastLoginTime(createTime);
                        userService.addUser(newUser);
                        //根据写回的自增id设置初始rank值
                        newUser.setRank(newUser.getId());
                        userService.updateUserByUserId(newUser);

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
    public String editMemberInfo(Long id,
                                 @RequestParam(value = "realName") String realName,
                                 @RequestParam(value = "email") String userEmail,
                                 @RequestParam(value = "phone") String userPhone,
                                 @RequestParam(value = "description",required = false) String description,
                                 @RequestParam(value = "gender") Integer userSex,
                                 @RequestParam(value = "type") Integer userType,
                                 @RequestParam(value = "state") Integer userState,
                                 @RequestParam(value = "role") Integer userRole,
                                 @RequestParam(value = "image",required = false) MultipartFile image,
                                 @RequestParam(value = "detail") String detail,
                                 @RequestParam(value = "web",required = false) String web,
                                 @RequestParam(value = "cv",required = false) MultipartFile cv,
                                 @RequestParam(value = "prospect",required = false) String prospect,
                                 HttpServletRequest request){
        User updateUser=userService.getUserByUserId(id);
        if(realName.trim().length()==0||userEmail.trim().length()==0||userSex==null||userType==null
                ||userState==null||userRole==null){
            request.setAttribute("Msg","You should fill in all fields with *!");
            return "error";
        }
        User oldUser=userService.getUserByEmail(userEmail);
        if(oldUser!=null&&!oldUser.getUserName().equals(updateUser.getUserName())){
            request.setAttribute("Msg","Email address has been registered!");
            return "error";
        }
        //TODO 路径配置
        String basePath = ConfigConstant.RESOURCE_ROOT_PATH+"members";
        try {
            if (image != null && !image.isEmpty()) {
                FileHandler.deleteFile(updateUser.getImageUrl());
                String filePath1 = FileHandler.uploadFile(basePath + "/"+updateUser.getUserName()+"/image", image, request);
                updateUser.setImageUrl(filePath1);
            }
            if (cv != null && !cv.isEmpty()) {
                FileHandler.deleteFile(updateUser.getCvUrl());
                String filePath2 = FileHandler.uploadFile(basePath + "/"+updateUser.getUserName()+"/cv", cv, request);
                updateUser.setCvUrl(filePath2);
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
        updateUser.setUserType(userType);
        updateUser.setUserState(userState);
        updateUser.setUserRole(userRole);
        updateUser.setDescription(TextHandler.toHtml(description));
        updateUser.setDetail(TextHandler.toHtml(detail));
        updateUser.setWebUrl(web);
        updateUser.setProspect(TextHandler.toHtml(prospect));
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

    //重置用户密码
    @RequestMapping(value = "resetPassword")
    public String resetPassword(Long id,HttpServletRequest request){
        User user=userService.getUserByUserId(id);
        user.setPassword(ConfigConstant.DEFAULT_PASSWORD);
        userService.updateUserByUserId(user);
        return "redirect:/admin/memberInfo";
    }

    //member详情
    @RequestMapping(value = "memberDetail",method = RequestMethod.GET)
    public String memberDetail(Long id,HttpServletRequest request){
        User user=userService.getUserByUserId(id);
        request.setAttribute("user",user);
        List<Publication> publications=publicationService.listPublicationByUserId(id);
        request.setAttribute("publications",publications);
        List<Project> projects=projectService.listProjectByUserId(id);
        request.setAttribute("projects",projects);
        return "admin/memberDetail";
    }

    //所有news
    @RequestMapping(value = "newsInfo")
    public String allNews(HttpServletRequest request) {
        List<News> newsList = newsService.listAllNews();
        request.setAttribute("newsList", newsList);
        return "admin/newsInfo";
    }

    //所有projects
    @RequestMapping(value = "projectInfo")
    public String allProject(HttpServletRequest request){
        List<Label> labelList=labelService.listAllLabel();
        List<List<Project>> projectArray=new LinkedList<List<Project>>();
        for(Label label:labelList){
            List<Project> projects=projectService.listProjectByLabelId(label.getId());
            projectArray.add(projects);
        }
        request.setAttribute("labels",labelList);
        //List<Project> projectList=projectService.listAllProject();
        request.setAttribute("projects",projectArray);
        return "admin/projectInfo";
    }

    //所有publications
    @RequestMapping(value = "publicationInfo")
    public String allPublication(HttpServletRequest request){
        List<Publication> publicationList=publicationService.listAllPublication();
        request.setAttribute("publications",publicationList);
        return "admin/publicationInfo";
    }

    //所有label
    @RequestMapping(value="labelInfo")
    public String allLabel(HttpServletRequest request){
        List<Label> labels=labelService.listAllLabel();
        request.setAttribute("labels",labels);
        return "admin/labelInfo";
    }

    //上移member
    @RequestMapping(value = "upMember")
    public String moveMemberUp(Long id){
        User cur=userService.getUserByUserId(id);
        User former=userService.getFormer(cur.getUserRole(),cur.getRank());
        if(cur.getId()!=former.getId()){
            Long temp=cur.getRank();
            cur.setRank(former.getRank());
            former.setRank(temp);
            userService.updateUserByUserId(cur);
            userService.updateUserByUserId(former);
        }
        return "redirect:/admin/memberInfo";
    }

    //下移member
    @RequestMapping(value = "downMember")
    public String moveMemberDown(Long id){
        User cur=userService.getUserByUserId(id);
        User latter=userService.getLatter(cur.getUserRole(),cur.getRank());
        if(cur.getId()!=latter.getId()){
            Long temp=cur.getRank();
            cur.setRank(latter.getRank());
            latter.setRank(temp);
            userService.updateUserByUserId(cur);
            userService.updateUserByUserId(latter);
        }
        return "redirect:/admin/memberInfo";
    }

    //上移project
    @RequestMapping(value = "upProject")
    public String moveProjectUp(Long id,Long labelId){
        Project cur=projectService.getProjectById(id);
        Project former=projectService.getFormer(labelId,cur.getRank());
        if(cur.getId()!=former.getId()){
            Long temp=cur.getRank();
            cur.setRank(former.getRank());
            former.setRank(temp);
            projectService.editProject(cur);
            projectService.editProject(former);
        }
        return "redirect:/admin/projectInfo";
    }

    //下移project
    @RequestMapping(value = "downProject")
    public String moveProjectDown(Long id,Long labelId){
        Project cur=projectService.getProjectById(id);
        Project latter=projectService.getLatter(labelId,cur.getRank());
        if(cur.getId()!=latter.getId()){
            Long temp=cur.getRank();
            cur.setRank(latter.getRank());
            latter.setRank(temp);
            projectService.editProject(cur);
            projectService.editProject(latter);
        }
        return "redirect:/admin/projectInfo";
    }

    //上移publication
    @RequestMapping(value = "upPublication")
    public String movePublicationUp(Long id){
        Publication cur=publicationService.getPublicationById(id);
        Publication former=publicationService.getFormer(cur.getRank());
        if(cur.getId()!=former.getId()){
            Long temp=cur.getRank();
            cur.setRank(former.getRank());
            former.setRank(temp);
            publicationService.editPublication(cur);
            publicationService.editPublication(former);
        }
        return "redirect:/admin/publicationInfo";
    }

    //下移publication
    @RequestMapping(value = "downPublication")
    public String movePublicationDown(Long id){
        Publication cur=publicationService.getPublicationById(id);
        Publication latter=publicationService.getLatter(cur.getRank());
        if(cur.getId()!=latter.getId()){
            Long temp=cur.getRank();
            cur.setRank(latter.getRank());
            latter.setRank(temp);
            publicationService.editPublication(cur);
            publicationService.editPublication(latter);
        }
        return "redirect:/admin/publicationInfo";
    }

    //上移label
    @RequestMapping(value = "upLabel")
    public String moveLabelUp(Long id){
        Label cur=labelService.getLabelById(id);
        Label former=labelService.getFormer(cur.getRank());
        if(cur.getId()!=former.getId()){
            Long temp=cur.getRank();
            cur.setRank(former.getRank());
            former.setRank(temp);
            labelService.editLabel(cur);
            labelService.editLabel(former);
        }
        return "redirect:/admin/labelInfo";
    }

    //下移label
    @RequestMapping(value = "downLabel")
    public String moveLabelDown(Long id){
        Label cur=labelService.getLabelById(id);
        Label latter=labelService.getLatter(cur.getRank());
        if(cur.getId()!=latter.getId()){
            Long temp=cur.getRank();
            cur.setRank(latter.getRank());
            latter.setRank(temp);
            labelService.editLabel(cur);
            labelService.editLabel(latter);
        }
        return "redirect:/admin/labelInfo";
    }

}
