package com.qingguatang.jdbctest.param;

import java.util.List;

/**
 * AccountQueryParam的描述:<br>
 *
 * @author apple 2018/5/3 下午2:11
 */
public class AccountQueryParam {

  private String id;

  private String type;

  private String name;

  private List<String> idList;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
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
}
