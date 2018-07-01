package com.qingguatang.jdbctest;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import javax.sql.DataSource;

/**
 * DataSourceManager的描述:<br>
 *
 * @author apple 2018/5/8 下午2:57
 */
public class DataSourceManager {

  private static DataSource druidMysqlSource;

  private static DataSourceManager manager;

  private DataSourceManager(){
    if (druidMysqlSource == null) {
      Properties properties = new Properties();
      try {
        properties.load(Main.class.getClassLoader().getResourceAsStream("druid-config.properties"));
        druidMysqlSource = DruidDataSourceFactory.createDataSource(properties);


      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

  public static DataSourceManager getInstance(){
    if(manager == null){
      manager = new DataSourceManager();
    }
    return manager;
  }

  public Connection getConnection(){
    Connection connection = null;
    try {
      connection = druidMysqlSource.getConnection();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return connection;
  }
}
