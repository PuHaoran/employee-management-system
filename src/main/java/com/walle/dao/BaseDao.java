package com.walle.dao;

import com.walle.pojo.Emp;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 123
 * @create 2022/9/5 23:08
 */
public abstract class BaseDao {
    private static String driver = "com.mysql.cj.jdbc.Driver";
    private static String url = "jdbc:mysql://127.0.0.1:3306/mydb?useSSL=false&useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai&allowPublicKeyRetrieval=true";
    private static String user = "root";
    private static String password = "123123";

    public List baseQuery(Class clazz, String sql, Object ... args) {
        // 获取链接
        Connection conn = null;
        // 获取语句对象statement
        PreparedStatement preparedStatement = null;
        // 获取结果集
        ResultSet resultSet = null;
        List<Object> list = null;
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, user, password);
            // conn.prepareStatement(sql)可以获得一个预编译语句对象PrepareStatement
            preparedStatement = conn.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            list = new ArrayList<>();
            for (int i = 0; i < args.length; i++) {
                preparedStatement.setObject(i+1, args[i]);
            }
            // 根据字节码获取所有属性
            Field[] fields = clazz.getDeclaredFields();
            for (Field field: fields) {
                field.setAccessible(true);
            }
            while (resultSet.next()) {
                // 通过反射创建对象
                Object obj = clazz.newInstance();
                // 临时用field设置字段值
                for (Field field : fields) {
                    String fieldName = field.getName();
                    Object data = resultSet.getObject(fieldName);
                    field.set(obj, data);
                }
                list.add(obj);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != resultSet) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (null != preparedStatement) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (null != conn) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return list;
    }

    public int baseUpdate(String sql, Object ... args) {
        // 获取链接
        Connection conn = null;
        // 获取语句对象statement
        PreparedStatement preparedStatement = null;
        int rows = 0;
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, user, password);
            // conn.prepareStatement(sql)可以获得一个预编译语句对象PrepareStatement
            preparedStatement = conn.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                preparedStatement.setObject(i+1, args[i]);
            }
            rows = preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != preparedStatement) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (null != conn) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return rows;
    }
}
