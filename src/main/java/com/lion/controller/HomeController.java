package com.lion.controller;

import com.lion.constant.ConfigConstant;
import com.lion.entity.News;
import com.lion.entity.Project;
import com.lion.entity.Publication;
import com.lion.service.NewsService;
import com.lion.service.ProjectService;
import com.lion.service.PublicationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class HomeController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    NewsService newsService;


    //显示首页
    @RequestMapping("/index")
    public String index(HttpServletRequest request){

        //TODO 数量配置
        int num= ConfigConstant.FOCUS_NEWS_NUM;

        List<News> newsList=newsService.listLatestNews(num);

        request.setAttribute("newsList",newsList);

        return "index";
    }

    //显示登录页面
    @RequestMapping(value={"/login"},method = {RequestMethod.GET})
    public String user(){
        return "user/userLogin";
    }

}
