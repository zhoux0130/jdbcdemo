package com.qingguatang.jdbctest.dao.impl;

import com.qingguatang.jdbctest.DBManager;
import com.qingguatang.jdbctest.dao.api.UserDAO;
import com.qingguatang.jdbctest.dao.model.UserDO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    int result = 0;
    String name = userDO.getName();
    String insertSql = "insert into user(name) values (?)";
    PreparedStatement preparedStatement = null;
    try {
      preparedStatement = connection.prepareStatement(insertSql);
      preparedStatement.setString(1, name);

      result = preparedStatement.executeUpdate();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      try {
        preparedStatement.close();
      } catch (SQLException e) {
        e.printStackTrace();
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
    int result = 0;
    Connection connection = manager.getConnection();
    if (userDO == null || connection == null) {
      return result;
    }
    String updateSql = "update user set name = ? where id = ?";
    PreparedStatement preparedStatement = null;

    try {
      preparedStatement = connection.prepareStatement(updateSql);
      preparedStatement.setString(1, userDO.getName());
      preparedStatement.setInt(2, userDO.getId());

      result = preparedStatement.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {
        preparedStatement.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
      try {
        manager.getConnection().close();
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
    List<UserDO> userDOList = new ArrayList<>();

    String querySql = "select * from user where name = ?";
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    try {
      preparedStatement = manager.getConnection().prepareStatement(querySql);
      preparedStatement.setString(1, name);

      resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        UserDO userDO = new UserDO();
        userDO.setId(resultSet.getInt("id"));
        userDO.setName(resultSet.getString("name"));

        userDOList.add(userDO);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {
        preparedStatement.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }

      try {
        manager.getConnection().close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }

    return userDOList;
  }

  //TODO: 完成通过不同参数查询用户
  @Override
  public List<UserDO> query(Map queryParam) {
    //select * from user where id = ? and name = ?
    Connection connection = manager.getConnection();
    if(queryParam == null || queryParam.isEmpty() || connection == null){
      return null;
    }
    List<UserDO> userDOList = new ArrayList<>();
    StringBuilder whereSQLBuilder = new StringBuilder("select * from user where ");
    List paramList = new ArrayList();

    for(Object key : queryParam.keySet()){
      if(queryParam.get(key) != null){
        String idStr = (String) key;
        whereSQLBuilder.append(idStr);
        whereSQLBuilder.append(" = ? ");
        whereSQLBuilder.append("and ");
        paramList.add(queryParam.get(key));
      }
    }

    String querySQL = whereSQLBuilder.toString();
    querySQL = querySQL.substring(0, querySQL.length() - 4);
    try {
      PreparedStatement preparedStatement = connection.prepareStatement(querySQL);
      for (int i = 0; i < paramList.size(); i++) {
        preparedStatement.setObject(i+1, paramList.get(i));
      }
      ResultSet resultSet = preparedStatement.executeQuery();
      while(resultSet.next()){
         UserDO userDO = new UserDO();
         userDO.setId(resultSet.getInt("id"));
         userDO.setName(resultSet.getString("name"));

         userDOList.add(userDO);
      }

    } catch (SQLException e) {
      e.printStackTrace();
    }

    return userDOList;
  }
}
