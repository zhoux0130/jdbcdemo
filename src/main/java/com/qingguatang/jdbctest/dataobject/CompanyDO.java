package com.qingguatang.jdbctest.dataobject;

import java.util.Date;

/**
 * CompanyDO的描述:<br>
 *   公司数据模型
 *
 * @author apple 2018/4/21 下午4:28
 */
public class CompanyDO {

  /**
   * 公司的id，唯一业务主键
   */
  private String id;

  /**
   * 记录创建的时间
   */
  private Date gmtCreated;

  /**
   * 记录最近一次被修改的时间
   */
  private Date gmtModified;

  /**
   * 公司的名字
   */
  private String name;

  /**
   * 公司的地址
   */
  private String address;

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

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  @Override
  public String toString(){
    return "id: " + this.id + ", name: " + this.name + ", type: " + this.address;
  }
}
