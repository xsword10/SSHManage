package cn.xsword.sshmanage.service;


import cn.xsword.sshmanage.entity.Machine;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface MachineService {
    public int insertMachine(Machine machine);

    public int updateMachine(Machine machine);

    public int deleteMachineById(Long id);

    public int selectMachine(Machine machine);

    Machine selectById(Long id);

    public int selectMachineById(Long id);

    public List<Machine> listMachines(Long userId);

    public PageInfo<Machine> listMachines(Long userId, int pageNum, int pageSize);

    public int getMachineNum(Long userId);
}
