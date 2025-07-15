package cn.xsword.sshmanage.util;

import cn.xsword.sshmanage.entity.Machine;

/**
 * @Program: sshManage
 * @author: xsword
 * @create: 2025-07-08 16:12
 * @description: 输入格式检查工具类，考虑将输入校验功能移交前端降低后端压力
 **/
public class InputVerify {
    public static boolean usernameVerify(String username) {
        if(username == null) {
            return false;
        }
        if(username.length() < 8 || username.length() > 12) {
            return false;
        }
        for(int i = 0; i < username.length(); i++) {
            if(!Character.isDigit(username.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean passwordVerify(String password) {
        if(password == null) {
            return false;
        }
        if(password.length() < 8 || password.length() > 16) {
            return false;
        }
        for(int i = 0; i < password.length(); i++) {
            if(!(Character.isDigit(password.charAt(i)) || Character.isLetter(password.charAt(i)))) {
                return false;
            }
        }
        return true;
    }

    public static boolean nicknameVerify(String nickname) {
        if(nickname == null) {
            return false;
        }
        if(nickname.length() > 10) {
            return false;
        }
        return true;
    }

    public static boolean inputNotNull(String input) {
        return !(input == null || input.isBlank());
    }

    public static boolean machineInputVerify(Machine machine) {
        return inputNotNull(machine.getIp()) && inputNotNull(machine.getHostname()) && inputNotNull(machine.getPassword());
    }
}
