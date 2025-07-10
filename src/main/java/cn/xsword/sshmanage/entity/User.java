package cn.xsword.sshmanage.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

/**
 * @Program: sshManage
 * @author: xsword
 * @create: 2025-07-08 14:11
 * @description: 用户信息实体类
 **/
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
