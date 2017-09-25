package com.lion;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

    @RequestMapping(path={"/Lion"})
    @ResponseBody
    public String lion(){
        System.out.println("We're Lions!");
        return "Lion";
    }
}
