package com.lion.controller;

import com.lion.entity.Label;
import com.lion.service.LabelService;
import com.lion.service.PublicationService;
import com.sun.org.apache.regexp.internal.RE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * @author DJ
 * @date 2018/1/2.
 */
@Controller
@RequestMapping(value = "/label")
public class LabelController {

    @Autowired
    LabelService labelService;

    //显示添加label页面
    @RequestMapping(value = "addLabel",method = RequestMethod.GET)
    public String  addLabel(){
        return "label/labelAdd";
    }

    //提交新label
    @RequestMapping(value = "addLabelInfo",method = RequestMethod.POST)
    public String addLabelInfo(String username,
                               @RequestParam(value="name") String name,
                               HttpServletRequest request){
        Label label=new Label();
        label.setName(name);
        label.setUserName(username);
        label.setCreateTime(new Timestamp(new Date().getTime()));
        label.setUpdateTime(new Timestamp(new Date().getTime()));
        label.setLastModifier(username);
        label.setLastIp(request.getRemoteAddr());

        //添加label
        labelService.addNewLabel(label);

        //用生成的id设置初始rank
        label.setRank(label.getId());
        labelService.editLabel(label);
        return "redirect:/admin/labelInfo";
    }

    //编辑label页面
    @RequestMapping(value = "editLabel",method = RequestMethod.GET)
    public String editLabel(Long id,HttpServletRequest request){
        Label label=labelService.getLabelById(id);
        request.setAttribute("label",label);
        return "label/labelEdit";
    }

    //提交编辑后的label
    @RequestMapping(value = "editLabelInfo",method = RequestMethod.POST)
    public String editLabelInfo(String username,Long id,
                                @RequestParam(value = "name") String name,
                                HttpServletRequest request){
        Label label=labelService.getLabelById(id);
        label.setName(name);
        label.setUpdateTime(new Timestamp(new Date().getTime()));
        label.setLastModifier(username);
        label.setLastIp(request.getRemoteAddr());
        labelService.editLabel(label);
        return "redirect:/admin/labelInfo";
    }

    //删除label
    @RequestMapping(value = "deleteLabelInfo")
    public String deleteLabelInfo(Long id){
        labelService.deleteLabel(id);
        return "redirect:/admin/labelInfo";
    }
}
