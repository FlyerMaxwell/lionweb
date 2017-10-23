package com.lion.controller;

import com.lion.entity.Publication;
import com.lion.service.PublicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author DJ
 * @date 2017/10/20.
 */
@Controller
@RequestMapping(value = "/publication")
public class PublicationController {

    @Autowired
    PublicationService publicationService;

    //显示publication主页面
    @RequestMapping(value = "")
    public String publicationPage(HttpServletRequest request){
        Publication publication=publicationService.listAllPublication();
        request.setAttribute("publication",publication);
        return "publication/publicationInfo";
    }
    //显示publication添加页面
    @RequestMapping(value = "addPublication")
    public String addPublication(String username,HttpServletRequest request){
        Publication publication=new Publication();
        publication.setAuthorName("username");
        request.setAttribute("publication",publication);
        return "publication/publicationAdd";
    }
    //提交新publication
    @RequestMapping(value = "addPublicationInfo",method = RequestMethod.POST)
    public String addPublicationInfo(Publication publication, RedirectAttributes redirectAttributes){
        publicationService.addNewPublication(publication);
        //redirectAttributes.addAttribute(publication.getAuthorName());
        return "redirect:/publication";
    }
    //编辑publication
    @RequestMapping (value = "editPublication",method = RequestMethod.GET)
    public String editPublication(String username,HttpServletRequest request){
        return "publication/publicationEdit";
    }
    //提交编辑后的publication
    @RequestMapping(value = "editPublicationInfo",method = RequestMethod.POST)
    public String editPublicationInfo(String username,HttpServletRequest request){
        return "redirect:/publication";
    }
}
