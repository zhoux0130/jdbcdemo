package com.qingguatang.jdbctest;

import com.qingguatang.jdbctest.dao.api.UserDAO;
import com.qingguatang.jdbctest.dao.impl.UserDAOImpl;
import com.qingguatang.jdbctest.dao.model.UserDO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Main的描述:<br>
 *
 * @author apple 2018/4/14 下午5:59
 */
public class Main {

  public static void main(String[] args){
    UserDAO userDAO = new UserDAOImpl();
    UserDO userDO = userDAO.selectById(4);

    System.out.println(userDO.toString());
  }

}
