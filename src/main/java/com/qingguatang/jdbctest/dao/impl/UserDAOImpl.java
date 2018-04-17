package com.qingguatang.jdbctest.dao.impl;

import com.qingguatang.jdbctest.DBManager;
import com.qingguatang.jdbctest.dao.api.UserDAO;
import com.qingguatang.jdbctest.dao.model.UserDO;
import com.qingguatang.jdbctest.dao.model.UserQueryParam;
import java.util.List;

/**
 * UserDAOImpl的描述:<br>
 *   完成
 *
 * @author apple 2018/4/15 下午4:07
 */
public class UserDAOImpl implements UserDAO {

  /**
   * 封装MySql的驱动，不需要每个方法都初始化一次驱动
   */
  private DBManager manager = DBManager.createInstance();

  @Override
  public int add(UserDO userDO) {
    return 0;
  }

  @Override
  public int update(UserDO userDO) {
    return 0;
  }

  //TODO: 完成删除逻辑
  @Override
  public int deleteByName(String name) {
    return 0;
  }

  @Override
  public List<UserDO> selectByName(String name) {
    return null;
  }

  //TODO: 完成通过不同参数查询用户
  @Override
  public List<UserDO> query(UserQueryParam queryParam) {
    return null;
  }
}
