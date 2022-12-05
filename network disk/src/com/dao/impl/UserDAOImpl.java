package com.dao.impl;

import com.dao.IUserDAO;
import com.pojo.User;
import com.utils.JDBCUtils;
import com.utils.JDBCUtilsV2;

import java.sql.SQLException;
import java.util.List;

public class UserDAOImpl implements IUserDAO {
    @Override
    public User login(String username, String password) throws SQLException {
        List<User> users = JDBCUtils.query(User.class, "select * from t_user where username=? and password=?", username, password);
        if(users.isEmpty())
        {
            return null;
        }
        return users.get(0);
    }

    @Override
    public List<User> selectUserById(Long userId) throws SQLException {
        List<User> users = JDBCUtils.query(User.class, "select * from t_user where id=?", userId);
        if(users.isEmpty())
        {
            return null;
        }
        return users;
    }

    @Override
    public void regiser(Long userId, String phone,String userName,String password, String email) {
        JDBCUtilsV2.update("insert into t_user(id,username,password,telephone,email,type) values(?,?,?,?,?,?)"
        ,userId,userName,password,phone,email,1);
    }

    @Override
    public List<User> getAllUser() {
        List<User> users= JDBCUtilsV2.query(User.class,"select * from t_user");
        return users;
    }

    @Override
    public List<User> loginByEmail(String eamil) {
        List<User> user= JDBCUtilsV2.query(User.class,"select * from t_user where email = ?",eamil);
        return user;
    }
}
