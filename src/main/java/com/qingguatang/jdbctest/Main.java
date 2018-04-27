package com.qingguatang.jdbctest;


import com.qingguatang.jdbctest.dao.AccountDAO;
import com.qingguatang.jdbctest.dataobject.AccountDO;
import com.qingguatang.jdbctest.impl.AccountDAOImpl;

/**
 * Main的描述:<br> 测试JDBC的连接
 *
 * @author apple 2018/4/14 下午5:59
 */
public class Main {

  public static void main(String[] args) {
    AccountDAO accountDAO = new AccountDAOImpl();
    AccountDO accountDO = new AccountDO();
    accountDO.setId("3");
    accountDO.setName("我是一个中国人???");
    accountDO.setType("seller");
    accountDO.setEmail("999rose@qq.com");

    int result = accountDAO.update(accountDO);
    System.out.println("update result: " + result);

  }
}
