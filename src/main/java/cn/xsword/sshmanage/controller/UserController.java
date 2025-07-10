package cn.xsword.sshmanage.controller;

import cn.xsword.sshmanage.DTO.LoginDTO;
import cn.xsword.sshmanage.DTO.UserDTO;
import cn.xsword.sshmanage.DTO.UserRegisterDTO;
import cn.xsword.sshmanage.entity.User;
import cn.xsword.sshmanage.entity.UserDetailsImpl;
import cn.xsword.sshmanage.service.UserService;
import cn.xsword.sshmanage.util.InputVerify;
import cn.xsword.sshmanage.util.JwtUtil;
import cn.xsword.sshmanage.util.SSHManageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;



/**
 * @Program: sshManage
 * @author: xsword
 * @create: 2025-07-08 15:14
 * @description: UserController, 用户注册、登陆、注销等
 **/
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/checkhealth")
    public SSHManageResponse check() {
        return SSHManageResponse.success("996748");
    }

    /**
     * @Description: 用户登陆
     * @Author: xsword
     * @Date: 2025/7/9
    **/
    @CrossOrigin
    @PostMapping("/login")
    public SSHManageResponse login(@RequestBody UserDTO userDTO) {
        String username = userDTO.getUsername();
        String password = userDTO.getPassword();
        if(!(InputVerify.usernameVerify(username) && InputVerify.passwordVerify(password))) {
            return SSHManageResponse.error("用户名密码不合法！请重新输入！");
        }

        try {
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
            Authentication authentication = authenticationManager.authenticate(token);
            User user = ((UserDetailsImpl)authentication.getPrincipal()).getUser();

            String jwt = JwtUtil.createJWT(String.valueOf(user.getUserId()));
            LoginDTO loginDTO = new LoginDTO();
            loginDTO.setUsername(username);
            loginDTO.setNickname("里斯特");
            loginDTO.setPhoto("../picture/default.jpg");
            loginDTO.setJwt(jwt);

            return SSHManageResponse.success("登陆成功！", loginDTO);
        }catch (Exception e){
            return SSHManageResponse.error("登陆失败！" +  e.getMessage());
        }
    }

    /**
     * @Description: 用户注册，code内定，密码加密后存储
     * @Author: xsword
     * @Date: 2025/7/9
    **/
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
            /*
             *TODO
             * 线程安全问题
             * 算了，直接在数据库中加unique完事了，多线程失败了就是资本做局了。
             */
            int res = userService.selectUserByUsername(username);
            if(res == 0) {
                User user = new User();
                user.setUsername(username);
                String encodePassword = passwordEncoder.encode(password);
                user.setPassword(encodePassword);
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

    @CrossOrigin
    @PostMapping("/logout")
    public SSHManageResponse logout() {
        System.out.println("=================");
        return SSHManageResponse.success("?");
    }
}
