package com.qingguatang.jdbctest.dao.api;

import com.qingguatang.jdbctest.dao.model.UserDO;
import com.qingguatang.jdbctest.dao.model.UserQueryParam;
import java.util.List;

/**
 * UserDAO的描述:<br>
 *
 * @author apple 2018/4/15 下午4:05
 */
public interface UserDAO {

  /**
   * 添加用户对象到DB
   * @param userDO
   * @return
   */
  int add(UserDO userDO);

  /**
   *
   * @param userDO
   * @return
   */
  int update(UserDO userDO);

  /**
   *
   * @param name
   * @return
   */
  int deleteByName(String name);

  /**
   *
   * @param name
   * @return
   */
  UserDO selectByName(String  name);

  /**
   *
   * @param queryParam
   * @return
   */
  List<UserDO> query(UserQueryParam queryParam);

}
