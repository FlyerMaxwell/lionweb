package com.lion.controller;

import com.lion.entity.News;
import com.lion.entity.User;
import com.lion.service.NewsService;
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
 * @date 2017/10/31.
 */
@Controller
@RequestMapping(value = "/news")
public class NewsController {
    @Autowired
    NewsService newsService;

    @Autowired
    UserService userService;

    //显示news主页面
    @RequestMapping(value = "")
    public String newsPage(String username,HttpServletRequest request) {
        List<News> newsList = newsService.listAllNews();
        request.setAttribute("newsList", newsList);
        return "news/newsInfo";
    }

    //用户个人news页面
    @RequestMapping(value = "userProfile", method = RequestMethod.GET)
    public String userNews(String username, HttpServletRequest request) {
        User user = userService.getUserByUserName(username);
        List<News> newsList = newsService.listNewsByUsername(username);
        request.setAttribute("newsList", newsList);
        request.setAttribute("user", user);
        //request.setAttribute("username",username);
        return "news/userNews";
    }

    //显示news添加页面
    @RequestMapping(value = "addNews")
    public String addNews(String username, HttpServletRequest request) {
//        request.setAttribute("username", username);
        return "news/newsAdd";
    }

    //提交新news
    @RequestMapping(value = "addNewsInfo", method = RequestMethod.POST)
    public String addNewsInfo(String username,
                              @RequestParam(value = "title") String title,
                              @RequestParam(value = "description", required = false) String description,
                              @RequestParam(value = "image", required = false) MultipartFile image,
//                              @RequestParam(value = "text", required = false) MultipartFile text,
                              @RequestParam(value = "text", required = false) String text,
                              HttpServletRequest request,
                              RedirectAttributes redirectAttributes) {
        News news = new News();
        news.setUserName(username);
        //TODO 路径配置
        String basePath = "D:/lion/news";
        news.setTitle(title);
        news.setDescription(description);
        news.setLastModifier(username);
        news.setLastIp(request.getRemoteAddr());
        news.setCreateTime(new Timestamp(new Date().getTime()));
        news.setUpdateTime(new Timestamp(new Date().getTime()));
        try {
            if (image != null && !image.isEmpty()) {
                String filePath1 = FileHandler.uploadFile(basePath + "/image", image, request);
                news.setImageUrl(filePath1);
            }
//            if (text != null && !text.isEmpty()) {
//                String filePath2 = FileHandler.uploadFile(basePath + "/text", text, request);
//                news.setTextUrl(filePath2);
//            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("Msg", "File Upload Failed!");
            return "error";
        }

        news.setTextUrl(text);

        //添加news
        newsService.addNewNews(news);

        redirectAttributes.addAttribute("username", news.getUserName());
        return "redirect:/news/userProfile";
    }

    //编辑news
    @RequestMapping(value = "editNews", method = RequestMethod.GET)
    public String editNews(String username, Long id, HttpServletRequest request) {
        News news = newsService.getNewsById(id);
//        request.setAttribute("username", username);
        request.setAttribute("news", news);
        return "news/newsEdit";
    }

    //提交编辑后的news
    @RequestMapping(value = "editNewsInfo", method = RequestMethod.POST)
    public String editNewsInfo(String username, Long id,
                               @RequestParam(value = "title") String title,
                               @RequestParam(value = "description") String description,
                               @RequestParam(value = "image", required = false) MultipartFile image,
                               @RequestParam(value = "text", required = false) MultipartFile text,
                               HttpServletRequest request,
                               RedirectAttributes redirectAttributes) {
        News news = newsService.getNewsById(id);
        //TODO路径配置
        String basePath = "D:/lion/news";
        news.setTitle(title);
        news.setDescription(description);
        news.setLastModifier(username);
        news.setLastIp(request.getRemoteAddr());
        news.setUpdateTime(new Timestamp(new Date().getTime()));
        try {
            if (image != null && !image.isEmpty()) {
                FileHandler.deleteFile(news.getImageUrl());
                String filePath1 = FileHandler.uploadFile(basePath + "/image", image, request);
                news.setImageUrl(filePath1);
            }
            if (text != null && !text.isEmpty()) {
                FileHandler.deleteFile(news.getTextUrl());
                String filePath2 = FileHandler.uploadFile(basePath + "/text", text, request);
                news.setTextUrl(filePath2);
            }
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addAttribute("Msg", "File Upload Failed!");
            return "error";
        }

        //更新news
        newsService.editNews(news);

        redirectAttributes.addAttribute("username", news.getUserName());
        return "redirect:/news/userProfile";
    }

    //删除news
    @RequestMapping(value = "deleteNewsInfo", method = RequestMethod.GET)
    public String deleteNewsInfo(String username, Long id, RedirectAttributes redirectAttributes) {
        News news = newsService.getNewsById(id);
        FileHandler.deleteFile(news.getImageUrl());
        FileHandler.deleteFile(news.getTextUrl());
        newsService.deleteNews(id);
        redirectAttributes.addAttribute("username", username);
        return "redirect:/news/userProfile";
    }

    //news详情页
    @RequestMapping(value = "newsDetail", method = RequestMethod.GET)
    public String newsDetail(Long id,HttpServletRequest request){
        News news=newsService.getNewsById(id);
        request.setAttribute("news",news);
        return "news/newsDetail";
    }

}