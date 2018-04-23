package com.qingguatang.jdbctest.dao;

import com.qingguatang.jdbctest.dataobject.AccountDO;
import java.util.List;

/**
 * AccountDAO的描述:<br>
 *   对注册用户信息DB的操作
 *
 * @author apple 2018/4/15 下午4:05
 */
public interface AccountDAO {

  /**
   * 新建用户，account对象需要包含主键
   */
  int add(AccountDO accountDO);

  /**
   * 修改用户的属性
   */
  int update(AccountDO accountDO);

  /**
   * 通过主键删除用户
   */
  int deleteById(String id);

  /**
   * 通过用户的名字，查找用户
   * @return 可能存在重名用户，返回List类型
   */
  List<AccountDO> selectByName(String name);

  /**
   * 通过主键，查找用户
   */
  AccountDO selectById(String id);


  /**
   * 通过参数，查找符合条件的用户
   * @param queryParam
   * @return
   */
//  List<UserDO> query(Map queryParam);

}
