package com.qingguatang.jdbctest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * DBManager的描述:<br> 单例模式初始化DB的配置
 *
 * @author apple 2018/4/26 下午3:14
 */
public class DBManager {

  private static DBManager manager = null;

  private static String dbURL;

  private static String user;

  private static String password;

  private static Connection connection;


  private DBManager() {
    // 1. 加载一个mysql的驱动，这里封装了对mysql数据库操作的具体实现
    try {
      // 2. 配置mysql数据库的访问地址，以及用户名及密码
      Properties properties = new Properties();
      properties.load(Main.class.getClassLoader().getResourceAsStream("db-config.properties"));

      dbURL = properties.getProperty("db.url");
      user = properties.getProperty("db.username");
      password = properties.getProperty("db.password");
      String mysqlDriver = properties.getProperty("db.drivername");

      Class.forName(mysqlDriver);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static DBManager getInstance() {
    if (manager == null) {
      manager = new DBManager();
    }
    return manager;
  }

  public Connection getConnection() {
    try {
      connection = DriverManager.getConnection(dbURL, user, password);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return connection;
  }


}
