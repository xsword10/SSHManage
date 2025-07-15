package cn.xsword.sshmanage.util;

import cn.xsword.sshmanage.DTO.MachineDTO;
import cn.xsword.sshmanage.entity.Machine;

/**
 * @Program: sshManage
 * @author: xsword
 * @create: 2025-07-14 18:17
 * @description: 用以实现一些类的转化
 **/
public class ClassConvert {
    public static MachineDTO machineConvertMachineDTO(Machine machine) {
        MachineDTO machineDTO = new MachineDTO();
        machineDTO.setId(machine.getMachineId());
        machineDTO.setIp(machine.getIp());
        machineDTO.setHostname(machine.getHostname());
        machineDTO.setPort(machine.getPort());
        machineDTO.setContent(machine.getContent());
        return machineDTO;
    }
}
