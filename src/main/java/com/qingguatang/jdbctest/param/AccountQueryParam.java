package com.qingguatang.jdbctest.param;

import java.util.List;

/**
 * AccountQueryParam的描述:<br>
 *   定义用户搜索字段模型
 *
 * @author apple 2018/4/24 上午8:05
 */
public class AccountQueryParam {

  private String id;

  private String name;

  private List<String> idList;

  private String type;


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

  public List<String> getIdList() {
    return idList;
  }

  public void setIdList(List<String> idList) {
    this.idList = idList;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }
}
