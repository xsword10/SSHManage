package cn.xsword.sshmanage.controller;

import cn.xsword.sshmanage.DTO.AddMachineDTO;
import cn.xsword.sshmanage.DTO.DeleteMachineDTO;
import cn.xsword.sshmanage.DTO.ListMachineDTO;
import cn.xsword.sshmanage.DTO.UserInfoDTO;
import cn.xsword.sshmanage.entity.Machine;
import cn.xsword.sshmanage.service.MachineService;
import cn.xsword.sshmanage.service.UserService;
import cn.xsword.sshmanage.util.ClassConvert;
import cn.xsword.sshmanage.util.InputVerify;
import cn.xsword.sshmanage.util.SSHManageResponse;
import cn.xsword.sshmanage.util.StringUtil;
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
    private UserService userService;
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
    public SSHManageResponse addMachine(@RequestBody AddMachineDTO machine) {
        if(!InputVerify.machineInputVerify(machine)) {
            return SSHManageResponse.error("连接信息不合法，请重新输入！");
        }
        try {
            Machine m = ClassConvert.convert(machine);
            m.setUserId(userService.getUserIdByUsername(machine.getUsername()));
            machineService.insertMachine(m);
            return SSHManageResponse.success("连接信息保存成功！");
        }catch(Exception e){
            System.out.println(e.getMessage());
            return SSHManageResponse.error("添加machine失败，请重新尝试！");
        }finally {
            ;
        }
    }

    /**
     * @Description: 删除连接信息，由于前端会保存machineid，故直接deletebyid
     * @Author: xsword
     * @Date: 2025/7/15
    **/
    @CrossOrigin
    @PostMapping("deleteMachine")
    public SSHManageResponse deleteMachine(@RequestBody DeleteMachineDTO deleteMachine) {
        System.out.println("=============");
        try {
            machineService.deleteMachineById(deleteMachine.getId());
            return SSHManageResponse.success("删除成功！");
        }catch(Exception e) {
            System.out.println(e.getMessage());
            return SSHManageResponse.error("遇到未知错误，请重试！");
        }finally {
            ;
        }
    }

    @PostMapping("updateMachine")
    public SSHManageResponse updateMachine(@RequestBody Machine machine) {
        return null;
    }

    @CrossOrigin
    @PostMapping("connect")
    public SSHManageResponse machineConnect(@RequestBody DeleteMachineDTO machine) {
        try {
            Machine res = machineService.selectById(machine.getId());
            return SSHManageResponse.success("连接成功！", res);
        }catch(Exception e) {
            System.out.println(e.getMessage());
            return SSHManageResponse.error("连接失败！");
        }finally {
            ;
        }
    }

    /**
     * @Description: 返回machine列表
     * @Author: xsword
     * @Date: 2025/7/17
    **/
    @CrossOrigin
    @PostMapping("listMachine")
    public SSHManageResponse listMachine(@RequestBody ListMachineDTO listMachineDTO) {
        Long userId = userService.getUserIdByUsername(listMachineDTO.getUsername());
        List<Machine> machineList = machineService.listMachines(userId);
        return SSHManageResponse.success("machineList", ClassConvert.convert(machineList));
    }
}
