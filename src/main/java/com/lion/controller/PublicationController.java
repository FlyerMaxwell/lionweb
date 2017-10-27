package com.lion.controller;

import com.lion.entity.Publication;
import com.lion.entity.User;
import com.lion.service.PublicationService;
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
import java.io.IOException;
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
        List<Publication> publications=publicationService.listPublicationByUsername(username);
        request.setAttribute("publications",publications);
        User user=userService.getUserByUserName(username);
        request.setAttribute("user",user);
        return "publication/userPublication";
    }

    //显示publication添加页面
    @RequestMapping(value = "addPublication")
    public String addPublication(String username,HttpServletRequest request){
        request.setAttribute("username",username);
        return "publication/publicationAdd";
    }
    //提交新publication
    @RequestMapping(value = "addPublicationInfo",method = RequestMethod.POST)
    public String addPublicationInfo(String username,
                                     @RequestParam(value="title") String title,
                                     @RequestParam(value="authors") String authors,
                                     @RequestParam(value="organization") String organization,
                                     @RequestParam(value = "image",required = false) MultipartFile image,
                                     @RequestParam(value = "text", required = false) MultipartFile text,
                                     @RequestParam(value = "slide", required = false) MultipartFile slide,
                                     HttpServletRequest request,
                                     RedirectAttributes redirectAttributes) throws IOException{
        Publication publication=new Publication();
        publication.setUserName(username);
        //TODO AuthorName管理员用户的处理
        publication.setAuthorName(username);
        String basePath="D:/lion/publication";
        System.out.println("userProfile username:"+publication.getUserName());
        publication.setTitle(title);
        publication.setAuthors(authors);
        publication.setOrganization(organization);
        if(image!=null&&!image.isEmpty()){
            String filePath1= FileHandler.uploadFile(basePath+"/image",image,request);
            publication.setImageUrl(filePath1);
        }
        if(text!=null&&!text.isEmpty()){
            String filePath2= FileHandler.uploadFile(basePath+"/text",text,request);
            publication.setTextUrl(filePath2);
        }
        if(slide!=null&&!slide.isEmpty()){
            String filePath3= FileHandler.uploadFile(basePath+"/slide",slide,request);
            publication.setSlideUrl(filePath3);
        }

        publicationService.addNewPublication(publication);
        redirectAttributes.addAttribute("username",publication.getUserName());
        return "redirect:/publication/userProfile";
    }

    //编辑publication
    @RequestMapping (value = "editPublication",method = RequestMethod.GET)
    public String editPublication(String username,Long id,HttpServletRequest request){
        Publication publication=publicationService.getPublicationById(id);
        request.setAttribute("username",username);
        request.setAttribute("publication",publication);
        return "publication/publicationEdit";
    }
    //提交编辑后的publication
    @RequestMapping(value = "editPublicationInfo",method = RequestMethod.POST)
    public String editPublicationInfo(String username,Long id,
                                      @RequestParam(value="title") String title,
                                      @RequestParam(value="authors") String authors,
                                      @RequestParam(value="organization") String organization,
                                      @RequestParam(value = "image",required = false) MultipartFile image,
                                      @RequestParam(value = "text", required = false) MultipartFile text,
                                      @RequestParam(value = "slide", required = false) MultipartFile slide,
                                      HttpServletRequest request,
                                      RedirectAttributes redirectAttributes) throws IOException {
        Publication publication=publicationService.getPublicationById(id);
        String basePath="D:/lion/publication";
        System.out.println("userProfile username:"+publication.getUserName());
        publication.setTitle(title);
        publication.setAuthors(authors);
        publication.setOrganization(organization);
        if(image!=null&&!image.isEmpty()){
            FileHandler.deleteFile(publication.getImageUrl());
            String filePath1= FileHandler.uploadFile(basePath+"/image",image,request);
            publication.setImageUrl(filePath1);
        }
        if(text!=null&&!text.isEmpty()){
            FileHandler.deleteFile(publication.getTextUrl());
            String filePath2= FileHandler.uploadFile(basePath+"/text",text,request);
            publication.setTextUrl(filePath2);
        }
        if(slide!=null&&!slide.isEmpty()){
            FileHandler.deleteFile(publication.getSlideUrl());
            String filePath3= FileHandler.uploadFile(basePath+"/slide",slide,request);
            publication.setSlideUrl(filePath3);
        }

        publicationService.editPublication(publication);
        redirectAttributes.addAttribute("username",publication.getUserName());
        return "redirect:/publication/userProfile";
    }
    //删除publication
    @RequestMapping(value="deletePublicationInfo",method = RequestMethod.GET)
    public String deletePublicationInfo(String username,Long id,RedirectAttributes redirectAttributes){
        Publication publication=publicationService.getPublicationById(id);
        FileHandler.deleteFile(publication.getImageUrl());
        FileHandler.deleteFile(publication.getTextUrl());
        FileHandler.deleteFile(publication.getSlideUrl());
        FileHandler.deleteFile(publication.getVideoUrl());
        publicationService.deletePublication(id);
        redirectAttributes.addAttribute("username",username);
        return "redirect:/publication/userProfile";
    }
}
