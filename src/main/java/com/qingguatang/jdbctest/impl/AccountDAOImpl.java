package com.qingguatang.jdbctest.impl;

import com.qingguatang.jdbctest.datasource.DataSourceManager;
import com.qingguatang.jdbctest.datasource.DruidDataSource;
import com.qingguatang.jdbctest.dao.AccountDAO;
import com.qingguatang.jdbctest.dataobject.AccountDO;
import com.qingguatang.jdbctest.util.DBUtil;
import com.qingguatang.jdbctest.util.UUIDUtil;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * AccountDAOImpl的描述:<br>
 *
 * @author apple 2018/4/21 下午4:43
 */
public class AccountDAOImpl implements AccountDAO {

  /**
   * 封装MySql的驱动，不需要每个方法都初始化一次驱动
   */
  private DataSourceManager dataSource = DataSourceManager.getInstance();
//  private DruidDataSource dataSource = DruidDataSource.getDataSource();

  private final static String USER_DB = "user";

  @Override
  public int add(AccountDO accountDO) {
    Connection connection = dataSource.getConnection(USER_DB);
    if (accountDO == null || connection == null) {
      return 0;
    }

    String id = UUIDUtil.getUUID();
    accountDO.setId(id);

    String insertSql = "insert into account(id, name, email, type, gmt_created, gmt_modified) "
        + "values (?,?,?,?,NOW(),NOW())";
    List<Object> paramList = getSqlParamList(id, accountDO.getName(), accountDO.getEmail(),
        accountDO.getType());

    return DBUtil.executeUpdate(connection, insertSql, paramList);
  }

  @Override
  public int update(AccountDO accountDO) {
    int result = 0;
    Connection connection = dataSource.getConnection(USER_DB);
    if (accountDO == null || connection == null) {
      return result;
    }
    String updateSql = "update account set name = ? and email = ? and gmt_modified = NOW() where id = ?";
    List<Object> paramList = getSqlParamList(accountDO.getName(), accountDO.getEmail(), accountDO.getId());

    return DBUtil.executeUpdate(connection, updateSql, paramList);
  }

  @Override
  public int deleteById(String id) {
    Connection connection = dataSource.getConnection(USER_DB);
    if (connection == null) {
      return 0;
    }

    String deleteSql = "delete from account where id = ?";
    List<Object> paramList = getSqlParamList(id);

    return DBUtil.executeUpdate(connection, deleteSql, paramList);
  }

  @Override
  public List<AccountDO> selectByName(String name) {
    Connection connection = dataSource.getConnection(USER_DB);
    if (connection == null) {
      return null;
    }

    String querySql = "select * from account where name = ?";
    List<Object> paramList = getSqlParamList(name);

    ResultSet resultSet = DBUtil.executeQuery(connection, querySql, paramList);
    if (resultSet == null) {
      return null;
    }

    List<AccountDO> accountDOList = generateAccountDO(resultSet);
    DBUtil.closeConnection(connection);

    return accountDOList;
  }

  @Override
  public AccountDO selectById(String id) {
    Connection connection = dataSource.getConnection(USER_DB);
    if (connection == null) {
      return null;
    }

    String querySql = "select * from account where id = ?";
    List<Object> paramList = getSqlParamList(id);
    ResultSet resultSet = DBUtil.executeQuery(connection, querySql, paramList);

    if (resultSet == null) {
      return null;
    }
    List<AccountDO> accountDOList = generateAccountDO(resultSet);
    if(accountDOList == null || accountDOList.isEmpty()){
      return null;
    }
    DBUtil.closeConnection(connection);

    return accountDOList.get(0);
  }

  private List<Object> getSqlParamList(Object... args) {
    List<Object> paramList = new ArrayList<>();
    paramList.addAll(Arrays.asList(args));

    return paramList;
  }

  private List<AccountDO> generateAccountDO(ResultSet resultSet){
    List<AccountDO> accountDOList = new ArrayList<>();
    try {
      while(resultSet.next()){
        AccountDO accountDO = new AccountDO();
        accountDO.setType(resultSet.getString("type"));
        accountDO.setName(resultSet.getString("name"));
        accountDO.setEmail(resultSet.getString("email"));
        accountDO.setId(resultSet.getString("id"));
        accountDO.setGmtCreated(resultSet.getDate("gmt_created"));
        accountDO.setGmtModified(resultSet.getDate("gmt_modified"));

        accountDOList.add(accountDO);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      DBUtil.closeResultSet(resultSet);
    }
    return accountDOList;
  }
}
