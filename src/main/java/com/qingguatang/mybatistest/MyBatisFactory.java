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

  private static SqlSession sqlSession;

  static {
    String resource = "mybatis-config.xml";
    InputStream inputStream = null;
    try {
      inputStream = Resources.getResourceAsStream(resource);
    } catch (IOException e) {
      e.printStackTrace();
    }
    SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

    sqlSession = sqlSessionFactory.openSession(true);
  }


  public static SqlSession getSqlSession(){
    return sqlSession;
  }


}
