package com.lion.controller;

import com.lion.service.PublicationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class HomeController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    PublicationService publicationService;

    @RequestMapping("/index")
    public String index(HttpServletRequest request){
        
        return "index";
    }

}
