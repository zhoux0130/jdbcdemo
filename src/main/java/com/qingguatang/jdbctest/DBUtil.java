package com.qingguatang.jdbctest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * DBUtil的描述:<br>
 *
 * @author apple 2018/5/3 下午7:40
 */
public class DBUtil {

  public static int executeUpdate(Connection connection, String sql, List<Object> paramList)
      {
    PreparedStatement preparedStatement = null;
    int result = 0;
    try {
      preparedStatement = connection.prepareStatement(sql);
      if (paramList != null && !paramList.isEmpty()) {
        for (int i = 0; i < paramList.size(); i++) {
          preparedStatement.setObject(i + 1, paramList.get(i));
        }
      }

      result = preparedStatement.executeUpdate();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      closeStatement(preparedStatement);
      closeConnection(connection);
    }
    return result;
  }

  public static void closeStatement(PreparedStatement preparedStatement) {
    if (preparedStatement != null) {
      try {
        preparedStatement.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }

  public static void closeConnection(Connection connection) {
    if (connection != null) {
      try {
        connection.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }

}
