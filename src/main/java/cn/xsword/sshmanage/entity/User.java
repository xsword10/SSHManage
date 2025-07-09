package cn.xsword.sshmanage.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@TableName("user")
public class User {
    @TableId(type = IdType.AUTO)
    private long userId;
    private String username;
    private String password;
}
