package com.qingguatang.jdbctest;

import com.qingguatang.jdbctest.algorithyms.MyQueue;
import com.qingguatang.jdbctest.algorithyms.MyQueueInLinkedList;
import org.junit.Test;

/**
 * MyStackTest的描述:<br>
 *
 * @author apple 2018/6/12 上午10:02
 */
public class MyQueueTest {

  @Test
  public void testQueueInit(){
    MyQueue myQueue = new MyQueue();
    myQueue.enqueue("a");
    myQueue.enqueue("b");
    myQueue.enqueue("c");

    System.out.println(myQueue.deque());
    System.out.println(myQueue.deque());

    myQueue.enqueue("d");
    myQueue.enqueue("f");
    myQueue.enqueue("e");
    myQueue.enqueue("g");

    System.out.println("l");
  }


  @Test
  public void testLinkedListQueueInit(){
    MyQueueInLinkedList myQueue = new MyQueueInLinkedList();
    myQueue.enqueue("a");
    myQueue.enqueue("b");
    myQueue.enqueue("c");

    System.out.println(myQueue.dequeue());
    System.out.println(myQueue.dequeue());

    myQueue.enqueue("d");
    myQueue.enqueue("f");
    myQueue.enqueue("e");
    myQueue.enqueue("g");

    System.out.println("l");
  }


}
