package com.walle.dao.impl;

import com.walle.dao.EmpDao;
import com.walle.pojo.Emp;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 123
 * @create 2022/8/22 22:34
 */
public class EmpDaoImpl implements EmpDao {

    private static String driver = "com.mysql.cj.jdbc.Driver";
    private static String url = "jdbc:mysql://127.0.0.1:3306/mydb?useSSL=false&useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai&allowPublicKeyRetrieval=true";
    private static String user = "root";
    private static String password = "123123";

    public List<Emp> findAll() {
        // 获取链接
        Connection conn = null;
        // 获取语句对象statement
        PreparedStatement preparedStatement = null;
        // 获取结果集
        ResultSet resultSet = null;
        ArrayList<Emp> list = null;
        Emp emp = null;
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, user, password);
            // conn.prepareStatement(sql)可以获得一个预编译语句对象PrepareStatement
            String sql = "select * from emp";
            preparedStatement = conn.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            list = new ArrayList<>();
            while (resultSet.next()) {
                Integer empno = resultSet.getInt("empno");
                String ename = resultSet.getString("ename");
                String job = resultSet.getString("job");
                Integer mgr = resultSet.getInt("mgr");
                Date hiredate = resultSet.getDate("hiredate");
                Double sal = resultSet.getDouble("sal");
                emp = new Emp(empno, ename, job, mgr,hiredate, sal);
                list.add(emp);
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
}
