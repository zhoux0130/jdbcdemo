package com.qingguatang.jdbctest;


import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Main的描述:<br>
 *   测试JDBC的连接
 *
 * @author apple 2018/4/14 下午5:59
 */
public class Main {

  public static void main(String[] args) {
    Connection connection = null;
    Statement statement = null;
    try {
      DBManager dbManager = DBManager.getInstance();
      connection = dbManager.getConnection();

      // 4. 使用JDBC的方式创建一个SQL
      String createTableSql = "CREATE TABLE `account` (\n"
          + "`id` int(11) NOT NULL COMMENT '用户的id，业务主键',\n"
          + "`gmt_created` datetime NOT NULL COMMENT '记录创建的时间',\n"
          + "`gmt_modified` datetime NOT NULL COMMENT '记录最近修改的时间',\n"
          + "`name` varchar(45) DEFAULT NULL COMMENT '用户的名字',\n"
          + "`type` varchar(45) DEFAULT NULL COMMENT '用户的类型，卖家还是买家',\n"
          + "`email` varchar(255) DEFAULT NULL COMMENT '用户注册的email地址',\n"
          + "PRIMARY KEY (`id`)\n"
          + ")  ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='记录用户信息，测试使用';";

      // 5. 执行SQL，并且封装了DB中的返回
      statement = connection.createStatement();
      int result = statement.executeUpdate(createTableSql);

    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      // 6. 关闭创建的客户端与DB的连接
      if(statement != null){
        try {
          statement.close();
        } catch (SQLException e) {
          e.printStackTrace();
        }
      }
      if(connection != null){
        try {
          connection.close();
        } catch (SQLException e) {
          e.printStackTrace();
        }
      }
    }



  }

}
