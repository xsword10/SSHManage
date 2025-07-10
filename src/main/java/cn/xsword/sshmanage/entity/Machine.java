package cn.xsword.sshmanage.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

/**
 * @Program: sshManage
 * @author: xsword
 * @create: 2025-07-08 14:12
 * @description: 服务器信息实体类
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@TableName("machine")
public class Machine {
    @TableId(type = IdType.AUTO)
    private long machineId;
    private long userId;
    private String hostname;
    private String ip;
    private String password;
    private int port;
    private String content;
}
