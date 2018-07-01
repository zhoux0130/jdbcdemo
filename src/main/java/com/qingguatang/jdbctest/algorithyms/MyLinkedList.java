package com.qingguatang.jdbctest.algorithyms;

import java.util.Iterator;

/**
 * MyLinkedList的描述:<br> 基于代码的对概念进行讲解
 *
 * @author apple 2018/5/21 下午6:46
 */
public class MyLinkedList {

  private Node firstNode;

  private int size;


  public int size() {
    return size;
  }

  public boolean isEmpty() {
    return size() == 0;
  }


  public Node add(String data) {
    if(firstNode == null){
      Node newNode = new Node(data, null);
      firstNode = newNode;
      size++;
      return newNode;
    }

    return addAfter(firstNode, data);
  }

  /**
   * 在链表的某个节点前面添加元素
   */
  public Node addAfter(Node node, String data) {
    if (node == null) {
      return null;
    }

    Node nextNode = node.next;
    Node newNode = new Node(data, nextNode);
    node.next = newNode;
    size++;
    return newNode;
  }

  /**
   * 找到某个节点的上一个节点
   */
  public Node finePreNode(Node node) {
    Node preNode = null;
    for (Node currentNode = firstNode; currentNode != null; currentNode = currentNode.next) {
      if (node.getData().equals(currentNode.getData())) {
        return preNode;
      }
      preNode = currentNode;
    }
    return null;
  }

  /**
   * c 从链表的开头寻找对应的节点是否存在 如果存在则返回对应的节点，否则返回一个空
   */
  public Node findNode(String data) {
    for (Node currentNode = firstNode; currentNode != null; currentNode = currentNode.next) {
      if (data.equals(currentNode.getData())) {
        return currentNode;
      }
    }
    return null;
  }

  public void remove(Node node) {
    Node preNode = finePreNode(node);
    if (preNode == null) {
      firstNode = node.next;
      return;
    }

    preNode.next = node.next;
    size--;
  }


  public Iterator<String> iterator(){
    return new LinkedListIterator();
  }

  private class LinkedListIterator implements  Iterator<String>{

    private Node currentNode;

    private int currentIndex;

    public LinkedListIterator(){
        currentNode = firstNode;
        currentIndex = 1;
    }

    @Override
    public boolean hasNext() {
      return this.currentIndex <= size;
    }

    @Override
    public String next() {
      if(!hasNext()){
        return null;
      }
      String data = currentNode.getData();
      currentNode = currentNode.next;
      this.currentIndex = this.currentIndex + 1;
      return data;
    }

    @Override
    public void remove() {
    }
  }

  public static class Node {

    private String data;
    private Node next;

    public Node(String data, Node nextNode) {
      this.data = data;
      this.next = nextNode;
    }

    public String getData() {
      return data;
    }

    public Node getNext() {
      return next;
    }
  }

}
