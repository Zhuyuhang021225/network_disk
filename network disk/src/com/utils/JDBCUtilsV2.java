package com.utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 数据库工具类
 */
public class JDBCUtilsV2 {

    //创建连接池对象
    private static ComboPooledDataSource dataSource = new ComboPooledDataSource();

    /**
     * 查询数据
     * @param clazz 实体类类型
     * @param sql 语句
     * @param args 参数
     * @param <T> 类型
     * @return
     */
    public static <T> List<T> query(Class clazz,String sql,Object... args) {
        //获得连接对象
        try(Connection conn = dataSource.getConnection()){
            //获得命令对象
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            //设置占位符参数
            for(int i = 0;i < args.length;i++){
                preparedStatement.setObject(i + 1,args[i]);
            }
            //执行查询，获得结果集
            ResultSet resultSet = preparedStatement.executeQuery();
            List<T> list = new ArrayList<>();
            //逐行访问结果集
            while(resultSet.next()){
                //创建对象包装一行数据
                Object obj = clazz.newInstance();
                //获得该类的所有属性
                Field[] fields = clazz.getDeclaredFields();
                //遍历属性
                for(Field field : fields){
                    String name = field.getName();
                    //获得该列的值
                    Object value = resultSet.getObject(name);
                    //将该属性设置为可访问
                    field.setAccessible(true);
                    //给该属性赋值 对象.属性 = 值
                    field.set(obj,castValue(field.getType(),value));
                }
                list.add((T) obj);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 属性值类型转换
     * @param valueType
     * @param value
     * @return
     */
    public static Object castValue(Class valueType,Object value){
        if(valueType == Integer.class){
            return Integer.valueOf(value.toString());
        }
        if(valueType == Byte.class){
            return Byte.valueOf(value.toString());
        }
        if(valueType == Short.class){
            return Short.valueOf(value.toString());
        }
        if(valueType == Long.class){
            return Long.valueOf(value.toString());
        }
        if(valueType == Float.class){
            return Float.valueOf(value.toString());
        }
        if(valueType == Double.class){
            return Double.valueOf(value.toString());
        }
        if(valueType == Boolean.class){
            return Boolean.valueOf(value.toString());
        }
        if(valueType == Character.class){
            return value.toString().charAt(0);
        }
        return value.toString();
    }

    /**
     * 执行增删改操作
     * @param sql
     * @param args
     * @return
     * @throws SQLException
     */
    public static int update(String sql,Object... args) {
        //获得连接对象
        try(Connection conn = dataSource.getConnection()){
            //获得命令对象
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            //设置占位符参数
            for(int i = 0;i < args.length;i++){
                preparedStatement.setObject(i + 1,args[i]);
            }
            //执行
            int rows = preparedStatement.executeUpdate();
            return rows;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
}
