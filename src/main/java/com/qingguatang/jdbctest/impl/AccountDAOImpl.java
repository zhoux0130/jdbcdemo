package com.qingguatang.jdbctest.impl;

import com.qingguatang.jdbctest.DBManager;
import com.qingguatang.jdbctest.dao.AccountDAO;
import com.qingguatang.jdbctest.dataobject.AccountDO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * AccountDAOImpl的描述:<br>
 *
 * @author apple 2018/4/26 下午6:21
 */
public class AccountDAOImpl implements AccountDAO {

  private DBManager dbManager = DBManager.getInstance();

  @Override
  public int add(AccountDO accountDO) {
    // 1. 获取到与DB的连接
    Connection connection = dbManager.getConnection();
    if (connection == null || accountDO == null) {
      return 0;
    }

    // 2. 提供待执行的sql
    String id = accountDO.getId();
    String name = accountDO.getName();
    String type = accountDO.getType();
    String email = accountDO.getEmail();
    String insertSql =
        "insert into account(id, name ,type, email, gmt_created, gmt_modified) values ('" + id
            + "', '" + name + "', '" + type + "', '" + email + "', now(),now());";

    // 3. 使用statement语句，使sql在DB层被执行
    int result = 0;
    Statement statement = null;
    try {
      statement = connection.createStatement();
      result = statement.executeUpdate(insertSql);
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      if (statement != null) {
        try {
          statement.close();
        } catch (SQLException e) {
          e.printStackTrace();
        }
      }
      if (connection != null) {
        try {
          connection.close();
        } catch (SQLException e) {
          e.printStackTrace();
        }
      }
    }

    return result;
  }

  @Override
  public int[] batchAdd(List<AccountDO> accountDOList) {
    Connection connection = dbManager.getConnection();
    if (connection == null || accountDOList == null || accountDOList.isEmpty()) {
      return null;
    }
    int[] result = null;
    PreparedStatement preparedStatement = null;
    try {
      connection.setAutoCommit(false);
      String insertSql = "insert into account (id, name, type, email, gmt_created, gmt_modified) values (?,?,?,?, now(),now())";
      preparedStatement = connection.prepareStatement(insertSql);

      for (AccountDO accountDO : accountDOList) {
        preparedStatement.setString(1, accountDO.getId());
        preparedStatement.setString(2, accountDO.getName());
        preparedStatement.setString(3, accountDO.getType());
        preparedStatement.setString(4, accountDO.getEmail());

        preparedStatement.addBatch();
      }

      result = preparedStatement.executeBatch();
      connection.commit();
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      if (preparedStatement != null) {
        try {
          preparedStatement.close();
        } catch (SQLException e) {
          e.printStackTrace();
        }
      }
      try {
        connection.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }

    return result;
  }

  @Override
  public int update(AccountDO accountDO) {
    Connection connection = dbManager.getConnection();
    if (connection == null || accountDO == null) {
      return 0;
    }
    String updateSql = "update account set name = ?, email = ?, type = ?, gmt_modified = now() where id = ?";
    int result = 0;
    PreparedStatement preparedStatement = null;
    try {
      preparedStatement = connection.prepareStatement(updateSql);
      preparedStatement.setString(1, accountDO.getName());
      preparedStatement.setString(3, accountDO.getType());
      preparedStatement.setString(2, accountDO.getEmail());
      preparedStatement.setString(4, accountDO.getId());

      result = preparedStatement.executeUpdate();

    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      if (preparedStatement != null) {
        try {
          preparedStatement.close();
        } catch (SQLException e) {
          e.printStackTrace();
        }
      }
      try {
        connection.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }

    return result;
  }

  @Override
  public int deleteById(String id) {
    return 0;
  }

  @Override
  public AccountDO getById(String id) {
    return null;
  }

  @Override
  public List<AccountDO> getByName(String name) {
    return null;
  }
}
