package cn.xsword.sshmanage.util;

import cn.xsword.sshmanage.DTO.AddMachineDTO;
import cn.xsword.sshmanage.DTO.MachineDTO;
import cn.xsword.sshmanage.entity.Machine;

import java.util.ArrayList;
import java.util.List;

/**
 * @Program: sshManage
 * @author: xsword
 * @create: 2025-07-14 18:17
 * @description: 用以实现一些类的转化
 **/
public class ClassConvert {
    public static List<MachineDTO> convert(List<Machine> machines) {
        List<MachineDTO> machineDTOs = new ArrayList<>();
        for(int i = 0; i < machines.size(); i++) {
            MachineDTO machineDTO = new MachineDTO();
            machineDTO.setId(i + 1L);
            machineDTO.setIp(machines.get(i).getIp());
            machineDTO.setPort(machines.get(i).getPort());
            machineDTO.setHostname(machines.get(i).getHostname());
            machineDTO.setContent(machines.get(i).getContent());
            machineDTOs.add(machineDTO);
        }
        return machineDTOs;
    }

    public static Machine convert(AddMachineDTO machine) {
        Machine result = new Machine();
        result.setIp(machine.getIp());
        result.setHostname(machine.getHostname());
        result.setPort(machine.getPort());
        result.setContent(machine.getContent());
        result.setPassword(machine.getPassword());
        return result;
    }
}
