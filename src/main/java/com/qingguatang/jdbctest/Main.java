package com.qingguatang.jdbctest;

import com.qingguatang.jdbctest.dao.AccountDAO;
import com.qingguatang.jdbctest.dao.CompanyDAO;
import com.qingguatang.jdbctest.dataobject.AccountDO;
import com.qingguatang.jdbctest.dataobject.CompanyDO;
import com.qingguatang.jdbctest.datasource.DruidDataSource;
import com.qingguatang.jdbctest.impl.AccountDAOImpl;
import com.qingguatang.jdbctest.impl.CompanyDAOImpl;
import com.qingguatang.jdbctest.mapper.AccountMapper;
import com.qingguatang.jdbctest.util.UUIDUtil;
import com.qingguatang.mybatistest.MyBatisFactory;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.mapping.ResultMap;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

/**
 * Main的描述:<br>
 *   测试JDBC的连接
 *
 * @author apple 2018/4/14 下午5:59
 */
public class Main {

  public static void main(String[] args) {
    // 将Jack,Rose,Tom等用户写到数据库中
//    UserDAO userDAO = new UserDAOImpl();
//    UserDO jackDO = new UserDO();
//    jackDO.setName("Jack");
//    jackDO.setGender("male");
//    userDAO.add(jackDO);
//
//    UserDO roseDO = new UserDO();
//    roseDO.setName("Rose");
//    roseDO.setGender("female");
//    userDAO.add(roseDO);
//
//    UserDO tomDO = new UserDO();
//    tomDO.setName("Tom");
//    tomDO.setGender("male");
//    userDAO.add(tomDO);
//
//    //将Rose的名字改为Old Rose
//    roseDO.setName("Old Rose");
//    roseDO.setId(5);
//    userDAO.update(roseDO);
//
//    // 将名字叫Tom的用户删除
//    String name = "Tom";
//    userDAO.deleteByName(name);
//
//    // 通过selectByName方法，查找名字叫Jack的用户，并且将用户信息打印到控制台中
//    name = "Jack";
//    List<UserDO> userDOList = userDAO.selectByName(name);
//    if(userDOList == null || userDOList.isEmpty()){
//      return;
//    }
//    for (UserDO userDO : userDOList) {
//      System.out.println(userDO.toString());
//    }
//
//    //使用id进行用户搜索
//    UserDO user = userDAO.selectById(1);
//    System.out.println(user.toString());
//
//    int sum = 0;
//    long t1 = (new Date()).getTime();
//    for (int i = 0; i < 1000000; i++) {
//      sum += i;
//    }
//
//    long t2 = (new Date()).getTime();
//    long diff = t2 - t1;
//    String str = "for循环执行时间:" + String.valueOf(diff);
//    System.out.println(str);

//    AccountDAO accountDAO = new AccountDAOImpl();
//    AccountDO accountDO = new AccountDO();
//    accountDO.setEmail("test1@qq.com");
//    accountDO.setName("王八蛋");
//    accountDO.setType("buyer");
//
//    accountDAO.add(accountDO);
//
//    accountDO = accountDAO.selectById("6beac433965d492f90c76301fee8e7df");
//    System.out.println(accountDO.toString());
//
//    CompanyDAO companyDAO = new CompanyDAOImpl();
//    CompanyDO companyDO = new CompanyDO();
//    companyDO.setAddress("杭州");
//    companyDO.setName("未达");
////    companyDAO.add(companyDO);
//
//    List<String> idList = new ArrayList<>();
//    idList.add("27ddaee9dead40368aa60a18c1cefe4f");
//    idList.add("dd16f52319e8459bbc19438dc2154968");
//
//    List<CompanyDO> companyDOList = companyDAO.selectByIdList(idList);
//    for (CompanyDO cDO : companyDOList) {
//      System.out.println(cDO.toString());
//    }


    SqlSession session = MyBatisFactory.getSqlSession();
    AccountMapper accountMapper = session.getMapper(AccountMapper.class);
    AccountDO accountDO = accountMapper.getAccount("6beac433965d492f90c76301fee8e7df");
//    System.out.println(accountDO.toString());

    String id = UUIDUtil.getUUID();
    AccountDO newAccountDO = new AccountDO();
    newAccountDO.setName("还是个王八蛋");
    newAccountDO.setType("seller");
    newAccountDO.setEmail("king8egg@qq.com");
    newAccountDO.setId(id);

    accountMapper.addAccount(newAccountDO);

//    newAccountDO = accountMapper.getAccount(id);
//    System.out.println(newAccountDO);

    List<AccountDO> accountDOList = accountMapper.getAllAccountList();
    for (AccountDO aDo : accountDOList) {
      System.out.println(aDo.toString());
    }


  }


}
