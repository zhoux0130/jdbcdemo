package com.qingguatang.jdbctest.mapper;

import com.qingguatang.jdbctest.dataobject.AccountDO;
import com.qingguatang.jdbctest.param.AccountQueryParam;
import java.util.List;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;

/**
 * AccountMapper的描述:<br>
 *
 * @author apple 2018/4/23 下午3:59
 */
public interface AccountMapper {

  AccountDO getAccount(String id);

  int addAccount(AccountDO accountDO);

  int updateAccount(AccountDO accountDO);

  List<AccountDO> getAllAccountList();

  List<AccountDO> query(AccountQueryParam queryParam);
}
