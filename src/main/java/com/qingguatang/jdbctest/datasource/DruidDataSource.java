package com.qingguatang.jdbctest.datasource;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.Properties;
import javax.sql.DataSource;
import org.apache.ibatis.datasource.DataSourceFactory;

/**
 * DruidDataSource的描述:<br>
 *
 * @author apple 2018/4/23 上午10:25
 */
public class DruidDataSource implements DataSourceFactory {

  private DataSource dataSource;

  @Override
  public void setProperties(Properties properties) {
    try {
      properties.load(DruidDataSource.class.getClassLoader().getResourceAsStream("druid_db.properties"));
    } catch (Exception e) {
      e.printStackTrace();
    }

    if (dataSource == null) {
      try {
        dataSource = DruidDataSourceFactory.createDataSource(properties);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

  @Override
  public DataSource getDataSource() {
    return this.dataSource;
  }

}