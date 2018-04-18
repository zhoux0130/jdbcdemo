package com.qingguatang.jdbctest;

import com.qingguatang.jdbctest.dao.UserDAO;
import com.qingguatang.jdbctest.impl.UserDAOImpl;
import com.qingguatang.jdbctest.dataobject.UserDO;
import java.util.List;

/**
 * Main的描述:<br>
 *   测试JDBC的连接
 *
 * @author apple 2018/4/14 下午5:59
 */
public class Main {

  public static void main(String[] args) {
    // 将Jack,Rose,Tom等用户写到数据库中
    UserDAO userDAO = new UserDAOImpl();
    UserDO jackDO = new UserDO();
    jackDO.setName("Jack");
    jackDO.setGender("male");
    userDAO.add(jackDO);

    UserDO roseDO = new UserDO();
    roseDO.setName("Rose");
    roseDO.setGender("female");
    userDAO.add(roseDO);

    UserDO tomDO = new UserDO();
    tomDO.setName("Tom");
    tomDO.setGender("male");
    userDAO.add(tomDO);

    //将Rose的名字改为Old Rose
    roseDO.setName("Old Rose");
    roseDO.setId(5);
    userDAO.update(roseDO);

    // 将名字叫Tom的用户删除
    String name = "Tom";
    userDAO.deleteByName(name);

    // 通过selectByName方法，查找名字叫Jack的用户，并且将用户信息打印到控制台中
    name = "Jack";
    List<UserDO> userDOList = userDAO.selectByName(name);
    if(userDOList == null || userDOList.isEmpty()){
      return;
    }
    for (UserDO userDO : userDOList) {
      System.out.println(userDO.toString());
    }

    // TODO: 使用id进行用户搜索
    UserDO user = userDAO.selectById(1);
    System.out.println(user.toString());
  }

}
