package com.qingguatang.jdbctest.dao.impl;

import com.qingguatang.jdbctest.DBManager;
import com.qingguatang.jdbctest.dao.api.UserDAO;
import com.qingguatang.jdbctest.dao.model.UserDO;
import com.qingguatang.jdbctest.dao.model.UserQueryParam;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import javax.swing.plaf.nimbus.State;

/**
 * UserDAOImpl的描述:<br>
 *
 * @author apple 2018/4/15 下午4:07
 */
public class UserDAOImpl implements UserDAO {

  private DBManager manager = DBManager.createInstance();

  @Override
  public int add(UserDO userDO) {
    Connection connection = manager.getConnection();
    // 如果主键是自增的，则可以不用显示的为其赋值
    String addSQL = "INSERT INTO user(name) values (?)";
    Object[] params = new Object[]{userDO.getName()};

    // 将对象的参数，写入sql中
    PreparedStatement preparedStatement = null;
    int result = 0;
    try {
      preparedStatement = connection.prepareStatement(addSQL);
      if(params.length > 0){
        for (int index = 1; index <= params.length; index ++) {
          preparedStatement.setObject(index, params[index-1]);
        }
      }
      result = preparedStatement.executeUpdate();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      try {
        preparedStatement.close();
        connection.close();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

    return result;
  }

  @Override
  public int update(UserDO userDO) {
    Connection connection = manager.getConnection();
    String updateSQL = "UPDATE user SET name = ? WHERE id = ?";

    String userName = userDO.getName();
    Integer userId = userDO.getId();

    PreparedStatement preparedStatement = null;
    int result = 0;
    try {
      preparedStatement = connection.prepareStatement(updateSQL);
      preparedStatement.setString(1, userName);
      preparedStatement.setInt(2, userId);

      result = preparedStatement.executeUpdate();
    }catch (Exception e){
      e.printStackTrace();
    }
    return result;
  }

  @Override
  public int deleteByName(String name) {
    Connection connection = manager.getConnection();
    String deleteSQL = "DELETE FROM user where name = ?";

    PreparedStatement preparedStatement = null;
    int result = 0;
    try {
      preparedStatement = connection.prepareStatement(deleteSQL);
      preparedStatement.setString(1, name);

      result = preparedStatement.executeUpdate();
    }catch (Exception e){
      e.printStackTrace();
    }
    return result;
  }

  @Override
  public UserDO selectByName(String name) {
    Connection connection = manager.getConnection();
    String selectSQL = "SELECT * FROM user where name = ?";

    PreparedStatement preparedStatement = null;
    UserDO userDO = new UserDO();
    try {
      preparedStatement = connection.prepareStatement(selectSQL);
      preparedStatement.setString(1, name);

      ResultSet result = preparedStatement.executeQuery();

      while(result.next()){
        userDO.setId(result.getInt("id"));
        userDO.setName(result.getString("name"));
      }
    }catch (Exception e){
      e.printStackTrace();
    }
    return userDO;
  }

  @Override
  public List<UserDO> query(UserQueryParam queryParam) {
    return null;
  }
}
