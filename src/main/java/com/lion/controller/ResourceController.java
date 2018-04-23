package com.lion.controller;

import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author DJ
 * @date 2017/11/1.
 */
@Controller
@RequestMapping(value = "/resource")
public class ResourceController {
    @RequestMapping("/showImage")
    public void showPicture(HttpServletRequest request,
                            HttpServletResponse response, String imagePath,
                            @RequestParam(value = "type",required = false) Integer type)
            throws IOException {
        //如果没有图片显示默认图片
        if(type!=null) {
            if (imagePath == null || imagePath.equals("")) {
                imagePath = request.getSession().getServletContext().getRealPath("/statics/images/default_" + (type == 0 ? "0" : "1") + ".png");
            }
        }
        FileInputStream in;
        response.setContentType("application/octet-stream;charset=UTF-8");
        String pat1="(\\.\\.)+";
        Pattern r1=Pattern.compile(pat1);
        Matcher m1=r1.matcher(imagePath);
        if(m1.find()){
            return;
        }

        String pat2="^(/home/lion/lionweb/data).*";
        Pattern r2=Pattern.compile(pat2);
        Matcher m2=r2.matcher(imagePath);
        if(!m2.find()){
            return;
        }
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

    @RequestMapping("/downloadFile")
    public ResponseEntity<byte[]> downloadFile(HttpServletRequest request,
                                               String filePath) throws Exception{

        //指定要下载的文件所在路径
        //String path = request.getServletContext().getRealPath(filePath);
        File file = new File(filePath);
        HttpHeaders headers = new HttpHeaders();

        String pat1="(\\.\\.)+";
        Pattern r1=Pattern.compile(pat1);
        Matcher m1=r1.matcher(filePath);
        if(m1.find()){
            return null;
        }

        String pat2="^(/home/lion/lionweb/data).*";
        Pattern r2=Pattern.compile(pat2);
        Matcher m2=r2.matcher(filePath);
        if(!m2.find()){
            return null;
        }

//        if (null == file || !file.exists()
//                || !file.getCanonicalFile().getParent().equals(new File(filePath).getCanonicalPath())) {
//            return null;
//        }

        //通知浏览器以下载的方式打开文件
        String[] filePaths=filePath.split("/");
        headers.setContentDispositionFormData("attachment", filePaths[filePaths.length-1]);

        //定义以流的形式下载返回文件数据
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);

        //使用Spring MVC框架的ResponseEntity对象封装返回下载数据
        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),headers, HttpStatus.OK);
    }
}
