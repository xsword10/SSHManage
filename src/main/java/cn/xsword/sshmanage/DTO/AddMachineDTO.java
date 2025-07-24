package cn.xsword.sshmanage.DTO;

import lombok.*;

/**
 * @Program: sshManage
 * @author: xsword
 * @create: 2025-07-24 15:56
 * @description: addmachinedto
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AddMachineDTO {
    private String username;
    private String ip;
    private String hostname;
    private Integer port;
    private String password;
    private String content;
}
