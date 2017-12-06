package com.lion.util;

/**
 * @author DJ
 * @date 2017/12/6.
 */
public class TextHandler {
    //textarea空格、换行符处理
    //TODO 其他特殊字符的单独处理
    public static String toHtml(String text){
        while (text.indexOf("\n") != -1) {
            text = text.replace("\n","<br/>");
        }
        while (text.indexOf(" ") != -1) {
            text = text.replace(" ","&nbsp;");
        }
        return text;
    }
}
