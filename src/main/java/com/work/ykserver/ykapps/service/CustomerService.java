package com.work.ykserver.ykapps.service;

import com.work.ykserver.ykapps.pojo.Customer;
import com.baomidou.mybatisplus.extension.service.IService;
import com.work.ykserver.ykapps.query.CustomerQuery;
import com.work.ykserver.ykapps.vo.CustomerExcel;
import com.work.ykserver.ykapps.vo.Result;

import java.util.List;

/**
* @author 胡国海
* @description 针对表【t_customer(客户表)】的数据库操作Service
* @createDate 2024-05-09 21:34:33
*/
public interface CustomerService extends IService<Customer> {

    Result conversionCustomer(CustomerQuery customerQuery);

    Result getCustomerListByPage(Integer currentPage);

    List<CustomerExcel> getCustomerListByExcel();

    List<CustomerExcel> getCustomerListByIds(String[] ids);
}
