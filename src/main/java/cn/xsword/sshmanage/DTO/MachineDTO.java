package cn.xsword.sshmanage.DTO;

import lombok.*;

/**
 * @Program: sshManage
 * @author: xsword
 * @create: 2025-07-14 18:15
 * @description: MachineDTO用以传输machineInfo
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MachineDTO {
    Long id;
    Long machineId;
    String ip;
    String hostname;
    Integer port;
    String content;
}
