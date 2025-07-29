package cn.xsword.sshmanage.service.impl;

import cn.xsword.sshmanage.entity.Machine;
import cn.xsword.sshmanage.mapper.MachineMapper;
import cn.xsword.sshmanage.service.MachineService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
    public Machine selectById(Long id) {
        return machineMapper.selectById(id);
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

    @Override
    public int getMachineNum(Long userId) {
        return Math.toIntExact(machineMapper.selectCount(new QueryWrapper<Machine>().eq("user_id", userId)));
    }

    @Override
    public PageInfo<Machine> listMachines(Long userId, int pageNum, int pageSize) {
        LambdaQueryWrapper<Machine> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Machine::getUserId, userId);
        queryWrapper.orderByDesc(Machine::getCreateTime);
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(machineMapper.selectList(queryWrapper));
    }
}
