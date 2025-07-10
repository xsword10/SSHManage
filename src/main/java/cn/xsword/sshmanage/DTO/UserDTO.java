package cn.xsword.sshmanage.DTO;

import lombok.*;

/**
 * @Program: sshManage
 * @author: xsword
 * @create: 2025-07-08 14:09
 * @description: 用于用户登陆接收参数
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDTO {
    private String username;
    private String password;
}
