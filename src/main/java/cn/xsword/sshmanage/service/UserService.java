package cn.xsword.sshmanage.service;

import cn.xsword.sshmanage.entity.User;

public interface UserService {
    public int insertUser(User user);

    public int updateUser(User user);

    public int deleteUserById(Integer id);

    public int selectUser(User user);

    public int selectUserByUsername(String username);
}
