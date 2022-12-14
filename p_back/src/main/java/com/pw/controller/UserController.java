package com.pw.controller;

import com.pw.entity.User;
import com.pw.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin //在服务器端支持跨域访问
@RestController //如果全是ajax请求
@RequestMapping("/user")
public class UserController {
    //一定会有业务逻辑层的对象
    @Autowired
    UserService userService;
    public static final int PAGE_SIZE = 5;

    @RequestMapping("login")
    public int login(String account,String password){
        //登录验证
        return userService.login(account,password);
    }

    @RequestMapping("/selectUserPage")
    public List<User> selectUserPage(String userName,String phone,Integer page){
        //根据页码计算起始行
        int startRow = 0;
        if(page != null){
            startRow = (page-1) * PAGE_SIZE;
        }
        return userService.selectUserPage(userName,phone,startRow);
    }

    @RequestMapping("/getRowCount")
    public int getRowCount(String userName,String phone){
        return userService.getRowCount(userName,phone);
    }

    @RequestMapping("/deleteUserById")
    public int deleteUserById(int userId){
        return userService.deleteUserById(userId);
    }


    @RequestMapping("/createUser")
    public int createUser(User user){
        String userId = System.currentTimeMillis()+"";
        user.setUserId(userId);
        return userService.createUser(user);
    }


}
