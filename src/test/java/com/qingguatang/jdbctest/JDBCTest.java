package com.qingguatang.jdbctest;

import java.sql.Connection;
import java.sql.DriverManager;
import org.dbunit.DatabaseTestCase;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.ext.mysql.MySqlConnection;

/**
 * JDBCTest的描述:<br>
 *
 * @author apple 2018/4/14 下午6:02
 */
public class JDBCTest extends DatabaseTestCase{

  @Override
  protected IDatabaseConnection getConnection() throws Exception {
    Class.forName("com.mysql.jdbc.Driver");
    Connection conn = DriverManager.getConnection(
        "jdbc:mysql://localhost:3306/test?user=root");

    return new MySqlConnection(conn, "demo");
  }

  @Override
  protected IDataSet getDataSet() throws Exception {
    return null;
  }


}
