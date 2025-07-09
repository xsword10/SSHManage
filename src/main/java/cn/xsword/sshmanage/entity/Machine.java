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
