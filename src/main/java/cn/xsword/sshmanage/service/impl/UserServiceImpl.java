package cn.xsword.sshmanage.service.impl;

import cn.xsword.sshmanage.entity.User;
import cn.xsword.sshmanage.mapper.UserMapper;
import cn.xsword.sshmanage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public int insertUser(User user) {
        return userMapper.insert(user);
    }

    @Override
    public int updateUser(User user) {
        return 0;
    }

    @Override
    public int deleteUserById(Integer id) {
        return 0;
    }

    @Override
    public int selectUser(User user) {
        String username = user.getUsername();
        String password = user.getPassword();
        Map<String,Object> map = new HashMap<>();
        map.put("username",username);
        map.put("password",password);
        List<User> list = userMapper.selectByMap(map);
        return list.size();
    }

    @Override
    public int selectUserByUsername(String username) {
        Map<String,Object> map = new HashMap<>();
        map.put("username",username);
        List<User> list = userMapper.selectByMap(map);
        return list.size();
    }
}
