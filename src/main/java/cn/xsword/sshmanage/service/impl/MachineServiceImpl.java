package cn.xsword.sshmanage.service.impl;

import cn.xsword.sshmanage.entity.Machine;
import cn.xsword.sshmanage.mapper.MachineMapper;
import cn.xsword.sshmanage.service.MachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Program: sshManage
 * @author: xsword
 * @create: 2025-07-14 17:06
 * @description: MachineServiceImpl
 **/
@Service
public class MachineServiceImpl implements MachineService {
    @Autowired
    private MachineMapper machineMapper;

    /**
     * @Description: 新增机器
     * @Author: xsword
     * @Date: 2025/7/14
    **/
    @Override
    public int insertMachine(Machine machine) {
        return machineMapper.insert(machine);
    }

    @Override
    public int updateMachine(Machine machine) {
        return machineMapper.updateById(machine);
    }

    @Override
    public int deleteMachineById(Long id) {
        return machineMapper.deleteById(id);
    }

    @Override
    public int selectMachine(Machine machine) {
        return 0;
    }

    @Override
    public int selectMachineById(Long id) {
        return machineMapper.selectById(id) ==  null ? 0 : 1;
    }

    @Override
    public List<Machine> listMachines(Long userId) {
        List<Machine> machineList = machineMapper.selectByMap((Map<String, Object>) new HashMap<>().put("user_id", userId));
        return machineList;
    }
}
