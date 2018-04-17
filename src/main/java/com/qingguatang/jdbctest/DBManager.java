package com.qingguatang.jdbctest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * DBManager的描述:<br>
 *   单例模式，完成DB的驱动初始化
 *
 * @author apple 2018/4/16 上午11:07
 */
public class DBManager {

    private static DBManager manager = null;

    private String driver;

    private String userName;

    private String dbURL;

    private DBManager(){
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

    public static DBManager createInstance(){
      if(manager == null){
        manager = new DBManager();
        manager.initDB();
      }
      return manager;
    }

    private void initDB(){
      try {
        Class.forName(this.driver);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

    public Connection getConnection(){
      Connection connection = null;
      try {
        connection = DriverManager.getConnection(this.dbURL, this.userName, null);
      } catch (SQLException e) {
        e.printStackTrace();
      }
      return connection;
    }

    public void closeConnection(Connection connection){
      if(connection == null){
        return;
      }

      try {
        connection.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }

}
