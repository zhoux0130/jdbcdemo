package com.qingguatang.jdbctest.impl;

import com.qingguatang.jdbctest.DBManager;
import com.qingguatang.jdbctest.dao.UserDAO;
import com.qingguatang.jdbctest.dataobject.UserDO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * UserDAOImpl的描述:<br> 完成
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
    Connection connection = manager.getConnection();
    if (userDO == null || connection == null) {
      return 0;
    }
    String name = userDO.getName();
    String gender = userDO.getGender();
    List<Object> paramList = new ArrayList<>();
    paramList.add(name);
    paramList.add(gender);

    String insertSql = "insert into user(name,gender) values (?,?)";
    return manager.executeUpdate(connection, insertSql, paramList);
  }

  @Override
  public int update(UserDO userDO) {
    int result = 0;
    Connection connection = manager.getConnection();
    if (userDO == null || connection == null) {
      return result;
    }
    List<Object> paramList = new ArrayList<>();
    paramList.add(userDO.getName());
    paramList.add(userDO.getId());

    String updateSql = "update user set name = ? where id = ?";
    return manager.executeUpdate(connection, updateSql, paramList);
  }

  @Override
  public int deleteByName(String name) {
    Connection connection = manager.getConnection();
    if (connection == null) {
      return 0;
    }

    List<Object> paramList = new ArrayList<>();
    paramList.add(name);
    String deleteSql = "delete from user where name = ?";
    return manager.executeUpdate(connection, deleteSql, paramList);
  }

  @Override
  public List<UserDO> selectByName(String name) {
    Connection connection = manager.getConnection();
    if (connection == null) {
      return null;
    }

    List<Object> paramList = new ArrayList<>();
    paramList.add(name);
    String querySql = "select * from user where name = ?";
    ResultSet resultSet = manager.executeQuery(connection, querySql, paramList);
    if (resultSet == null) {
      return null;
    }

    List<UserDO> userDOList = generateResult(resultSet);
    manager.closeConnection(connection);

    return userDOList;
  }

  @Override
  public UserDO selectById(Integer id) {
    Connection connection = manager.getConnection();
    if (connection == null) {
      return null;
    }

    String querySql = "select * from user where id = ?";
    UserDO userDO = new UserDO();
    try {
      PreparedStatement statement = connection.prepareStatement(querySql);
      statement.setInt(1, id);

      ResultSet resultSet = statement.executeQuery();
      List<UserDO> userDOList = generateResult(resultSet);
      if(userDOList != null && !userDOList.isEmpty()){
        userDO = userDOList.get(0);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      manager.closeConnection(connection);
    }

    return userDO;
  }

  private List<UserDO> generateResult(ResultSet resultSet){
    List<UserDO> userDOList = new ArrayList<>();
    try {
      while(resultSet.next()){
        UserDO userDO = new UserDO();
        userDO.setName(resultSet.getString("name"));
        userDO.setId(resultSet.getInt("id"));
        userDO.setGender(resultSet.getString("gender"));
        userDOList.add(userDO);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      manager.closeResultSet(resultSet);
    }
    return userDOList;
  }

}
