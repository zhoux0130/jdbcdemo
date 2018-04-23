package com.qingguatang.jdbctest.util;

import java.util.UUID;

/**
 * UUIDUtil的描述:<br>
 *
 * @author apple 2018/4/21 下午5:59
 */
public class UUIDUtil {

  public static String getUUID() {
    return UUID.randomUUID().toString().replace("-", "");
  }

}
