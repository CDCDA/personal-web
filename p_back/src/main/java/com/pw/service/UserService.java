package com.pw.service;

import com.pw.entity.User;

import java.util.List;

public interface UserService {
    /*
    * url   /user/selectUserPage?userName=a&phone=1213213
    * */
    List<User> selectUserPage(String userName,String phone,int starRow);

    /*
    * */
    int createUser(User user);

    /*
     * */
    int deleteUserById(int userId);

    User getUserById(int userId);
    /*
     * */
    int getRowCount(String userName,String phone);

    int login(String account,String password);
}
