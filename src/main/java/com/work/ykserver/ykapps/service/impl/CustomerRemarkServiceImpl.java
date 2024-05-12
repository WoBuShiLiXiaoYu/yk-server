package com.work.ykserver.ykapps.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.work.ykserver.ykapps.pojo.CustomerRemark;
import com.work.ykserver.ykapps.service.CustomerRemarkService;
import com.work.ykserver.ykapps.mapper.CustomerRemarkMapper;
import org.springframework.stereotype.Service;

/**
* @author 胡国海
* @description 针对表【t_customer_remark(客户跟踪记录表)】的数据库操作Service实现
* @createDate 2024-05-09 21:34:33
*/
@Service
public class CustomerRemarkServiceImpl extends ServiceImpl<CustomerRemarkMapper, CustomerRemark>
    implements CustomerRemarkService{

}




