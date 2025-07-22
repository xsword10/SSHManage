package cn.xsword.sshmanage.controller;

import cn.xsword.sshmanage.DTO.MachineDTO;
import cn.xsword.sshmanage.DTO.UserInfoDTO;
import cn.xsword.sshmanage.entity.Machine;
import cn.xsword.sshmanage.service.MachineService;
import cn.xsword.sshmanage.util.ClassConvert;
import cn.xsword.sshmanage.util.InputVerify;
import cn.xsword.sshmanage.util.SSHManageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Program: sshManage
 * @author: xsword
 * @create: 2025-07-14 17:15
 * @description: MachineController，本项目拟不对machine信息作除非空外任何校验，因为在使用machine信息
 *              进行ssh时会报相应错误信息。
 **/
@RestController
@RequestMapping("machine")
public class MachineController {
    @Autowired
    private MachineService machineService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;

    /*
     *TODO
     * machine 与 userid 关联。因此在请求machine时要附带userid或者username（去查userid）。
     * 可是jwt加密内容为userid，考虑在通过spring security后取出userid -> redis，用以后续业务。
     */
    private ThreadLocal<UserInfoDTO> threadLocal;

    /**
     * @Description: 新建连接，允许格式等不合法但不允许关键信息为空。port可为0即默认22端口。
     *              不对重复的连接进行校验约束，后续考虑对用户建立的连接数量上限作限制。
     * @Author: xsword
     * @Date: 2025/7/14
    **/
    @CrossOrigin
    @PostMapping("addMachine")
    public SSHManageResponse addMachine(@RequestBody Machine machine) {
        if(!InputVerify.machineInputVerify(machine)) {
            return SSHManageResponse.error("ssh 连接信息不合法，请重新输入！");
        }
        try {
            MachineDTO machineDTO = ClassConvert.machineConvertMachineDTO(machine);
            machine.setPassword(passwordEncoder.encode(machine.getPassword()));
            machineService.insertMachine(machine);
            return SSHManageResponse.success("连接信息保存成功！", machineDTO);
        }catch(Exception e) {
            System.out.println(e.getMessage());
            return SSHManageResponse.error("连接信息保存失败！请重新尝试！");
        }finally {
            ;
        }
    }

    /**
     * @Description: 删除连接信息，由于前端会保存machineid，故直接deletebyid
     * @Author: xsword
     * @Date: 2025/7/15
    **/
    @PostMapping("deleteMachine")
    public SSHManageResponse deleteMachine(@RequestBody Machine machine) {
        return null;
    }

    @PostMapping("updateMachine")
    public SSHManageResponse updateMachine(@RequestBody Machine machine) {
        return null;
    }

    @PostMapping("selectMachine")
    public SSHManageResponse selectMachine(@RequestBody Machine machine) {
        return null;
    }

    /**
     * @Description: 返回machine列表
     * @Author: xsword
     * @Date: 2025/7/17
    **/
    @PostMapping("listMachine")
    public SSHManageResponse listMachine(@RequestParam Long userId) {
        List<Machine> machineList = machineService.listMachines(userId);
        return SSHManageResponse.success("machineList", machineList);
    }
}
