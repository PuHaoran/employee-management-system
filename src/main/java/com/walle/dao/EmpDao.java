package com.walle.dao;

import com.walle.pojo.Emp;

import java.util.List;

/**
 * @author 123
 * @create 2022/8/22 22:32
 */
public interface EmpDao {

    /**
     * 查看数据库表格中的所有员工信息
     * @return 所有员工信息封装的一个List<Emp>
     */
    List<Emp> findAll();

    /**
     * 根据员工编号删除员工信息的方法
     * @param empno 要删除的员工编号
     * @return 删除成功返回大于0的整数，失败返回0
     */
    int deleteByEmpno(int empno);

    /**
     * 根据员工编号修改员工其他所有字段的方法
     * @param emp 员工编号和其他7个字段封装的emp对象
     * @return 修改成功返回大于0的整数，失败返回0
     */
    int updateEmp(Emp emp);

    int addEmp(Emp emp);

}
