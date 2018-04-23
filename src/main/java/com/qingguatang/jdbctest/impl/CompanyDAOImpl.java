package com.qingguatang.jdbctest.impl;

import com.qingguatang.jdbctest.datasource.DataSourceManager;
import com.qingguatang.jdbctest.dao.CompanyDAO;
import com.qingguatang.jdbctest.dataobject.CompanyDO;
import com.qingguatang.jdbctest.util.DBUtil;
import com.qingguatang.jdbctest.util.UUIDUtil;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * CompanyDAOImpl的描述:<br>
 *   对公司模型的DB操作
 *
 * @author apple 2018/4/21 下午7:43
 */
public class CompanyDAOImpl implements CompanyDAO {

  /**
   * 封装MySql的驱动，不需要每个方法都初始化一次驱动
   */
  private DataSourceManager dataSource = DataSourceManager.getInstance();

  private final static String COMPANY_DB = "company";

  @Override
  public int add(CompanyDO companyDO) {
    Connection connection = dataSource.getConnection(COMPANY_DB);
    if (companyDO == null || connection == null) {
      return 0;
    }

    String id = UUIDUtil.getUUID();
    companyDO.setId(id);

    String insertSql = "insert into company (id, name, address, gmt_created, gmt_modified) "
        + "values (?,?,?,NOW(),NOW())";
    List<Object> paramList = DBUtil.getSqlParamList(id, companyDO.getName(), companyDO.getAddress());

    return DBUtil.executeUpdate(connection, insertSql, paramList);
  }

  @Override
  public int update(CompanyDO companyDO) {
    int result = 0;
    Connection connection = dataSource.getConnection(COMPANY_DB);
    if (companyDO == null || connection == null) {
      return result;
    }
    String updateSql = "update account set name = ? and address = ? and gmt_modified = NOW() where id = ?";
    List<Object> paramList = DBUtil.getSqlParamList(companyDO.getName(), companyDO.getAddress(), companyDO.getId());

    return DBUtil.executeUpdate(connection, updateSql, paramList);
  }

  @Override
  public int deleteById(String id) {
    Connection connection = dataSource.getConnection(COMPANY_DB);
    if (connection == null) {
      return 0;
    }

    String deleteSql = "delete from company where id = ?";
    List<Object> paramList = DBUtil.getSqlParamList(id);

    return DBUtil.executeUpdate(connection, deleteSql, paramList);
  }

  @Override
  public List<CompanyDO> selectByName(String name) {
    Connection connection = dataSource.getConnection(COMPANY_DB);
    if (connection == null) {
      return null;
    }

    String querySql = "select * from company where name like ?";
    List<Object> paramList = DBUtil.getSqlParamList("%" + name + "%");

    ResultSet resultSet = DBUtil.executeQuery(connection, querySql, paramList);
    if (resultSet == null) {
      return null;
    }

    List<CompanyDO> companyDOList = generateCompanyDO(resultSet);
    DBUtil.closeConnection(connection);

    return companyDOList;
  }

  @Override
  public CompanyDO selectById(String id) {
    Connection connection = dataSource.getConnection(COMPANY_DB);
    if (connection == null) {
      return null;
    }

    String querySql = "select * from account where id = ?";
    List<Object> paramList = DBUtil.getSqlParamList(id);
    ResultSet resultSet = DBUtil.executeQuery(connection, querySql, paramList);

    if (resultSet == null) {
      return null;
    }
    List<CompanyDO> accountDOList = generateCompanyDO(resultSet);
    if(accountDOList == null || accountDOList.isEmpty()){
      return null;
    }
    DBUtil.closeConnection(connection);

    return accountDOList.get(0);
  }

  @Override
  public List<CompanyDO> selectByIdList(List<String> idList) {
    Connection connection = dataSource.getConnection(COMPANY_DB);
    if (connection == null) {
      return null;
    }

    String querySql = "select * from company where id in";
    querySql = DBUtil.getInSql(querySql, idList);

    List<Object> paramList = DBUtil.getSqlParamList(idList);

    ResultSet resultSet = DBUtil.executeQuery(connection, querySql, paramList);

    if (resultSet == null) {
      return null;
    }
    List<CompanyDO> companyDOList = generateCompanyDO(resultSet);
    DBUtil.closeConnection(connection);

    return companyDOList;
  }

  private List<CompanyDO> generateCompanyDO(ResultSet resultSet){
    List<CompanyDO> accountDOList = new ArrayList<>();
    try {
      while(resultSet.next()){
        CompanyDO companyDO = new CompanyDO();
        companyDO.setAddress(resultSet.getString("address"));
        companyDO.setName(resultSet.getString("name"));
        companyDO.setId(resultSet.getString("id"));
        companyDO.setGmtCreated(resultSet.getDate("gmt_created"));
        companyDO.setGmtModified(resultSet.getDate("gmt_modified"));

        accountDOList.add(companyDO);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      DBUtil.closeResultSet(resultSet);
    }
    return accountDOList;
  }
}
