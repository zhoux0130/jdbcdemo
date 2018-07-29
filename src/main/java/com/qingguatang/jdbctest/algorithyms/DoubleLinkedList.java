package com.qingguatang.jdbctest.algorithyms;

/**
 * DoubleLinkedList的描述:<br> 维护一个双向链表
 *
 * @author apple 2018/7/29 下午4:14
 */
public class DoubleLinkedList {

  private Node firstNode;

  private int size;

  public void delete(Node node) {
    //如果链表中没有任何元素，则不执行任何删除操作
    if (size == 0) {
      return;
    }

    // 如果删除的是头部节点，那么是不需要重置指针的指向的
    Node nextNode = node.next;
    if (node == firstNode) {
      firstNode = nextNode;
    } else {
      //得到待删除节点的前序节点
      Node preNode = node.pre;
      preNode.next = nextNode;
      nextNode.pre = preNode;
    }
    size--;
  }

  public Node insert(Node node, String newData) {
    Node newNode = new Node(newData);
    if (node == null) {
      firstNode = newNode;
      size++;
      return newNode;
    }

    Node nextNode = node.getNext();
    node.next = newNode;
    newNode.pre = node;

    if (nextNode != null) {
      newNode.next = nextNode;
      nextNode.pre = newNode;
    }

    size++;
    return newNode;
  }


  public void travelList() {
    Node node = firstNode;
    do {
      System.out.println(node.data);
      node = node.next;
    } while (node != null);
  }

  public class Node {

    String data;
    Node next;
    Node pre;

    public Node(String data) {
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

    public Node getPre() {
      return pre;
    }

    public void setPre(Node pre) {
      this.pre = pre;
    }
  }

}
