
package com.qingguatang.jdbctest.impl;

import com.mysql.jdbc.StringUtils;
import com.qingguatang.jdbctest.DBManager;
import com.qingguatang.jdbctest.DBUtil;
import com.qingguatang.jdbctest.DataSourceManager;
import com.qingguatang.jdbctest.dao.AccountDAO;
import com.qingguatang.jdbctest.dataobject.AccountDO;
import com.qingguatang.jdbctest.param.AccountQueryParam;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;

/**
 * AccountDAOImpl的描述:<br>
 *
 * @author apple 2018/4/26 下午6:21
 */
public class AccountDAOImpl implements AccountDAO {

  private DBManager dbManager = DBManager.getInstance();

  private DataSourceManager manager = DataSourceManager.getInstance();

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
    List<Object> paramList = new ArrayList<>();
    paramList.add(accountDO.getName());
    paramList.add(accountDO.getEmail());
    paramList.add(accountDO.getType());
    paramList.add(accountDO.getId());

    int result = DBUtil.executeUpdate(connection, updateSql, paramList);

    return result;
  }

  @Override
  public int deleteById(String id) {
    return 0;
  }

  @Override
  public AccountDO getById(String id) {
    Connection connection = dbManager.getConnection();

    String querySql = "select * from account where id = ?";
    PreparedStatement preparedStatement = null;
    AccountDO accountDO = null;
    try {
      preparedStatement = connection.prepareStatement(querySql);
      preparedStatement.setString(1, id);

      ResultSet resultSet = preparedStatement.executeQuery();
      accountDO = new AccountDO();
      if (resultSet.next()) {
        accountDO.setGmtCreated(resultSet.getDate("gmt_created"));
        accountDO.setGmtModified(resultSet.getDate("gmt_modified"));
        accountDO.setId(resultSet.getString("id"));
        accountDO.setName(resultSet.getString("name"));
        accountDO.setType(resultSet.getString("type"));
        accountDO.setEmail(resultSet.getString("email"));
      }

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
    return accountDO;
  }

  @Override
  public List<AccountDO> getByName(String name) {
    return null;
  }

  @Override
  public List<AccountDO> query(AccountQueryParam queryParam) {
    //select * from account where id = ? and  name = ? and
    List<AccountDO> accountDOList = new ArrayList<>();
    Connection connection = manager.getConnection();
    if (connection == null || queryParam == null) {
      return null;
    }

    String querySql = "select * from account ";
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(querySql);
    String id = queryParam.getId();
    String name = queryParam.getName();
    String type = queryParam.getType();
    Map<Integer, String> placeHolderMap = new HashMap<>();
    if (!StringUtils.isNullOrEmpty(id) || !StringUtils.isNullOrEmpty(name) || !StringUtils
        .isNullOrEmpty(type)) {
      stringBuilder.append(" where ");
      Integer index = 1;

      if (!StringUtils.isNullOrEmpty(id)) {
        stringBuilder.append(" id = ? and");
        placeHolderMap.put(index, id);
        index++;
      }

      if (!StringUtils.isNullOrEmpty(name)) {
        stringBuilder.append(" name = ? and");
        placeHolderMap.put(index, name);
        index++;
      }

      if (!StringUtils.isNullOrEmpty(type)) {
        stringBuilder.append(" type = ? and");
        placeHolderMap.put(index, type);
      }

      stringBuilder.delete(stringBuilder.length() - 4, stringBuilder.length());
      querySql = stringBuilder.toString();
    }
    PreparedStatement preparedStatement = null;
    try {
      preparedStatement = connection.prepareStatement(querySql);
      if (!placeHolderMap.isEmpty()) {
        for (Integer key : placeHolderMap.keySet()) {
          preparedStatement.setString(key, placeHolderMap.get(key));
        }
      }
      ResultSet resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        AccountDO accountDO = new AccountDO();
        accountDO.setGmtCreated(resultSet.getDate("gmt_created"));
        accountDO.setGmtModified(resultSet.getDate("gmt_modified"));
        accountDO.setId(resultSet.getString("id"));
        accountDO.setName(resultSet.getString("name"));
        accountDO.setType(resultSet.getString("type"));
        accountDO.setEmail(resultSet.getString("email"));
        accountDOList.add(accountDO);
      }

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

    return accountDOList;

  }
}
