package com.qingguatang.jdbctest.dao.api;

import com.qingguatang.jdbctest.dao.model.UserDO;
import com.qingguatang.jdbctest.dao.model.UserQueryParam;
import java.util.List;
import java.util.Map;

/**
 * UserDAO的描述:<br>
 *   对用户信息DB的操作
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
   * 修改用户的属性
   * @param userDO
   * @return
   */
  int update(UserDO userDO);

  /**
   * 通过用户的名字删除用户
   * @param name
   * @return
   */
  int deleteByName(String name);

  /**
   * 通过用户的名字，查找用户
   * @param name
   * @return 可能存在重名用户，返回List类型
   */
  List<UserDO> selectByName(String  name);

  /**
   * 通过参数，查找符合条件的用户
   * @param queryParam
   * @return
   */
  List<UserDO> query(Map queryParam);

}
