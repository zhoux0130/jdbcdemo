package com.qingguatang.jdbctest.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * DBUtil的描述:<br>
 *
 * @author apple 2018/4/21 下午6:33
 */
public class DBUtil {

  private static void setPrepareStatementParams(PreparedStatement preparedStatement,
      List<Object> paramList) throws SQLException {
    if (paramList != null && !paramList.isEmpty()) {
      for (int i = 0; i < paramList.size(); i++) {
        preparedStatement.setObject(i + 1, paramList.get(i));
      }
    }
  }

  public static ResultSet executeQuery(Connection connection, String sql, List<Object> paramList) {
    ResultSet resultSet = null;
    if (connection == null || paramList == null || paramList.isEmpty()) {
      return null;
    }
    PreparedStatement preparedStatement = null;
    try {
      preparedStatement = connection.prepareStatement(sql);
      setPrepareStatementParams(preparedStatement, paramList);

      resultSet = preparedStatement.executeQuery();
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return resultSet;
  }

  public static int executeUpdate(Connection connection, String sql, List<Object> paramList) {
    int result = 0;
    if (connection == null) {
      return result;
    }
    PreparedStatement preparedStatement = null;
    try {
      preparedStatement = connection.prepareStatement(sql);
      setPrepareStatementParams(preparedStatement, paramList);

      result = preparedStatement.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      closeStatement(preparedStatement);
      closeConnection(connection);
    }
    return result;
  }

  private static void closeStatement(PreparedStatement statement) {
    if (statement != null) {
      try {
        statement.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }

  public static void closeResultSet(ResultSet resultSet) {
    if (resultSet == null) {
      return;
    }

    try {
      resultSet.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public static void closeConnection(Connection connection) {
    if (connection == null) {
      return;
    }

    try {
      connection.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public static List<Object> getSqlParamList(Object... args) {
    List paramList = new ArrayList<>();
    if(args[0].getClass().equals(ArrayList.class)){
      paramList = (List) args[0];
    }else{
      paramList.addAll(Arrays.asList(args));
    }

    return paramList;
  }

  public static String getInSql(String inSql, List<String> idList){
    StringBuilder queryBuilder = new StringBuilder(inSql);
    queryBuilder.append("(");
    for ( int i = 0; i < idList.size(); i++) {
      queryBuilder.append( " ?,");
    }
    return queryBuilder.toString().substring(0, queryBuilder.toString().length()-1) + ")";
  }

}
