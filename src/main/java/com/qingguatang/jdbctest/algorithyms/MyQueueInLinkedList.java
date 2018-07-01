package com.qingguatang.jdbctest.algorithyms;

/**
 * MyQueueInLinkedList的描述:<br>
 *   使用链式结构实现队列
 *
 * @author apple 2018/6/17 下午5:48
 */
public class MyQueueInLinkedList {

  private Node head;

  private Node tail;

  private int size;

  public void enqueue(String data){
    Node newData = new Node(data);
    Node oldTail = tail;
    if(oldTail != null){
      oldTail.next = newData;
      tail = newData;
    }

    if(isEmpty()){
      head = newData;
      tail = newData;
    }

    size ++;
  }

  public String dequeue(){
    if(isEmpty()){
      return null;
    }
    Node firstNode = head;
    head = firstNode.next;

    size --;
    return firstNode.data;
  }

  public boolean isEmpty(){
    if(head == null){
      return true;
    }
    return false;
  }

  public int size(){
    return size;
  }


  private class Node{
    String data;
    Node next;

    public Node(String data){
      this.data = data;
    }

    public String getData() {
      return data;
    }

    public void setData(String data) {
      this.data = data;
    }

    public Node getNext() {
      return next;
    }

    public void setNext(Node next) {
      this.next = next;
    }
  }

}
