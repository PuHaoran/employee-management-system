package com.walle.view;

import com.walle.dao.DeptDao;
import com.walle.dao.EmpDao;
import com.walle.dao.impl.DeptDaoImpl;
import com.walle.dao.impl.EmpDaoImpl;
import com.walle.pojo.Dept;
import com.walle.pojo.Emp;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 * @author 123
 * @create 2022/8/22 22:25
 */
public class EmpManageSystem {

    private static Scanner sc = new Scanner(System.in);

    private static EmpDao empDao = new EmpDaoImpl();
    private static DeptDao deptDao = new DeptDaoImpl();

    public static void case1() {
        List<Emp> empList = empDao.findAll();
        empList.forEach(System.out::println);
    }

    public static void case2() {
        List<Dept> deptList = deptDao.findAll();
        deptList.forEach(System.out::println);
    }

    public static void case3() {
        System.out.println("请输入要删除的员工编号");
        int empno = sc.nextInt();
        empDao.deleteByEmpno(empno);
    }

    public static void case4() {
        System.out.println("请输入员工编号：");
        Integer empno = sc.nextInt();
        System.out.println("请输入员工姓名：");
        String ename = sc.next();
        System.out.println("请输入员工职位：");
        String job = sc.next();
        System.out.println("请输入员工上级：");
        Integer mgr = sc.nextInt();
        System.out.println("请输入员工入职日期：");
        Date hiredate = null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            hiredate = simpleDateFormat.parse(sc.next());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println("请输入员工工资：");
        Double sal = sc.nextDouble();
        System.out.println("请输入员工补助：");
        Double comm = sc.nextDouble();
        System.out.println("请输入员工部门号：");
        Integer deptno = sc.nextInt();

        Emp emp = new Emp(empno, ename, job, mgr, hiredate, sal, comm, deptno);
        empDao.updateEmp(emp);
    }

    public static void case5() {
        System.out.println("请输入员工编号：");
        Integer empno = sc.nextInt();
        System.out.println("请输入员工姓名：");
        String ename = sc.next();
        System.out.println("请输入员工职位：");
        String job = sc.next();
        System.out.println("请输入员工上级：");
        Integer mgr = sc.nextInt();
        System.out.println("请输入员工入职日期：");
        Date hiredate = null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            hiredate = simpleDateFormat.parse(sc.next());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println("请输入员工工资：");
        Double sal = sc.nextDouble();
        System.out.println("请输入员工补助：");
        Double comm = sc.nextDouble();
        System.out.println("请输入员工部门号：");
        Integer deptno = sc.nextInt();

        Emp emp = new Emp(empno, ename, job, mgr, hiredate, sal, comm, deptno);
        empDao.addEmp(emp);
    }

    public static void main(String[] args) {
        while (true) {
            showMenu();
            System.out.println("请录入选项");
            int option = sc.nextInt();
            switch (option) {
                case 1:
                    case1();
                    break;
                case 2:
                    case2();
                    break;
                case 3:
                    case3();
                    break;
                case 4:
                    case4();
                    break;
                case 5:
                    case5();
                    break;
                case 6: break;
                case 7: break;
                default:
                    System.out.println("请正确输入选项");
            }
        }

    }
    public static void showMenu() {
        System.out.println("*****************************");
        System.out.println("* 1 查看所有员工信息");
        System.out.println("* 2 查看所有部门信息");
        System.out.println("* 3 根据工号删除员工信息");
        System.out.println("* 4 根据工号修改员工信息");
        System.out.println("* 5 增加员工信息");
        System.out.println("* 6 增加部门信息");
        System.out.println("* 7 退出");
    }
}
