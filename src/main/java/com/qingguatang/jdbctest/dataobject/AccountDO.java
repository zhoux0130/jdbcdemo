
package com.qingguatang.jdbctest.dataobject;

import java.util.Date;

/**
 * AccountDO的描述:<br>
 *   注册用户DO模型
 *
 * @author apple 2018/4/26 下午6:18
 */
public class AccountDO {

  /**
   * 用户的业务主键，唯一的id
   */
  private String id;

  /**
   * 用户的名称
   */
  private String name;

  /**
   * 用户的类型，卖家还是买家
   */
  private String type;

  /**
   * 用户注册的email
   */
  private String email;

  /**
   * 记录被创建的时间
   */
  private Date gmtCreated;

  /**
   * 记录最近一次被修改的时间
   */
  private Date gmtModified;

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

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Date getGmtCreated() {
    return gmtCreated;
  }

  public void setGmtCreated(Date gmtCreated) {
    this.gmtCreated = gmtCreated;
  }

  public Date getGmtModified() {
    return gmtModified;
  }

  public void setGmtModified(Date gmtModified) {
    this.gmtModified = gmtModified;
  }

  @Override
  public String toString() {
    return "id:" + this.id + ", name:" + this.name + ", email:" + this.email;
  }
}
