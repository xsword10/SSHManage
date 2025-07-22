package cn.xsword.sshmanage.common;

import java.util.Map;

/**
 * @Program: sshManage
 * @author: xsword
 * @create: 2025-07-21 17:05
 * @description: 保存用户上下文信息如userId
 **/
public class UserContext {
    private static ThreadLocal<Map<String, Object>> context = new ThreadLocal<>();

    public static void setContext(String key, Object value) {
        Map<String, Object> map = context.get();
        map.put(key, value);
    }

    public static Object getContext(String key) {
        Map<String, Object> map = context.get();
        return map.get(key);
    }

    public static void removeContext() {
        context.remove();
    }
}
