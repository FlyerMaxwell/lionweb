package com.lion.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @RequestMapping(path={"/","/home"},method = {RequestMethod.GET})
    public String lion(Model model){
        System.out.println("We're Lions!");
        return "/index.jsp";
    }
}
