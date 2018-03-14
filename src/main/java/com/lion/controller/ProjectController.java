package com.lion.controller;

import com.lion.constant.ConfigConstant;
import com.lion.entity.*;
import com.lion.service.*;
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
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
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

    @Autowired
    PublicationService publicationService;

    @Autowired
    LabelService labelService;

    @Autowired
    ProCounterService proCounterService;

    //显示project主页面
    @RequestMapping(value = "")
    public String projectPage(HttpServletRequest request){
        List<Label> labelList=labelService.listAllLabel();
        List<List<Project>> projectArray=new LinkedList<List<Project>>();
        for(Label label:labelList){
            List<Project> projects=projectService.listProjectByLabelId(label.getId());
            projectArray.add(projects);
        }
        request.setAttribute("labels",labelList);
        request.setAttribute("projects",projectArray);
        return "project/projectInfo";
    }

    //用户个人project页面
    @RequestMapping(value ="userProfile",method = RequestMethod.GET)
    public String userProject(String username, HttpServletRequest request){
        User user=userService.getUserByUserName(username);
        List<Label> labelList=labelService.listAllLabel();
        List<List<Project>> projectArray=new LinkedList<List<Project>>();
        for(Label label:labelList){
            List<Project> projects=projectService.listProjectByUserAndLabelId(user.getId(),label.getId());
            projectArray.add(projects);
        }
        request.setAttribute("labels",labelList);
        request.setAttribute("projects",projectArray);
        request.setAttribute("user",user);
        return "project/userProject";
    }

    //显示project添加页面
    @RequestMapping(value = "addProject")
    public String addProject(String username,HttpServletRequest request){
        List<User> users=userService.listAllUser();
        List<Label> labels=labelService.listAllLabel();
        List<Publication> publications=publicationService.listAllPublication();
        request.setAttribute("users",users);
        request.setAttribute("publications",publications);
        request.setAttribute("username",username);
        request.setAttribute("labels",labels);
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
                                     @RequestParam(value = "members") List<Long> authorList,
                                     @RequestParam(value = "refs") List<Long> pubList,
                                     @RequestParam(value = "richText") String richText,
                                     @RequestParam(value = "access") Integer access,
                                     @RequestParam(value = "label") Long labelId,
                                     HttpServletRequest request,
                                     RedirectAttributes redirectAttributes){
        if(title.trim().length()==0||authors.trim().length()==0||description.trim().length()==0||organization.trim().length()==0||
                authorList==null||authorList.size()==0){
            request.setAttribute("Msg","You should fill in all fields with *!");
            return "error";
        }
        Project project=new Project();
        project.setUserName(username);
        //TODO 路径配置
        String basePath= ConfigConstant.RESOURCE_ROOT_PATH+"project";
        project.setTitle(title);
        project.setAuthors(authors);
        project.setDescription(TextHandler.toHtml(description));
        project.setOrganization(organization);
        project.setRichText(richText);
        project.setLastModifier(username);
        project.setLastIp(request.getRemoteAddr());
        project.setCreateTime(new Timestamp(new Date().getTime()));
        project.setUpdateTime(new Timestamp(new Date().getTime()));
        project.setAccess(access);
        project.setLabelId(labelId);
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
        //根据写回的自增id设置初始rank值
        project.setRank(project.getId());
        projectService.editProject(project);

        //添加文章作者关联记录
        //TODO 一次插入多条效率更高，此处不是非常需要
        batchInsertProPub(pubList,project,username,request);
        batchInsertProUser(authorList,project,username,request);

        redirectAttributes.addAttribute("username",project.getUserName());
        return "redirect:/project/userProfile";
    }

    //编辑project
    @RequestMapping (value = "editProject",method = RequestMethod.GET)
    public String editProject(Long id,Integer panel,HttpServletRequest request){
        List<User> users=userService.listAllUser();
        List<Publication> publications=publicationService.listAllPublication();
        List<Label> labels=labelService.listAllLabel();
        Project project=projectService.getProjectById(id);
        List<Long> oldAuthorList=projectUserService.listUserIdByProId(id);
        List<Long> oldPubList=projectPublicationService.listPubIdByProId(id);
        request.setAttribute("users",users);
        request.setAttribute("publications",publications);
        request.setAttribute("project",project);
        request.setAttribute("oldAuthorList",oldAuthorList);
        request.setAttribute("oldPubList",oldPubList);
        request.setAttribute("panel",panel);
        request.setAttribute("labels",labels);
        return "project/projectEdit";
    }
    //提交编辑后的project
    @RequestMapping(value = "editProjectInfo",method = RequestMethod.POST)
    public String editProjectInfo(String username,Long id,Integer panel,
                                      @RequestParam(value="title") String title,
                                      @RequestParam(value="authors") String authors,
                                      @RequestParam(value="description") String description,
                                      @RequestParam(value="organization") String organization,
                                      @RequestParam(value = "image",required = false) MultipartFile image,
                                      @RequestParam(value = "text", required = false) MultipartFile text,
                                      @RequestParam(value = "slide", required = false) MultipartFile slide,
                                      @RequestParam(value = "video", required = false) MultipartFile video,
                                      @RequestParam(value = "members") List<Long> authorList,
                                      @RequestParam(value = "refs") List<Long> publicationList,
                                      @RequestParam(value = "richText") String richText,
                                      @RequestParam(value = "access") Integer access,
                                      @RequestParam(value = "label") Long labelId,
                                      HttpServletRequest request,
                                      RedirectAttributes redirectAttributes) {
        if(title.trim().length()==0||authors.trim().length()==0||description.trim().length()==0||organization.trim().length()==0||
                authorList==null||authorList.size()==0){
            request.setAttribute("Msg","You should fill in all fields with *!");
            return "error";
        }
        Project project=projectService.getProjectById(id);
        //TODO路径配置
        String basePath=ConfigConstant.RESOURCE_ROOT_PATH+"project";
        project.setTitle(title);
        project.setAuthors(authors);
        project.setDescription(TextHandler.toHtml(description));
        project.setOrganization(organization);
        project.setRichText(richText);
        project.setLastModifier(username);
        project.setLastIp(request.getRemoteAddr());
        project.setUpdateTime(new Timestamp(new Date().getTime()));
        project.setAccess(access);
        project.setLabelId(labelId);
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
//        List<Long> tempList=authorList;
//        authorList.removeAll(oldAuthorList);
//        batchInsertProUser(authorList,project,username,request);
//        authorList=tempList;
//        oldAuthorList.removeAll(authorList);
//        for(Long oldAuthorId:oldAuthorList){
//            projectUserService.deleteRecordById(oldAuthorId,id);
//        }
        for(Long oldAuthorId:oldAuthorList){
            projectUserService.deleteRecordById(oldAuthorId,id);
        }
        batchInsertProUser(authorList,project,username,request);

        //更新项目-文章关联记录
        List<Long> oldPubList=projectPublicationService.listPubIdByProId(id);
//        tempList=publicationList;
//        publicationList.removeAll(oldPubList);
//        batchInsertProPub(publicationList,project,username,request);
//        publicationList=tempList;
//        oldPubList.removeAll(publicationList);
//        for(Long oldPubId:oldPubList){
//            projectPublicationService.deleteRecordById(oldPubId,id);
//        }
        for(Long oldPubId:oldPubList){
            projectPublicationService.deleteRecordById(oldPubId,id);
        }
        batchInsertProPub(publicationList,project,username,request);

        if(panel==null) {
            redirectAttributes.addAttribute("username", username);
            return "redirect:/project/userProfile";
        }
        return "redirect:/admin/projectInfo";
    }

    //TODO 信息可单独设表记录，此处预留username参数
    //删除project
    @RequestMapping(value="deleteProjectInfo",method = RequestMethod.GET)
    public String deleteProjectInfo(String username,Long id,Integer panel,RedirectAttributes redirectAttributes){
        Project project=projectService.getProjectById(id);
        FileHandler.deleteFile(project.getImageUrl());
        FileHandler.deleteFile(project.getTextUrl());
        FileHandler.deleteFile(project.getSlideUrl());
        FileHandler.deleteFile(project.getVideoUrl());
        projectService.deleteProject(id);
        projectPublicationService.deleteRecordByProId(id);
        projectUserService.deleteRecordByProId(id);
        proCounterService.deleteProCounter(id);
        if(panel==null) {
            redirectAttributes.addAttribute("username", username);
            return "redirect:/project/userProfile";
        }
        return "redirect:/admin/projectInfo";
    }

    //显示project详情
    @RequestMapping(value = "projectDetail")
    public String projectDetail(Long id,HttpServletRequest request){
        Project project=projectService.getProjectById(id);
        request.setAttribute("project",project);

        ProCounter counter=proCounterService.selectCountByProId(id);
        //TODO project是否存在需优化判断
        if(counter==null && project!=null){
            counter=new ProCounter();
            counter.setCounter(new Long(0));
            counter.setProId(id);
            proCounterService.addProCounter(counter);
        }
        counter.setCounter(counter.getCounter()+1);
        proCounterService.updateProCounter(counter);
        request.setAttribute("count",counter.getCounter());

        List<Long> publicationIds=projectPublicationService.listPubIdByProId(id);
        List<Publication> publications=new ArrayList<Publication>();
        for(Long pubId:publicationIds){
            publications.add(publicationService.getPublicationById(pubId));
        }
        request.setAttribute("publications",publications);
        return "project/projectDetail";
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
