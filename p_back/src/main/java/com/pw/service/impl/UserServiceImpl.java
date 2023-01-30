package com.pw.service.impl;

import com.pw.entity.User;
import com.pw.mapper.UserMapper;
import com.pw.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    //一定会有数据访问层对象
    @Autowired
    UserMapper userMapper;

    @Override
    public List<User> selectUserPage(String userName, String phone, int starRow) {
        return userMapper.selectUserPage(userName,phone,starRow);
    }

    @Override
    public int createUser(User user) {
        return userMapper.createUser(user);
    }

    @Override
    public int deleteUserById(int userId) {
        return userMapper.deleteUserById(userId);
    }

    @Override
    public User getUserById(int userId) {
        return userMapper.getUserById(userId);
    }

    @Override
    public int getRowCount(String userName, String phone) {
        return userMapper.getRowCount(userName,phone);
    }

    @Override
    public int login(String account,String password) {
        return userMapper.login(account,password);
    }
}
