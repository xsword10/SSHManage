package cn.xsword.sshmanage.DTO;

import lombok.*;


/**
 * @Program: sshManage
 * @author: xsword
 * @create: 2025-07-08 14:10
 * @description: 用于用户注册接收参数
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserRegisterDTO {
    private String username;
    private String password;
    private String code;
}
