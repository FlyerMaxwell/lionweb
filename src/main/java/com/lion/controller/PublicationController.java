package com.lion.controller;

import com.lion.entity.ProjectPublication;
import com.lion.entity.Publication;
import com.lion.entity.PublicationUser;
import com.lion.entity.User;
import com.lion.service.ProjectPublicationService;
import com.lion.service.PublicationService;
import com.lion.service.PublicationUserService;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author DJ
 * @date 2017/10/20.
 */
@Controller
@RequestMapping(value = "/publication")
public class PublicationController {

    @Autowired
    PublicationService publicationService;

    @Autowired
    UserService userService;

    @Autowired
    PublicationUserService publicationUserService;

    @Autowired
    ProjectPublicationService projectPublicationService;

    //显示publication主页面
    @RequestMapping(value = "")
    public String publicationPage(HttpServletRequest request){
        List<Publication> publications=publicationService.listAllPublication();
        request.setAttribute("publications",publications);
        return "publication/publicationInfo";
    }

    //用户个人publication页面
    @RequestMapping(value ="userProfile",method = RequestMethod.GET)
    public String userPublication(String username, HttpServletRequest request){
        User user=userService.getUserByUserName(username);
        List<Publication> publications=publicationService.listPublicationByUserId(user.getId());
        request.setAttribute("publications",publications);
        request.setAttribute("user",user);
        return "publication/userPublication";
    }

    //显示publication添加页面
    @RequestMapping(value = "addPublication")
    public String addPublication(String username,HttpServletRequest request){
        List<User> users=userService.listAllUser();
        request.setAttribute("username",username);
        request.setAttribute("users",users);
        return "publication/publicationAdd";
    }
    //提交新publication
    @RequestMapping(value = "addPublicationInfo",method = RequestMethod.POST)
    public String addPublicationInfo(String username,
                                     @RequestParam(value="title") String title,
                                     @RequestParam(value="authors") String authors,
                                     @RequestParam(value="description") String description,
                                     @RequestParam(value="organization") String organization,
                                     @RequestParam(value = "image",required = false) MultipartFile image,
                                     @RequestParam(value = "text") MultipartFile text,
                                     @RequestParam(value = "slide", required = false) MultipartFile slide,
                                     @RequestParam(value = "video", required = false) MultipartFile video,
                                     @RequestParam(value = "members") String members,
                                     HttpServletRequest request,
                                     RedirectAttributes redirectAttributes){
        if(title.trim().length()==0||authors.trim().length()==0||description.trim().length()==0||organization.trim().length()==0||
                members.trim().length()==0){
            request.setAttribute("Msg","You should fill in all fields with *!");
            return "error";
        }
        Publication publication=new Publication();
        publication.setUserName(username);
        //TODO 路径配置
        String basePath="D:/lion/publication";
        publication.setTitle(title);
        publication.setAuthors(authors);
        publication.setDescription(description);
        publication.setOrganization(organization);
        publication.setLastModifier(username);
        publication.setLastIp(request.getRemoteAddr());
        publication.setCreateTime(new Timestamp(new Date().getTime()));
        publication.setUpdateTime(new Timestamp(new Date().getTime()));
        try {
            if (image != null && !image.isEmpty()) {
                String filePath1 = FileHandler.uploadFile(basePath + "/image", image, request);
                publication.setImageUrl(filePath1);
            }
            if (text != null && !text.isEmpty()) {
                String filePath2 = FileHandler.uploadFile(basePath + "/text", text, request);
                publication.setTextUrl(filePath2);
            }
            if (slide != null && !slide.isEmpty()) {
                String filePath3 = FileHandler.uploadFile(basePath + "/slide", slide, request);
                publication.setSlideUrl(filePath3);
            }
            if (video != null && !video.isEmpty()) {
                String filePath4 = FileHandler.uploadFile(basePath + "/video", video, request);
                publication.setVideoUrl(filePath4);
            }
        }catch (Exception e){
            e.printStackTrace();
            request.setAttribute("Msg","File Upload Failed!");
            return "error";
        }

        //添加Publication
        publicationService.addNewPublication(publication);

        //添加文章作者关联记录
        //TODO 一次插入多条效率更高，此处不是非常需要
        String[] temp=members.split(",");
        List<Long> authorList=new ArrayList<Long>();
        for(String s:temp){
            authorList.add(Long.parseLong(s.trim()));
        }
        batchInsertPubUser(authorList,publication,username,request);

        redirectAttributes.addAttribute("username",publication.getUserName());
        return "redirect:/publication/userProfile";
    }

    //编辑publication
    @RequestMapping (value = "editPublication",method = RequestMethod.GET)
    public String editPublication(Long id,Integer panel,HttpServletRequest request){
        List<User> users=userService.listAllUser();
        Publication publication=publicationService.getPublicationById(id);
        request.setAttribute("users",users);
        request.setAttribute("publication",publication);
        List<Long> oldAuthorList=publicationUserService.listUserIdByPubId(id);
        request.setAttribute("oldAuthorList",oldAuthorList);
        request.setAttribute("panel",panel);
        return "publication/publicationEdit";
    }
    //提交编辑后的publication
    @RequestMapping(value = "editPublicationInfo",method = RequestMethod.POST)
    public String editPublicationInfo(String username,Long id,Integer panel,
                                      @RequestParam(value="title") String title,
                                      @RequestParam(value="authors") String authors,
                                      @RequestParam(value="description") String description,
                                      @RequestParam(value="organization") String organization,
                                      @RequestParam(value = "image",required = false) MultipartFile image,
                                      @RequestParam(value = "text", required = false) MultipartFile text,
                                      @RequestParam(value = "slide", required = false) MultipartFile slide,
                                      @RequestParam(value = "video", required = false) MultipartFile video,
                                      @RequestParam(value = "members") String members,
                                      HttpServletRequest request,
                                      RedirectAttributes redirectAttributes) {
        if(title.trim().length()==0||authors.trim().length()==0||description.trim().length()==0||organization.trim().length()==0||
                members.trim().length()==0){
            request.setAttribute("Msg","You should fill in all fields with *!");
            return "error";
        }
        Publication publication=publicationService.getPublicationById(id);
        //TODO路径配置
        String basePath="D:/lion/publication";
        publication.setTitle(title);
        publication.setAuthors(authors);
        publication.setDescription(description);
        publication.setOrganization(organization);
        publication.setLastModifier(username);
        publication.setLastIp(request.getRemoteAddr());
        publication.setUpdateTime(new Timestamp(new Date().getTime()));
        try {
            if (image != null && !image.isEmpty()) {
                FileHandler.deleteFile(publication.getImageUrl());
                String filePath1 = FileHandler.uploadFile(basePath + "/image", image, request);
                publication.setImageUrl(filePath1);
            }
            if (text != null && !text.isEmpty()) {
                FileHandler.deleteFile(publication.getTextUrl());
                String filePath2 = FileHandler.uploadFile(basePath + "/text", text, request);
                publication.setTextUrl(filePath2);
            }
            if (slide != null && !slide.isEmpty()) {
                FileHandler.deleteFile(publication.getSlideUrl());
                String filePath3 = FileHandler.uploadFile(basePath + "/slide", slide, request);
                publication.setSlideUrl(filePath3);
            }

            if (video != null && !video.isEmpty()) {
                FileHandler.deleteFile(publication.getVideoUrl());
                String filePath4 = FileHandler.uploadFile(basePath + "/video", video, request);
                publication.setVideoUrl(filePath4);
            }
        }catch (Exception e){
            e.printStackTrace();
            redirectAttributes.addAttribute("Msg","File Upload Failed!");
            return "error";
        }

        publicationService.editPublication(publication);
        List<Long> oldAuthorList=publicationUserService.listUserIdByPubId(id);
        String[] temp=members.split(",");
        List<Long> authorList=new ArrayList<Long>();
        for(String s:temp){
            authorList.add(Long.parseLong(s.trim()));
        }
        //此处注意使用深拷贝
//        List<Long> tempList=authorList;
//        authorList.removeAll(oldAuthorList);
//        batchInsertPubUser(authorList,publication,username,request);
//        authorList=tempList;
//        oldAuthorList.removeAll(authorList);
//        for(Long oldAuthorId:oldAuthorList){
//            publicationUserService.deleteRecordById(oldAuthorId,id);
//        }
        for(Long oldAuthorId:oldAuthorList){
            publicationUserService.deleteRecordById(oldAuthorId,id);
        }
        batchInsertPubUser(authorList,publication,username,request);

        if(panel==null) {
            redirectAttributes.addAttribute("username", publication.getUserName());
            return "redirect:/publication/userProfile";
        }
        return "redirect:/admin/publicationInfo";
    }
    //删除publication
    @RequestMapping(value="deletePublicationInfo",method = RequestMethod.GET)
    public String deletePublicationInfo(String username,Long id,Integer panel,RedirectAttributes redirectAttributes){
        Publication publication=publicationService.getPublicationById(id);
        FileHandler.deleteFile(publication.getImageUrl());
        FileHandler.deleteFile(publication.getTextUrl());
        FileHandler.deleteFile(publication.getSlideUrl());
        FileHandler.deleteFile(publication.getVideoUrl());
        publicationService.deletePublication(id);
        publicationUserService.deleteRecordByPubId(id);
        projectPublicationService.deleteRecordByPubId(id);
        if(panel==null) {
            redirectAttributes.addAttribute("username", username);
            return "redirect:/publication/userProfile";
        }
        return "redirect:/admin/publicationInfo";
    }

    //显示publication详情
    @RequestMapping(value = "publicationDetail")
    public String publicationDetail(Long id,HttpServletRequest request){
        Publication publication=publicationService.getPublicationById(id);
        request.setAttribute("publication",publication);
        return "publication/publicationDetail";
    }

    private void batchInsertPubUser(List<Long> authorList,Publication publication,
                                    String username,HttpServletRequest request){
        for(Long authorId:authorList){
            PublicationUser publicationUser=new PublicationUser();
            publicationUser.setPubId(publication.getId());
            publicationUser.setUserId(authorId);
            Timestamp timestamp=new Timestamp(new Date().getTime());
            publicationUser.setCreateTime(timestamp);
            publicationUser.setUpdateTime(timestamp);
            publicationUser.setLastModifier(username);
            publicationUser.setLastIp(request.getRemoteAddr());
            publicationUserService.addRecord(publicationUser);
        }
    }
}
