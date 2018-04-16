package com.qingguatang.jdbctest.dao.model;

/**
 * UserDO的描述:<br>
 *
 * @author apple 2018/4/15 下午3:56
 */
public class UserDO {

  private Integer id;

  private String name;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString(){
    return "id: " + this.id + ", name: " + this.name;
  }
}
