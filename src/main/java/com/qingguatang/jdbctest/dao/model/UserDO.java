package com.qingguatang.jdbctest.dao.model;

/**
 * UserDO的描述:<br>
 *
 * @author apple 2018/4/15 下午3:56
 */
public class UserDO {

  /**
   * 用户的业务主键，唯一id
   */
  private Integer id;

  /**
   * 用户的名字
   */
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
