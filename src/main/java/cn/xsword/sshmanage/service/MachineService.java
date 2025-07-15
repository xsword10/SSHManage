package cn.xsword.sshmanage.service;


import cn.xsword.sshmanage.entity.Machine;

public interface MachineService {
    public int insertMachine(Machine machine);

    public int updateMachine(Machine machine);

    public int deleteMachineById(Long id);

    public int selectMachine(Machine machine);

    public int selectMachineById(Long id);
}
