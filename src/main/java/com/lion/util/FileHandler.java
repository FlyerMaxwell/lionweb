package com.lion.util;

/**
 * @author DJ
 * @date 2017/10/23.
 */
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;


public class FileHandler {

    //文件上传
    public static String uploadFile(String rootPath, MultipartFile file, HttpServletRequest request) throws IOException {
        File dir=new File(rootPath);
        if(!dir.exists()){
            dir.mkdirs();
        }
        //写文件到服务器
        File serverFile=new File(dir.getAbsolutePath()+File.separator+file.getOriginalFilename());
        file.transferTo(serverFile);
//        return rootPath+File.separator+serverFile.getName();
        return rootPath+"/"+serverFile.getName();
    }

    //文件或目录删除
    public static boolean deletePath(String filePath) {
        boolean flag = false;
        File file = new File(filePath);
        // 判断目录或文件是否存在
        if (!file.exists()) {  // 不存在返回 false
            return flag;
        } else {
            // 判断是否为文件
            if (file.isFile()) {  // 为文件时调用删除文件方法
                return deleteFile(filePath);
            } else {  // 为目录时调用删除目录方法
                return deleteDirectory(filePath);
            }
        }
    }

    //删除文件
    public static boolean deleteFile(String filePath) {
        boolean flag = false;
        File file = new File(filePath);
        // 路径为文件且不为空则进行删除
        if (file.isFile() && file.exists()) {
            file.delete();
            flag = true;
        }
        return flag;
    }

    //删除目录
    public static boolean deleteDirectory(String sPath) {
        //如果filePath不以文件分隔符结尾，自动添加文件分隔符
        if (!sPath.endsWith(File.separator)) {
            sPath = sPath + File.separator;
        }
        File dirFile = new File(sPath);
        //如果dir对应的文件不存在，或者不是一个目录，则退出
        if (!dirFile.exists() || !dirFile.isDirectory()) {
            return false;
        }
        boolean flag = true;
        //删除文件夹下的所有文件(包括子目录)
        File[] files = dirFile.listFiles();
        for (int i = 0; i < files.length; i++) {
            //删除子文件
            if (files[i].isFile()) {
                flag = deleteFile(files[i].getAbsolutePath());
                if (!flag) break;
            } //删除子目录
            else {
                flag = deleteDirectory(files[i].getAbsolutePath());
                if (!flag) break;
            }
        }
        if (!flag) return false;
        //删除当前目录
        if (dirFile.delete()) {
            return true;
        } else {
            return false;
        }
    }

}
