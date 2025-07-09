package cn.xsword.sshmanage.DTO;

import lombok.*;

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
