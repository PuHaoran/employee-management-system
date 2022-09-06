package com.walle.test;

import com.walle.dao.MyConnectionPool;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author 123
 * @create 2022/9/6 22:55
 */
public class Test1 {
    public static void main(String[] args) {
        Connection connection1 = MyConnectionPool.getConnection();
        Connection connection2 = MyConnectionPool.getConnection();
        Connection connection3 = MyConnectionPool.getConnection();
        Connection connection4 = MyConnectionPool.getConnection();
        MyConnectionPool.returnConnection(connection1);
        MyConnectionPool.returnConnection(connection1);
        MyConnectionPool.returnConnection(connection2);
        MyConnectionPool.returnConnection(connection3);
        try {
            connection4.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        MyConnectionPool.returnConnection(connection4);
    }
}
