package com.qingguatang.jdbctest.algorithyms;

/**
 * MyStack的描述:<br>
 *
 * @author apple 2018/5/30 下午3:29
 */
public class MyStack {

  private int size;

  private Node first;

  private class Node{
    String data;
    Node next;
  }

  /**
   * 将数据入栈
   * @param data
   */
  public void push(String data){
    Node oldFirst = first;
    Node newNode = new Node();
    newNode.data = data;
    newNode.next = oldFirst;
    size++;
  }

  /**
   * 将数据出栈
   * @return
   */
  public String pop(){
    if(first == null){
      return "";
    }

    String data = first.data;
    first = first.next;
    size--;
    return data;
  }

}
