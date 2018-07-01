package com.qingguatang.jdbctest.algorithyms;

/**
 * MyArrayList的描述:<br>
 *
 * @author apple 2018/5/18 下午3:56
 */
public class MyLinerList {

  private int size;

  private static final int LIST_INIT_SIZE = 20;

  private int listSize;

  private String[] linerList;

  public MyLinerList(){
    linerList = new String[LIST_INIT_SIZE];
    size = 0;
    listSize = LIST_INIT_SIZE;
  }

  public int size(){
    return size;
  }

  public void add(String data, int index){
    if(size >= listSize){
      allocateList();
    }

    // 将index后面的元素，都向后挪动一位
    for(int i = size; i >= index; i --){
      linerList[i] = linerList[i-1];
    }
    linerList[index-1]=data;
    size++;
  }

  private void allocateList(){
    String[] newList = new String[size * 2];
    // 将之前的数组的内容，copy到新的数组中
    for (int i = 0; i < newList.length; i++) {
      newList[i] = linerList[i];
    }
    listSize = size * 2;
    linerList = newList;
  }

  public String get(int index){
    if(index > size - 1) {
      throw new ArrayIndexOutOfBoundsException();
    }

    return linerList[index];
  }

  public void remove(String data){
    int index = find(data);
    if(index < 0){
      return;
    }

    for(int i = index; i < size -1 ; i ++){
      linerList[i] = linerList[i+1];
    }
    linerList[size-1]=null;
    size--;
  }


  public int find(String data){
    for (int i = 0; i < size; i++) {
      if(data.equals(linerList[i])){
        return i;
      }
    }
    return -1;
  }



}
