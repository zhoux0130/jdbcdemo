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

  int add(UserDO userDO);

  int update(UserDO userDO);

  int deleteById(Integer id);

  UserDO selectById(Integer id);

  List<UserDO> query(UserQueryParam queryParam);

}
