package com.qingguatang.jdbctest.impl;

import com.qingguatang.jdbctest.dao.AccountDAO;
import com.qingguatang.jdbctest.dataobject.AccountDO;
import java.util.List;

/**
 * AccountDAOImpl的描述:<br>
 *
 * @author apple 2018/4/26 下午6:21
 */
public class AccountDAOImpl implements AccountDAO {

  @Override
  public int add(AccountDO accountDO) {
    return 0;
  }

  @Override
  public int update(AccountDO accountDO) {
    return 0;
  }

  @Override
  public int deleteById(String id) {
    return 0;
  }

  @Override
  public AccountDO getById(String id) {
    return null;
  }

  @Override
  public List<AccountDO> getByName(String name) {
    return null;
  }
}
