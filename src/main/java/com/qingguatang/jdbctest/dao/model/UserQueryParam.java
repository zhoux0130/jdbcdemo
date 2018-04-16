package com.qingguatang.jdbctest.dao.model;

/**
 * UserQueryParam的描述:<br>
 *
 * @author joe 2018/4/15 下午4:06
 */
public class UserQueryParam {

  /**
   *  通过id进行用户的查询
   */
  private String id;

  /**
   *  通过名称进行用户的查询
   */
  private String name;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
