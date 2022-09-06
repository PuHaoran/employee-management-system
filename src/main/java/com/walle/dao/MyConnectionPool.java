package com.walle.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 * 实现连接池
 * @author 123
 * @create 2022/9/6 22:14
 */
public class MyConnectionPool {

    private static String driver = "com.mysql.cj.jdbc.Driver";
    private static String url = "jdbc:mysql://127.0.0.1:3306/mydb?useSSL=false&useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai&allowPublicKeyRetrieval=true";
    private static String user = "root";
    private static String password = "123123";
    private static LinkedList<Connection> pool;
    private static Integer initSize = 2;
    private static Integer maxSize = 3;

    static {
        // 加载驱动
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        pool = new LinkedList<>();
        for (int i = 0; i < initSize; i++) {
            Connection connection = initConnection();
            if (null != connection) {
                pool.add(connection);
            }
        }
    }

    /**
     * 初始化连接
     * @return
     */
    private static Connection initConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("初始化链接" + connection.hashCode() + "放入连接池");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    /**
     * 从连接池向外界提供链接对象
     * @return
     */
    public static Connection getConnection() {
        Connection connection = null;
        if (pool.size() > 0) {
            connection = pool.remove();
            System.out.println("获取链接：" + connection.hashCode());
        } else {
            connection = initConnection();
            System.out.println("获取链接，链接池空，创建新链接：" + connection.hashCode());
        }
        return connection;
    }

    /**
     * 向连接池归还链接对象的方法
     * @param connection
     */
    public static void returnConnection(Connection connection) {
        if (null != connection) {
            try {
                if (!connection.isClosed()) {
                    try {
                        connection.setAutoCommit(true);
                        System.out.println("设置链接" + connection.hashCode() + "自动提交为true");

                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                    if (pool.size() < maxSize) {
                        pool.addLast(connection);
                        System.out.println("连接池未满" + connection.hashCode() + "放入连接池");
                    } else {
                        try {
                            System.out.println("连接池已满" + connection.hashCode() + "关闭链接");
                            connection.close();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                } else {
                    System.out.println("链接已关闭，无需归还！");
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }


}
