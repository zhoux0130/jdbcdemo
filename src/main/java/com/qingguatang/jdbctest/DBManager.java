package com.qingguatang.jdbctest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

/**
 * DBManager的描述:<br> 单例模式，完成DB的驱动初始化
 *
 * @author apple 2018/4/16 上午11:07
 */
public class DBManager {

  private static DBManager manager = null;

  private String driver;

  private String userName;

  private String dbURL;

  private DBManager() {
    Properties properties = new Properties();
    try {
      properties.load(DBManager.class.getClassLoader().getResourceAsStream("db.properties"));
    } catch (Exception e) {
      e.printStackTrace();
    }
    this.driver = properties.getProperty("driver");
    this.dbURL = properties.getProperty("url");
    this.userName = properties.getProperty("username");
  }

  public static DBManager createInstance() {
    if (manager == null) {
      manager = new DBManager();
      manager.initDB();
    }
    return manager;
  }

  private void initDB() {
    try {
      Class.forName(this.driver);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public Connection getConnection() {
    Connection connection = null;
    try {
      connection = DriverManager.getConnection(this.dbURL, this.userName, null);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return connection;
  }

  private void setPrepareStatementParams(PreparedStatement preparedStatement,
      List<Object> paramList) throws SQLException {
    if (paramList != null && !paramList.isEmpty()) {
      for (int i = 0; i < paramList.size(); i++) {
        preparedStatement.setObject(i + 1, paramList.get(i));
      }
    }
  }

  public ResultSet executeQuery(Connection connection, String sql, List<Object> paramList) {
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

  public int executeUpdate(Connection connection, String sql, List<Object> paramList) {
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

  private void closeStatement(PreparedStatement statement) {
    if (statement != null) {
      try {
        statement.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }

  public void closeConnection(Connection connection) {
    if (connection == null) {
      return;
    }

    try {
      connection.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }


}
