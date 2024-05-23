package com.work.ykserver.ykapps.mapper;

import com.work.ykserver.ykapps.bo.Page;
import com.work.ykserver.ykapps.common.DataScope;
import com.work.ykserver.ykapps.pojo.Customer;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.work.ykserver.ykapps.query.CustomerQuery;
import com.work.ykserver.ykapps.vo.CustomerExcel;
import com.work.ykserver.ykapps.vo.CustomerVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author 胡国海
* @description 针对表【t_customer(客户表)】的数据库操作Mapper
* @createDate 2024-05-09 21:34:33
* @Entity com.work.ykserver.ykapps.pojo.Customer
*/
public interface CustomerMapper extends BaseMapper<Customer> {

    @DataScope(myTableAlias = "tct", myTableField = "create_id")
    List<CustomerVO> selectListByPage(@Param("customerQuery") CustomerQuery customerQuery, @Param("page") Page page);

    @DataScope(myTableAlias = "tct", myTableField = "create_id")
    int selectCountByPage(@Param("customerQuery") CustomerQuery customerQuery);

    List<CustomerExcel> selectListByExcel();

    List<CustomerExcel> selectCustomerLsitByIds(@Param("idList") List<Integer> idList);
}




