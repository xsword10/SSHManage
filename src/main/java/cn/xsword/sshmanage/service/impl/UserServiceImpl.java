package cn.xsword.sshmanage.service.impl;

import cn.xsword.sshmanage.entity.User;
import cn.xsword.sshmanage.mapper.UserMapper;
import cn.xsword.sshmanage.service.UserService;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Program: sshManage
 * @author: xsword
 * @create: 2025-07-08 16:12
 * @description: UserServiceImpl
 **/
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    /**
     * @Description: 新增用户，返回 1 ？ 成功 ： 失败
     * @Author: xsword
     * @Date: 2025/7/9
     **/
    @Override
    public int insertUser(User user) {
        return userMapper.insert(user);
    }

    /**
     * @Description: 更新用户信息，返回 1 ？ 成功 ： 失败
     * @Author: xsword
     * @Date: 2025/7/9
    **/
    @Override
    public int updateUserByUsername(User user) {
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("username",user.getUsername()).set("password",user.getPassword()).set("nickname",user.getNickname());
        return userMapper.update(null, updateWrapper);
    }

    /**
     * @Description: 根据用户id删除用户，返回 1 ？ 成功 ： 失败
     * @Author: xsword
     * @Date: 2025/7/9
    **/
    @Override
    public int deleteUserById(Long id) {
        return 0;
    }

    /**
     * @Description: 根据用户实体查询用户，返回查询结果数量（0 / 1）
     * @Author: xsword
     * @Date: 2025/7/9
    **/
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

    /**
     * @Description: 根据用户名查找用户，返回查询结果数量（0 / 1）
     * @Author: xsword
     * @Date: 2025/7/9
    **/
    @Override
    public int selectUserByUsername(String username) {
        Map<String,Object> map = new HashMap<>();
        map.put("username",username);
        List<User> list = userMapper.selectByMap(map);
        return list.size();
    }
}
