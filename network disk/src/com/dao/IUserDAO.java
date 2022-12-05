package com.dao;

import com.pojo.User;

import java.sql.SQLException;
import java.util.List;

public interface IUserDAO {
    User login(String username, String password) throws SQLException;

    List<User> selectUserById(Long userId) throws SQLException;

    void regiser(Long userId,String phone,String password, String userName,String email);

    List<User> getAllUser();

    List<User> loginByEmail(String eamil);
}
