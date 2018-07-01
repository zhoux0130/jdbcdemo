package com.qingguatang.jdbctest;

import static org.junit.Assert.assertTrue;

import com.qingguatang.jdbctest.algorithyms.MyLinerList;
import com.qingguatang.jdbctest.algorithyms.MyLinkedList;
import com.qingguatang.jdbctest.algorithyms.MyLinkedList.Node;
import java.util.Iterator;
import org.junit.Before;
import org.junit.Test;

/**
 * MyLinkedListTest的描述:<br>
 *
 * @author apple 2018/5/21 下午7:43
 */
public class MyLinkedListTest {

  @Test
  public void testAddLinkedList(){

    MyLinkedList linkedList = new MyLinkedList();
    linkedList.add("A");
    linkedList.add("B");
    linkedList.add("C");

    Iterator<String> iterator = linkedList.iterator();
    while (iterator.hasNext()){
      System.out.println(iterator.next());
    }

    assertTrue(!linkedList.isEmpty());
    assertTrue(linkedList.size() > 0);

    Node theNode = linkedList.findNode("C");
    linkedList.remove(theNode);

    assertTrue(linkedList.size() == 2);
  }



}
