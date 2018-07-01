package com.qingguatang.jdbctest.algorithyms;

/**
 * MyQueue的描述:<br> 使用双向数组的方式，实现队列
 *
 * @author apple 2018/6/11 下午3:09
 */
public class MyQueue {

  private String[] dataArray;

  private int front;

  private int back;

  private int captity = 4;


  public MyQueue() {
    dataArray = new String[captity];
    front = 0;
    back = 0;
  }

  /**
   * 需要判断当前的front和back分别是多少
   */
  public void enqueue(String data) {
    dataArray[back] = data;
    back++;
    if (back == captity) {
      back = 0;
    }
    if (front == back) {
      //扩容
      System.out.println("需要扩容");
    }
  }

  public String deque() {
    if (front == back) {
      return null;
    }

    String data = dataArray[front];
    front++;
    if (front == captity) {
      front = 0;
    }
    return data;
  }


}
