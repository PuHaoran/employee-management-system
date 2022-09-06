package com.walle.dao.impl;

import com.walle.dao.BaseDao;
import com.walle.dao.EmpDao;
import com.walle.pojo.Emp;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 123
 * @create 2022/8/22 22:34
 */
public class EmpDaoImpl extends BaseDao implements EmpDao {

    public List<Emp> findAll() {
        String sql = "select * from emp";
        return baseQuery(Emp.class, sql);
    }

    @Override
    public int deleteByEmpno(int empno) {
        String sql = "delete from emp where empno=?";
        return baseUpdate(sql, empno);
    }

    @Override
    public int updateEmp(Emp emp) {
        String sql = "update emp set ename=?, job=?, mgr=?, hiredate=?, sal=?, comm=?, deptno=? where empno=?";
        return baseUpdate(sql, emp.getEname(), emp.getJob(), emp.getMgr(), emp.getHiredate(), emp.getSal(), emp.getComm(), emp.getDeptno(), emp.getEmpno());
    }

    @Override
    public int addEmp(Emp emp) {
        String sql = "insert into emp (empno, ename, job, mgr, hiredate, sal, comm, deptno) values (?, ?, ?, ?, ?, ?, ?, ?)";
        return baseUpdate(sql, emp.getEmpno(), emp.getEname(), emp.getJob(), emp.getMgr(), emp.getHiredate(), emp.getSal(), emp.getComm(), emp.getDeptno());
    }
}
