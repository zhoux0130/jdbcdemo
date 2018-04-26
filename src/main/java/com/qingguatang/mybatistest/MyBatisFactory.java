package com.qingguatang.mybatistest;

import java.io.IOException;
import java.io.InputStream;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 * MyBatisFactory的描述:<br>
 *
 * @author apple 2018/4/23 下午3:50
 */
public class MyBatisFactory {


  private static SqlSessionFactory sqlSessionFactory;

  static {
    String resource = "mybatis-config.xml";
    InputStream inputStream = null;
    try {
      inputStream = Resources.getResourceAsStream(resource);
    } catch (IOException e) {
      e.printStackTrace();
    }
    sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
  }


  public static SqlSession getSqlSession(Boolean notAutoCommit){
    SqlSession sqlSession = null;
    if(notAutoCommit == null || Boolean.FALSE.equals(notAutoCommit)){
      sqlSession = sqlSessionFactory.openSession(true);
    }else{
      sqlSession = sqlSessionFactory.openSession();
    }

    return sqlSession;
  }


}
