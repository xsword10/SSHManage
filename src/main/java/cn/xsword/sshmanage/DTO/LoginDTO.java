package cn.xsword.sshmanage.DTO;

import lombok.*;

/**
 * @Program: sshManage
 * @author: xsword
 * @create: 2025-07-10 11:16
 * @description: 用于传输用户登陆成功后返回给前端的一些数据
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LoginDTO {
    String jwt; //加密userid 及 认证信息；
    String nickname;
    String username;
    String photo;
    
    /**
     * @Description: 将loginDTO转json，用于jwt加密返回前端
     * @Author: xsword
     * @Date: 2025/7/10
    **/
    @Override
    public String toString() {
        return "{'nickname':'" + nickname + "','username':'" + username + "','photo':'" + photo + "'}";
    }
}
