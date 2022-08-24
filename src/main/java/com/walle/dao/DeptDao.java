package com.walle.dao;

import com.walle.pojo.Dept;
import com.walle.pojo.Emp;

import java.util.List;

/**
 * @author 123
 * @create 2022/8/22 22:32
 */
public interface DeptDao {

    /**
     * 查看数据库表格中的所有员工信息
     * @return 所有员工信息封装的一个List<Emp>
     */
    List<Dept> findAll();

    int addDept(Dept dept);
}
