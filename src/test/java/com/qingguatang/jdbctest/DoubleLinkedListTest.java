package com.qingguatang.jdbctest;

import com.qingguatang.jdbctest.algorithyms.DoubleLinkedList;
import com.qingguatang.jdbctest.algorithyms.DoubleLinkedList.Node;
import org.junit.Test;

/**
 * DoubleLinkedListTest的描述:<br>
 *
 * @author apple 2018/7/29 下午4:36
 */
public class DoubleLinkedListTest {

  @Test
  public void insertTest(){
    DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
    Node a = doubleLinkedList.insert(null, "a");
    Node b = doubleLinkedList.insert(a,"b");
    Node c = doubleLinkedList.insert(a, "c");

    doubleLinkedList.travelList();
  }

}
