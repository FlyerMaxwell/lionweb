package com.lion.util;

/**
 * @author DJ
 * @date 2017/12/6.
 */
public class TextHandler {
    //textarea空格、换行符处理
    //TODO 其他特殊字符的单独处理
    public static String toHtml(String text){
        //不同操作系统中的换行符
        while (text.indexOf("\r\n") != -1) {
            text = text.replace("\r\n","<br/>");
        }
        while (text.indexOf("\n") != -1) {
            text = text.replace("\n","<br/>");
        }
        while (text.indexOf("\r") != -1) {
            text = text.replace("\r","<br/>");
        }
//        while (text.indexOf(" ") != -1) {
//            text = text.replace(" ","&nbsp;");
//        }
        return text;
    }
}
