package com.work.ykserver.ykapps.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.work.ykserver.ykapps.pojo.Customer;
import com.work.ykserver.ykapps.service.CustomerService;
import com.work.ykserver.ykapps.mapper.CustomerMapper;
import org.springframework.stereotype.Service;

/**
* @author 胡国海
* @description 针对表【t_customer(客户表)】的数据库操作Service实现
* @createDate 2024-05-09 21:34:33
*/
@Service
public class CustomerServiceImpl extends ServiceImpl<CustomerMapper, Customer>
    implements CustomerService{

}




