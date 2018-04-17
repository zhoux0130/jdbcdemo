package com.qingguatang.jdbctest;

import com.qingguatang.jdbctest.dao.api.UserDAO;
import com.qingguatang.jdbctest.dao.impl.UserDAOImpl;
import com.qingguatang.jdbctest.dao.model.UserDO;

/**
 * Main的描述:<br>
 *
 * @author apple 2018/4/14 下午5:59
 */
public class Main {

  public static void main(String[] args){
    UserDAO userDAO = new UserDAOImpl();
    UserDO jackDO = new UserDO();
    jackDO.setName("Jack");
    userDAO.add(jackDO);

    UserDO roseDO = new UserDO();
    roseDO.setName("Rose");
    userDAO.add(roseDO);

    UserDO tomDO = new UserDO();
    tomDO.setName("Tom");
    userDAO.add(tomDO);

    // TODO： 将Rose的名字改为Old Rose

    // TODO:  将名字叫Tom的用户删除

    // 通过selectByName方法，查找名字叫Jack的用户，并且将用户信息打印到控制台中

    // TODO： 通过query方法，查找名字叫Jack的用户，并且将用户信息打印到控制台中



  }

}
