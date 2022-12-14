package com.pw.mapper;

import com.pw.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    List<User> selectUserPage(
            @Param("userName") String userName,
            @Param("phone") String phone,
            @Param("startRow") int startRow);

    int createUser(User user);
    int deleteUserById(int userId);
    int getRowCount(
            @Param("userName")
            String userName,
            @Param("phone")
            String phone);

    int login(
            @Param("account")
            String account,
            @Param("password")
            String password);
}
