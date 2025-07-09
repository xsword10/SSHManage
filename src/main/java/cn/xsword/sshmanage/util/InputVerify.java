package cn.xsword.sshmanage.util;

public class InputVerify {
    public static boolean usernameVerify(String username) {
        if(username == null) {
            return false;
        }
        if(username.length() < 8) {
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
        if(password.length() < 8) {
            return false;
        }
        for(int i = 0; i < password.length(); i++) {
            if(!(Character.isDigit(password.charAt(i)) || Character.isLetter(password.charAt(i)))) {
                return false;
            }
        }
        return true;
    }
}
