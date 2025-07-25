package cn.xsword.sshmanage.service;


import cn.xsword.sshmanage.entity.Machine;

import java.util.List;

public interface MachineService {
    public int insertMachine(Machine machine);

    public int updateMachine(Machine machine);

    public int deleteMachineById(Long id);

    public int selectMachine(Machine machine);

    Machine selectById(Long id);

    public int selectMachineById(Long id);

    public List<Machine> listMachines(Long userId);
}
