package com.lion.controller;

import com.lion.entity.*;
import com.lion.service.ProjectPublicationService;
import com.lion.service.ProjectService;
import com.lion.service.ProjectUserService;
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
 * @date 2017/10/30.
 */
@Controller
@RequestMapping(value="/project")
public class ProjectController {

    @Autowired
    ProjectService projectService;

    @Autowired
    ProjectPublicationService projectPublicationService;

    @Autowired
    ProjectUserService projectUserService;

    @Autowired
    UserService userService;

    //显示project主页面
    @RequestMapping(value = "")
    public String projectPage(HttpServletRequest request){
        List<Project> projects=projectService.listAllProject();
        request.setAttribute("projects",projects);
        return "project/projectInfo";
    }

    //用户个人project页面
    @RequestMapping(value ="userProfile",method = RequestMethod.GET)
    public String userProject(String username, HttpServletRequest request){
        User user=userService.getUserByUserName(username);
        List<Project> projects=projectService.listProjectByUserId(user.getId());
        request.setAttribute("projects",projects);
        request.setAttribute("user",user);
        return "project/userProject";
    }

    //显示project添加页面
    @RequestMapping(value = "addProject")
    public String addProject(String username,HttpServletRequest request){
        request.setAttribute("username",username);
        return "project/projectAdd";
    }
    //提交新project
    @RequestMapping(value = "addProjectInfo",method = RequestMethod.POST)
    public String addProjectInfo(String username,
                                     @RequestParam(value="title") String title,
                                     @RequestParam(value="authors") String authors,
                                     @RequestParam(value="description") String description,
                                     @RequestParam(value="organization") String organization,
                                     @RequestParam(value = "image",required = false) MultipartFile image,
                                     @RequestParam(value = "text", required = false) MultipartFile text,
                                     @RequestParam(value = "slide", required = false) MultipartFile slide,
                                     @RequestParam(value = "video", required = false) MultipartFile video,
                                     @RequestParam(value = "authorList") List<Long> authorList,
                                     @RequestParam(value = "pubList") List<Long> pubList,
                                     HttpServletRequest request,
                                     RedirectAttributes redirectAttributes){
        Project project=new Project();
        project.setUserName(username);
        //TODO 路径配置
        String basePath="D:/lion/project";
        project.setTitle(title);
        project.setAuthors(authors);
        project.setDescription(description);
        project.setOrganization(organization);
        project.setLastModifier(username);
        project.setLastIp(request.getRemoteAddr());
        project.setCreateTime(new Timestamp(new Date().getTime()));
        project.setUpdateTime(new Timestamp(new Date().getTime()));
        try {
            if (image != null && !image.isEmpty()) {
                String filePath1 = FileHandler.uploadFile(basePath + "/image", image, request);
                project.setImageUrl(filePath1);
            }
            if (text != null && !text.isEmpty()) {
                String filePath2 = FileHandler.uploadFile(basePath + "/text", text, request);
                project.setTextUrl(filePath2);
            }
            if (slide != null && !slide.isEmpty()) {
                String filePath3 = FileHandler.uploadFile(basePath + "/slide", slide, request);
                project.setSlideUrl(filePath3);
            }
            if (video != null && !video.isEmpty()) {
                String filePath4 = FileHandler.uploadFile(basePath + "/video", video, request);
                project.setVideoUrl(filePath4);
            }
        }catch (Exception e){
            e.printStackTrace();
            request.setAttribute("Msg","File Upload Failed!");
            return "error";
        }

        //添加Project
        projectService.addNewProject(project);

        //添加文章作者关联记录
        //TODO 一次插入多条效率更高，此处不是非常需要
        batchInsertProPub(authorList,project,username,request);
        batchInsertProUser(pubList,project,username,request);

        redirectAttributes.addAttribute("username",project.getUserName());
        return "redirect:/project/userProfile";
    }

    //编辑project
    @RequestMapping (value = "editProject",method = RequestMethod.GET)
    public String editProject(String username,Long id,HttpServletRequest request){
        Project project=projectService.getProjectById(id);
        request.setAttribute("username",username);
        request.setAttribute("project",project);
        return "project/projectEdit";
    }
    //提交编辑后的project
    @RequestMapping(value = "editProjectInfo",method = RequestMethod.POST)
    public String editProjectInfo(String username,Long id,
                                      @RequestParam(value="title") String title,
                                      @RequestParam(value="authors") String authors,
                                      @RequestParam(value="description") String description,
                                      @RequestParam(value="organization") String organization,
                                      @RequestParam(value = "image",required = false) MultipartFile image,
                                      @RequestParam(value = "text", required = false) MultipartFile text,
                                      @RequestParam(value = "slide", required = false) MultipartFile slide,
                                      @RequestParam(value = "video", required = false) MultipartFile video,
                                      @RequestParam(value = "authorList") List<Long> authorList,
                                      @RequestParam(value = "publicationList") List<Long> publicationList,
                                      HttpServletRequest request,
                                      RedirectAttributes redirectAttributes) {
        Project project=projectService.getProjectById(id);
        //TODO路径配置
        String basePath="D:/lion/project";
        project.setTitle(title);
        project.setAuthors(authors);
        project.setDescription(description);
        project.setOrganization(organization);
        project.setLastModifier(username);
        project.setLastIp(request.getRemoteAddr());
        project.setUpdateTime(new Timestamp(new Date().getTime()));
        try {
            if (image != null && !image.isEmpty()) {
                FileHandler.deleteFile(project.getImageUrl());
                String filePath1 = FileHandler.uploadFile(basePath + "/image", image, request);
                project.setImageUrl(filePath1);
            }
            if (text != null && !text.isEmpty()) {
                FileHandler.deleteFile(project.getTextUrl());
                String filePath2 = FileHandler.uploadFile(basePath + "/text", text, request);
                project.setTextUrl(filePath2);
            }
            if (slide != null && !slide.isEmpty()) {
                FileHandler.deleteFile(project.getSlideUrl());
                String filePath3 = FileHandler.uploadFile(basePath + "/slide", slide, request);
                project.setSlideUrl(filePath3);
            }

            if (video != null && !video.isEmpty()) {
                FileHandler.deleteFile(project.getVideoUrl());
                String filePath4 = FileHandler.uploadFile(basePath + "/video", video, request);
                project.setVideoUrl(filePath4);
            }
        }catch (Exception e){
            e.printStackTrace();
            redirectAttributes.addAttribute("Msg","File Upload Failed!");
            return "error";
        }

        //更新project
        projectService.editProject(project);

        //更新项目-用户关联记录
        List<Long> oldAuthorList=projectUserService.listUserIdByProId(id);
        List<Long> tempList=authorList;
        authorList.removeAll(oldAuthorList);
        batchInsertProUser(authorList,project,username,request);
        authorList=tempList;
        oldAuthorList.removeAll(authorList);
        for(Long oldAuthorId:oldAuthorList){
            projectUserService.deleteRecordById(oldAuthorId,id);
        }

        //更新项目-文章关联记录
        List<Long> oldPubList=projectPublicationService.listPubIdByProId(id);
        tempList=publicationList;
        publicationList.removeAll(oldPubList);
        batchInsertProPub(publicationList,project,username,request);
        publicationList=tempList;
        oldPubList.removeAll(publicationList);
        for(Long oldPubId:oldPubList){
            projectPublicationService.deleteRecordByPubId(oldPubId,id);
        }

        redirectAttributes.addAttribute("username",project.getUserName());
        return "redirect:/project/userProfile";
    }
    //删除project
    @RequestMapping(value="deleteProjectInfo",method = RequestMethod.GET)
    public String deleteProjectInfo(String username,Long id,RedirectAttributes redirectAttributes){
        Project project=projectService.getProjectById(id);
        FileHandler.deleteFile(project.getImageUrl());
        FileHandler.deleteFile(project.getTextUrl());
        FileHandler.deleteFile(project.getSlideUrl());
        FileHandler.deleteFile(project.getVideoUrl());
        projectService.deleteProject(id);
        redirectAttributes.addAttribute("username",username);
        return "redirect:/project/userProfile";
    }

    private void batchInsertProUser(List<Long> authorList,Project project,
                                    String username,HttpServletRequest request){
        for(Long authorId:authorList){
            ProjectUser projectUser=new ProjectUser();
            projectUser.setProId(project.getId());
            projectUser.setUserId(authorId);
            Timestamp timestamp=new Timestamp(new Date().getTime());
            projectUser.setCreateTime(timestamp);
            projectUser.setUpdateTime(timestamp);
            projectUser.setLastModifier(username);
            projectUser.setLastIp(request.getRemoteAddr());
            projectUserService.addRecord(projectUser);
        }
    }

    private void batchInsertProPub(List<Long> publicationList,Project project,
                                   String username,HttpServletRequest request){
        for(Long pubId:publicationList){
            ProjectPublication projectPublication=new ProjectPublication();
            projectPublication.setProId(project.getId());
            projectPublication.setPubId(pubId);
            Timestamp timestamp=new Timestamp(new Date().getTime());
            projectPublication.setCreateTime(timestamp);
            projectPublication.setUpdateTime(timestamp);
            projectPublication.setLastModifier(username);
            projectPublication.setLastIp(request.getRemoteAddr());
            projectPublicationService.addRecord(projectPublication);
        }
    }
}
