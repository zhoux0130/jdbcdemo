package com.qingguatang.jdbctest.algorithyms;

import java.util.Arrays;
import java.util.List;

/**
 * SortUtil的描述:<br>
 *
 * @author apple 2018/7/1 下午1:56
 */
public class SortUtil {

  public static boolean isLess(Integer currentData, Integer min){
    return currentData <= min ? true : false;
  }

  public static void exchange(Integer[] list, int indexA, int indexB) {
    Integer aValue = list[indexA];
    list[indexA] = list[indexB];
    list[indexB] = aValue;
  }

  public static void currentStatusHelper(Integer[] list) {
    List sortedList = Arrays.asList(list);
    sortedList.forEach(data -> {
      System.out.print(data + " ");
    });
    System.out.println(" ");
  }

}
