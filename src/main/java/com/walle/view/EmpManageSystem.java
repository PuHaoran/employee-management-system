package com.walle.view;

import com.walle.dao.EmpDao;
import com.walle.dao.impl.EmpDaoImpl;
import com.walle.pojo.Emp;

import java.util.List;
import java.util.Scanner;

/**
 * @author 123
 * @create 2022/8/22 22:25
 */
public class EmpManageSystem {

    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            showMenu();
            System.out.println("请录入选项");
            int option = sc.nextInt();
            switch (option) {
                case 1:
                    EmpDao empDao = new EmpDaoImpl();
                    List<Emp> list = empDao.findAll();
                    list.forEach(System.out::println);
                    break;
                case 2: break;
                case 3: break;
                case 4: break;
                case 5: break;
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
