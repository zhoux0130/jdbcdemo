package com.qingguatang.jdbctest.algorithyms;

import java.util.Arrays;
import java.util.List;

/**
 * Selection的描述:<br> 选择排序的实现
 *
 * @author apple 2018/6/29 下午3:43
 */
public class Selection {

  public static void sort(Integer[] list) {

    int size = list.length;
    for (int i = 0; i < size; i++) {
      int minIndex = i;
      for (int j = i + 1; j < size; j++) {
        if(SortUtil.isLess(list[j],list[minIndex])){
          minIndex = j;
        }
      }
      SortUtil.exchange(list, i, minIndex);
      SortUtil.currentStatusHelper(list);
    }

  }

  public static void main(String[] args) {
    Integer[] unsortedList = new Integer[]{3, 4, 7, 9, 8, 2};

    sort(unsortedList);
    List sortedList = Arrays.asList(unsortedList);
    sortedList.forEach(data -> {
      System.out.print(data + " ");
    });
  }

}
