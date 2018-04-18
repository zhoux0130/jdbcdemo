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
    if(connection == null || userDO == null){
      return 0;
    }
    String insertSql = "insert into user (name) values (?)";
    PreparedStatement statement = null;
    int result = 0;
    try {
      statement = connection.prepareStatement(insertSql);
      statement.setString(1, userDO.getName());

      result = statement.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      if(statement != null){
        try {
          statement.close();
        } catch (SQLException e) {
          e.printStackTrace();
        }
      }
      try {
        connection.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }

    return result;
  }

  @Override
  public int update(UserDO userDO) {
    // update user set name = 'Old Rose' where id = 29
    Connection connection = manager.getConnection();
    if(connection == null || userDO == null){
      return  0;
    }

    String updateSql = "update user set name = ? where id = ?";
    PreparedStatement preparedStatement = null;
    int result = 0;
    try {
      preparedStatement = connection.prepareStatement(updateSql);

      preparedStatement.setString(1, userDO.getName());
      preparedStatement.setInt(2, userDO.getId());

      result = preparedStatement.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      if(preparedStatement != null){
        try {
          preparedStatement.close();
        } catch (SQLException e) {
          e.printStackTrace();
        }
      }
      try {
        connection.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }

    return result;
  }

  //TODO: 完成删除逻辑
  @Override
  public int deleteByName(String name) {

    return 0;
  }

  @Override
  public List<UserDO> selectByName(String name) {
    Connection connection = manager.getConnection();
    List<UserDO> userDOList = new ArrayList<>();
    if(connection == null ){
      return null;
    }

    String selectSql = "select * from user where name = ?";
    PreparedStatement statement = null;
    try {
      statement = connection.prepareStatement(selectSql);
      statement.setString(1, name);

      ResultSet resultSet = statement.executeQuery(selectSql);
      while(resultSet.next()){
        UserDO userDO = new UserDO();
        userDO.setId(resultSet.getInt("id"));
        userDO.setName(resultSet.getString("name"));
        userDOList.add(userDO);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      if(statement!= null){
        try {
          statement.close();
        } catch (SQLException e) {
          e.printStackTrace();
        }
      }

      try {
        connection.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }

    return userDOList;
  }

  //TODO: 完成executeQuery方法的练习
  @Override
  public UserDO selectById(Integer id) {
    return null;
  }

}
