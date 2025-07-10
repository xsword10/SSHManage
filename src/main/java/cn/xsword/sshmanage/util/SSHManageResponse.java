package cn.xsword.sshmanage.util;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Program: sshManage
 * @author: xsword
 * @create: 2025-07-08 18:32
 * @description: 自定义http返回类型,其中data用以存放jwt
 **/
@Data
@AllArgsConstructor
public class SSHManageResponse<T> {
    private long code;
    private String message;
    private Object data;

    public static SSHManageResponse success(String message, Object data) {
        return new SSHManageResponse(200, message, data);
    }

    public static SSHManageResponse success(String message) {
        return new SSHManageResponse(200, message, null);
    }

    public static SSHManageResponse error(String message) {
        return new SSHManageResponse(500, message, null);
    }

    public  static SSHManageResponse error(String message, Object data) {
        return new SSHManageResponse(500, message, data);
    }
}
