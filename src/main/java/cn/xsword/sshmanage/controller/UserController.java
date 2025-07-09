package cn.xsword.sshmanage.controller;

import cn.xsword.sshmanage.DTO.UserDTO;
import cn.xsword.sshmanage.DTO.UserRegisterDTO;
import cn.xsword.sshmanage.entity.User;
import cn.xsword.sshmanage.service.UserService;
import cn.xsword.sshmanage.util.InputVerify;
import cn.xsword.sshmanage.util.SSHManageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/checkhealth")
    public SSHManageResponse check() {
        return SSHManageResponse.success("996748");
    }

    @CrossOrigin
    @PostMapping("/login")
    public SSHManageResponse login(@RequestBody UserDTO userDTO) {
        String username = userDTO.getUsername();
        String password = userDTO.getPassword();
        //System.out.println(username + " " +  password);
        if(!(InputVerify.usernameVerify(username) && InputVerify.passwordVerify(password))) {
            return SSHManageResponse.error("用户名密码不合法！请重新输入！");
        }
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        int res = userService.selectUser(user);

        return res > 0 ? SSHManageResponse.success("登陆成功！") : SSHManageResponse.error("用户名或密码错误！");
    }

    @CrossOrigin
    @PostMapping("/register")
    public SSHManageResponse register(@RequestBody UserRegisterDTO userDTO) {
        String username = userDTO.getUsername();
        String password = userDTO.getPassword();
        String code = userDTO.getCode();
        if(!(InputVerify.usernameVerify(username) && InputVerify.passwordVerify(password))) {
            return SSHManageResponse.error("用户名密码不合法！请重新输入！");
        }
        if(code == null || code.isEmpty()) {
            return SSHManageResponse.error("验证码code不能为空！");
        }
        if(!("996520".equals(code))) {
            return SSHManageResponse.error("你没资格啊～没资格～");
        }

        try {
            int res = userService.selectUserByUsername(username);
            if(res == 0) {
                User user = new User();
                user.setUsername(username);
                user.setPassword(password);
                userService.insertUser(user);
                return SSHManageResponse.success("注册成功！欢迎您成为高贵的SSHManage用户!");
            }else {
                return SSHManageResponse.error("该用户名已被注册！哎呀呀，想用户名真是麻烦呢～");
            }
        }catch(Exception e) {
            System.out.println(e.getMessage());
            return SSHManageResponse.error("注册失败！注册过程被资本做局，发生了未知异常！");
        }finally {
            ;
        }
    }
}
