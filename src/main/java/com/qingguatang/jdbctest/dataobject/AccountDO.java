package com.qingguatang.jdbctest.dataobject;

import java.util.Date;
import org.apache.ibatis.type.Alias;

/**
 * AccountDO的描述:<br>
 *   注册用户数据模型
 *
 * @author apple 2018/4/21 下午4:25
 */
public class AccountDO {

  /**
   * 用户的id，业务唯一主键
   */
  private String id;

  /**
   * 记录被创建的时间
   */
  private Date gmtCreated;

  /**
   * 记录最近一次被修改的时间
   */
  private Date gmtModified;

  /**
   * 用户的真实姓名
   */
  private String name;

  /**
   * 用户的类型，卖家还是买家
   */
  private String type;

  /**
   * 用户的email地址
   */
  private String email;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
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

  @Override
  public String toString(){
    return "id: " + this.id + ", name: " + this.name + ", type: " + this.type;
  }
}
