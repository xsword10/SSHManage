package cn.xsword.sshmanage.util;

/**
 * @Program: sshManage
 * @author: xsword
 * @create: 2025-07-09 18:20
 * @description: 针对字符串的一些工具
 **/
public class StringUtil {
    
    /**
     * @Description: 检查字符串token是否含有内容
     * @Author: xsword
     * @Date: 2025/7/10
    **/
    public static boolean hasText(String token) {
        if(token == null || token.isEmpty())
            return false;
        return true;
    }

    public static String objectToString(Object object) {
        if (object == null) {
            return "";
        }
        return object.toString();
    }
}
