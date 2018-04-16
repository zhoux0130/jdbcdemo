package com.qingguatang.jdbctest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * Main的描述:<br>
 *
 * @author apple 2018/4/14 下午5:59
 */
public class Main {

  public static void main(String[] args){
    try {
      // 1. 加载一个mysql的驱动，这里封装了对mysql数据库操作的具体实现
      Class.forName("com.mysql.jdbc.Driver");

      // 2. 配置mysql数据库的访问地址，以及用户名及密码
      String dbURL = "jdbc:mysql://localhost:3306/test";
      String userName = "root";
      String password = "";

      // 3. 创建与数据库中的连接通道
      Connection connection = DriverManager.getConnection(dbURL, userName, password);

      // 4. 使用JDBC的方式创建一个SQL
      String createUserTableSQL = "CREATE TABLE `user1` (\n"
          + "  `id` int(11) NOT NULL AUTO_INCREMENT,\n"
          + "  `name` varchar(45) DEFAULT NULL,\n"
          + "  PRIMARY KEY (`id`)\n"
          + ") ";
      Statement statement = connection.createStatement();

      // 5. 执行SQL，并且封装了DB中的返回
      int result = statement.executeUpdate(createUserTableSQL);

      // 6. 关闭创建的客户端与DB的连接
      statement.close();
      connection.close();
    } catch (Exception e) {
      e.printStackTrace();
    }

  }

}
