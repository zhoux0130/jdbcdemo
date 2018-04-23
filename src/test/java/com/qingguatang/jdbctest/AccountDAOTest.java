package com.qingguatang.jdbctest;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import com.qingguatang.jdbctest.dataobject.AccountDO;
import com.qingguatang.jdbctest.mapper.AccountMapper;
import com.qingguatang.mybatistest.MyBatisFactory;
import org.apache.ibatis.session.SqlSession;
import org.junit.Before;
import org.junit.Test;

/**
 * AccountDAO的描述:<br>
 *   对注册用户信息DB的操作
 *
 * @author apple 2018/4/15 下午4:05
 */
public class AccountDAOTest {

  SqlSession sqlSession;

  @Before
  public void getSession(){
    sqlSession = MyBatisFactory.getSqlSession();
  }

  @Test
  public void testInsert(){
    AccountMapper accountMapper = sqlSession.getMapper(AccountMapper.class);
    AccountDO accountDO = new AccountDO();
    accountDO.setId("1");
    accountDO.setEmail("king8egg@qq.com");
    accountDO.setType("seller");
    accountDO.setName("king8egg");

    int result = accountMapper.addAccount(accountDO);
    assertThat(result > 0);
  }


}
