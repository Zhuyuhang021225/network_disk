package com.utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class JDBCUtils {
    private static ComboPooledDataSource dataSource = new ComboPooledDataSource();
    private static QueryRunner queryRunner = new QueryRunner(dataSource);


    public static <T> List<T> query(Class clazz,String sql,Object... args) throws SQLException {
        BeanListHandler<T> handler = new BeanListHandler<T>(clazz);
        List<T> list = queryRunner.query(sql, handler, args);
        return list;
    }

    public static int update(String sql,Object... args) throws SQLException {
        int rows = queryRunner.update(sql, args);
        return rows;
    }
}
