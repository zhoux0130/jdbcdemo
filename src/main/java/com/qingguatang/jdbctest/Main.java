package com.qingguatang.jdbctest;

import com.qingguatang.jdbctest.dao.AccountDAO;
import com.qingguatang.jdbctest.dataobject.AccountDO;
import com.qingguatang.jdbctest.impl.AccountDAOImpl;
import com.qingguatang.jdbctest.param.AccountQueryParam;
import java.util.List;

/**
 * Main的描述:<br> 测试JDBC的连接
 *
 * @author apple 2018/4/14 下午5:59
 */
public class Main {

  public static void main(String[] args) {
    AccountDAO accountDAO = new AccountDAOImpl();
    String id = "4";
    AccountQueryParam queryParam = new AccountQueryParam();
    List<AccountDO> accountDOList = accountDAO.query(queryParam);
    for (AccountDO accountDO : accountDOList) {
      System.out.println(accountDO.toString());
    }
  }

}
