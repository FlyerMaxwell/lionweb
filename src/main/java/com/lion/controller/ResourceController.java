package com.lion.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @author DJ
 * @date 2017/11/1.
 */
@Controller
@RequestMapping(value = "/resource")
public class ResourceController {
    @RequestMapping("/showImage")
    public void showPicture(HttpServletRequest request,
                            HttpServletResponse response, String imagePath) throws IOException {
        //如果没有图片显示默认图片
        if(imagePath == null || imagePath.equals("")){
            imagePath = request.getSession().getServletContext().getRealPath("static/img/default.jpg");
        }
        FileInputStream in;
        response.setContentType("application/octet-stream;charset=UTF-8");
        try {
            // 图片读取路径
            in = new FileInputStream(imagePath);
            int i = in.available();
            byte[] data = new byte[i];
            in.read(data);
            in.close();

            // 写图片
            OutputStream outputStream = new BufferedOutputStream(
                    response.getOutputStream());
            outputStream.write(data);
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
