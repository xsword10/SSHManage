package cn.xsword.sshmanage.util;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SSHManageResponse {
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
