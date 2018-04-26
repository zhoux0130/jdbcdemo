package com.qingguatang.jdbctest.dao;

import com.qingguatang.jdbctest.dataobject.AccountDO;
import java.util.List;

/**
 * AccountDAO的描述:<br>
 *
 * @author apple 2018/4/26 下午6:17
 */
public interface AccountDAO {

  /**
   * 添加用户
   */
  int add(AccountDO accountDO);

  /**
   * 更新用户，需要传入完整的用户模型
   */
  int update(AccountDO accountDO);

  /**
   * 通过主键，删除用户
   */
  int deleteById(String id);

  /**
   * 通过主键查询用户
   */
  AccountDO getById(String id);

  /**
   * 通过用户名称查找用户
   */
  List<AccountDO> getByName(String name);

}
