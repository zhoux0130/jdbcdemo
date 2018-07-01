package com.qingguatang.jdbctest;

import com.qingguatang.jdbctest.algorithyms.MyLinerList;
import org.junit.Before;
import org.junit.Test;

/**
 * MyLinerListTest的描述:<br>
 *
 * @author apple 2018/5/31 下午7:51
 */
public class MyLinerListTest {

  private MyLinerList linerList;

  @Before
  public void initList(){
    linerList = new MyLinerList();
    linerList.add("1", 1);
    linerList.add("2", 2);
    linerList.add("3",3);
  }


  @Test
  public void testAddLinerList(){
    linerList.add("4", 3);

    for (int i = 0; i < linerList.size(); i++) {
      System.out.println(linerList.get(i));
    }
  }

  @Test
  public void testGet(){
    int index = 0;
    System.out.printf(linerList.get(index));
  }

  @Test
  public void testFind(){
    String data = "3";
    System.out.println(linerList.find(data));

    data = "4";
    System.out.println(linerList.find(data));
  }

  @Test
  public void testRemove(){
    for (int i = 0; i < linerList.size(); i++) {
      System.out.println(linerList.get(i));
    }

    String data = "3";
    linerList.remove(data);

    for (int i = 0; i < linerList.size(); i++) {
      System.out.println(linerList.get(i));
    }
  }

}
