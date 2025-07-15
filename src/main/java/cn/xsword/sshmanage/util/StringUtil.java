package cn.xsword.sshmanage.util;

import java.util.Random;

/**
 * @Program: sshManage
 * @author: xsword
 * @create: 2025-07-09 18:20
 * @description: 针对字符串的一些工具
 **/
public class StringUtil {
    private static char[] dict = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".toCharArray();
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

    public static String randomString(int len) {
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder(len);

        // 生成随机字符并添加到StringBuilder
        for (int i = 0; i < len; i++) {
            int index = random.nextInt(dict.length);
            stringBuilder.append(dict[index]);
        }

        return stringBuilder.toString();
    }
}
