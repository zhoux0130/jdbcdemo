package com.qingguatang.jdbctest;


import com.qingguatang.jdbctest.dao.AccountDAO;
import com.qingguatang.jdbctest.dataobject.AccountDO;
import com.qingguatang.jdbctest.impl.AccountDAOImpl;
import java.util.ArrayList;
import java.util.List;

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
    accountDO.setName("我是一个中国人4");
    accountDO.setType("seller");
    accountDO.setEmail("999rose@qq.com");


    List<AccountDO> accountDOList = new ArrayList();
    accountDOList.add(accountDO);

    AccountDO newAccountDO = new AccountDO();
    newAccountDO.setId("5");
    newAccountDO.setName("我是一个中国人5");
    newAccountDO.setType("seller");
    newAccountDO.setEmail("999rose@qq.com");

    accountDOList.add(newAccountDO);

    accountDAO.batchAdd(accountDOList);
  }
}
