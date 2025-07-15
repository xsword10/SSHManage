package cn.xsword.sshmanage.DTO;

import lombok.*;

/**
 * @Program: sshManage
 * @author: xsword
 * @create: 2025-07-14 17:55
 * @description: 用户信息DTO，用以修改用户信息请求接收参数
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserInfoDTO {
    String password;
    String nickname;
    String username;
}
