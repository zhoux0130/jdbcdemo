package com.qingguatang.jdbctest.dao;

import com.qingguatang.jdbctest.dataobject.CompanyDO;
import java.util.List;

/**
 * CompanyDAO的描述:<br>
 *   对注册公司信息DB的操作
 *
 * @author apple 2018/4/15 下午4:05
 */
public interface CompanyDAO {

  /**
   * 新建公司，companyDO对象需要包含主键
   */
  int add(CompanyDO companyDO);

  /**
   * 修改公司的属性
   */
  int update(CompanyDO companyDO);

  /**
   * 通过主键删除公司
   */
  int deleteById(String id);

  /**
   * 通过公司的名字，查找公司
   * 模糊查找，可能存在多个匹配的公司，返回List类型
   */
  List<CompanyDO> selectByName(String name);

  /**
   * 通过主键，查找公司
   */
  CompanyDO selectById(String id);

  /**
   * 通过公司的主键List，查询对应的公司对象
   */
  List<CompanyDO> selectByIdList(List<String> idList);


  /**
   * 通过参数，查找符合条件的用户
   * @param queryParam
   * @return
   */
//  List<UserDO> query(Map queryParam);

}
