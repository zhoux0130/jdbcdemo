package com.qingguatang.jdbctest.datasource;

import java.sql.Connection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import org.apache.commons.dbcp.BasicDataSource;

/**
 * DataSourceManager的描述:<br>
 *
 * @author apple 2018/4/19 上午11:28
 */
public class DataSourceManager {

  private static DataSourceManager manager = null;

  private BasicDataSource userDataSource;

  private BasicDataSource companyDataSource;

  private Map<String, BasicDataSource> dataSourceMap = new HashMap<>();

  private final static String USER_DB = "user";

  private final static String COMPANY_DB = "company";

  private DataSourceManager(){
    userDataSource = new BasicDataSource();

    Properties properties = new Properties();
    try {
      properties.load(DBManager.class.getClassLoader().getResourceAsStream("db.properties"));
    } catch (Exception e) {
      e.printStackTrace();
    }

    userDataSource.setUrl(properties.getProperty("mysql.userdb.url"));
    userDataSource.setUsername(properties.getProperty("mysql.userdb.username"));

    userDataSource.setMinIdle(5);
    userDataSource.setMaxIdle(20);

    dataSourceMap.put(USER_DB, userDataSource);

    companyDataSource = new BasicDataSource();
    companyDataSource.setUrl(properties.getProperty("mysql.companydb.url"));
    companyDataSource.setUsername(properties.getProperty("mysql.companydb.username"));

    companyDataSource.setMinIdle(5);
    companyDataSource.setMaxIdle(20);
    dataSourceMap.put(COMPANY_DB, companyDataSource);
  }

  public static DataSourceManager getInstance(){
    if(manager == null){
      manager = new DataSourceManager();
    }
    return manager;
  }

  public Connection getConnection(String dbName) {
    try{
      long t1 = (new Date()).getTime();
      if(dataSourceMap.get(dbName) == null){
        return null;
      }
      Connection connection = dataSourceMap.get(dbName).getConnection();
      long t2 = (new Date()).getTime();
      long diff = t2 - t1;
      System.out.println(diff);
      return connection;
    }catch (Exception e){
      e.printStackTrace();
    }
    return null;
  }

}
