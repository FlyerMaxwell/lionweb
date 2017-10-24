package com.lion.controller;

import com.lion.entity.Publication;
import com.lion.service.PublicationService;
import com.lion.util.FileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

    //显示publication主页面
    @RequestMapping(value = "")
    public String publicationPage(HttpServletRequest request){
        List<Publication> publications=publicationService.listAllPublication();
        request.setAttribute("publications",publications);
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
    public String addPublicationInfo(Publication publication, MultipartFile image,MultipartFile text,MultipartFile ppt,HttpServletRequest request,RedirectAttributes redirectAttributes,ModelMap modelmap) throws IOException{
        String filePath1= FileUpload.uploadFile(image,request);
        String filePath2= FileUpload.uploadFile(text,request);
        String filePath3= FileUpload.uploadFile(ppt,request);
        ImageEntity

        publicationService.addNewPublication(publication);
        //redirectAttributes.addAttribute(publication.getAuthorName());
        return "redirect:/publication";
    }

//    @RequestMapping(value = "addIndent",method = RequestMethod.POST)
//    public String addIndent(String commodityName,MultipartFile imagetop,MultipartFile imagetwo,MultipartFile imagethree,int price,String description,double discount,HttpServletRequest request,ModelMap modelMap ) throws IOException {
//        CommodityEntity commodityEntity = new CommodityEntity();
//        ImageEntity imageEntity = new ImageEntity();
//        String filePath1 = FileUpload.uploadFile(imagetop, request);
//        String filePath2 = FileUpload.uploadFile(imagetwo, request);
//        String filePath3 = FileUpload.uploadFile(imagethree, request);
//        filePath1 = filePath1+";"+filePath2+";"+filePath3;
//        imageEntity.setImg(filePath1);
//        imageRepository.saveAndFlush(imageEntity);
//        imageEntity =  imageRepository.findByImg(filePath1);
//        commodityEntity.setCommodityname(commodityName);
//        commodityEntity.setPrice(price);
//        commodityEntity.setDescription(description);
//        commodityEntity.setImageByImageId(imageEntity);
//        commodityEntity.setDiscount(discount);
//        commodityRepository.saveAndFlush(commodityEntity);
//        List<CommodityEntity> commodityEntities = commodityRepository.findAll();
//        modelMap.addAttribute("commodityEntities",commodityEntities);
//        return "cate";
//    }

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
