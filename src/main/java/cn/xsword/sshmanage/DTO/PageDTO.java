package cn.xsword.sshmanage.DTO;

import cn.xsword.sshmanage.entity.Machine;
import lombok.*;

import java.util.List;

/**
 * @Program: sshManage
 * @author: xsword
 * @create: 2025-07-28 16:55
 * @description: page dto
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PageDTO {
    private Integer total;
    private Integer pageNum;
    private Integer pageSize;
    private List<MachineDTO> machineList;
}
