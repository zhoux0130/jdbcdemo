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
    accountDO.setId("4");
    accountDO.setName("999");
    accountDO.setType("seller");
    accountDO.setEmail("999@qq.com");
    accountDAO.update(accountDO);

    int n = 10;
    for (int i = 0; i < n; i++) {
      for(int j = 0; j < n/2; j ++){
        doSomething(i,j);
      }
    }


    for (int i = 0; i < n; i++) {
        doSomething(i);
    }

  }

  public static void doSomething(int i){}


  public static void doSomething(int i, int j){}
}
