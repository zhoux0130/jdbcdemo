package com.qingguatang.jdbctest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.qingguatang.jdbctest.dao.AccountDAO;
import com.qingguatang.jdbctest.dataobject.AccountDO;
import com.qingguatang.jdbctest.impl.AccountDAOImpl;
import com.qingguatang.jdbctest.param.AccountQueryParam;
import java.sql.Connection;
import java.util.List;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

/**
 * AccountDAOTest的描述:<br>
 *
 * @author apple 2018/5/7 下午9:12
 */
public class AccountDAOTest {

  private AccountDAO accountDAO;

  @Before
  public void init(){
    accountDAO = new AccountDAOImpl();
  }

  @Test
  public void addNullAccountTest(){
    AccountDO accountDO = null;
    int result = accountDAO.add(accountDO);

    assertTrue(result == 0);
  }

//  @Test
  @Ignore
  public void addAccountTest(){
    AccountDO accountDO = new AccountDO();
    accountDO.setId("6");
    accountDO.setName("test junit");
    accountDO.setType("buyer");
    accountDO.setEmail("buyer@qq.com");
    int result = accountDAO.add(accountDO);

    assertTrue(result > 0);
  }

  @Test
  public void queryTest(){
    AccountQueryParam queryParam = new AccountQueryParam();
    queryParam.setId("1");

    List<AccountDO> accountDOList = accountDAO.query(queryParam);
    assertTrue(accountDOList.size() > 0);
  }


}
