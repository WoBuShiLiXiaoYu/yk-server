package com.work.ykserver.ykapps.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.work.ykserver.ykapps.bo.Page;
import com.work.ykserver.ykapps.common.CodeEnum;
import com.work.ykserver.ykapps.manager.CustomerManager;
import com.work.ykserver.ykapps.pojo.Customer;
import com.work.ykserver.ykapps.query.CustomerQuery;
import com.work.ykserver.ykapps.service.CustomerService;
import com.work.ykserver.ykapps.mapper.CustomerMapper;
import com.work.ykserver.ykapps.util.CustomerExcelUtils;
import com.work.ykserver.ykapps.util.PageUtils;
import com.work.ykserver.ykapps.util.ResultUtils;
import com.work.ykserver.ykapps.vo.CustomerExcel;
import com.work.ykserver.ykapps.vo.CustomerVO;
import com.work.ykserver.ykapps.vo.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
* @author 胡国海
* @description 针对表【t_customer(客户表)】的数据库操作Service实现
* @createDate 2024-05-09 21:34:33
*/
@Service
public class CustomerServiceImpl extends ServiceImpl<CustomerMapper, Customer>
    implements CustomerService{

    @Resource
    private CustomerManager customerManager;
    @Resource
    private CustomerMapper customerMapper;

    @Override
    public Result conversionCustomer(CustomerQuery customerQuery) {
        Boolean result = customerManager.getConversionCustomerResult(customerQuery);
        if (!result) {
            return ResultUtils.fail(CodeEnum.CLUE_TO_CUSTOMER_FAIL);
        }
        return ResultUtils.success(CodeEnum.OK);
    }

    @Override
    public Result getCustomerListByPage(Integer currentPage) {
        Page page = PageUtils.getPage(currentPage);
        CustomerQuery customerQuery = new CustomerQuery();
        // 查询客户列表
        List<CustomerVO> customerVOList = customerMapper.selectListByPage(customerQuery, page);
        // 查询总记录条数
        int total = customerMapper.selectCountByPage(customerQuery);

        page = PageUtils.pageSetting(page,customerVOList, total);
        return ResultUtils.success(page);
    }

    @Override
    public List<CustomerExcel> getCustomerListByExcel() {
        List<CustomerExcel> customerExcelList = customerMapper.selectListByExcel();
        List<CustomerExcel> customerExcels = new ArrayList<>();
        for (CustomerExcel customerExcel : customerExcelList) {
            CustomerExcel nullConversion = CustomerExcelUtils.customerExcelNullConversion(customerExcel);
            customerExcels.add(nullConversion);
        }
        return customerExcels;
    }

    @Override
    public List<CustomerExcel> getCustomerListByIds(String[] ids) {
        List<Integer> idList = Arrays.stream(ids).map(Integer::parseInt).collect(Collectors.toList());
        List<CustomerExcel> customerExcelList = customerMapper.selectCustomerLsitByIds(idList);
        List<CustomerExcel> customerExcels = new ArrayList<>();
        for (CustomerExcel customerExcel : customerExcelList) {
            CustomerExcel nullConversion = CustomerExcelUtils.customerExcelNullConversion(customerExcel);
            customerExcels.add(nullConversion);
        }
        return customerExcels;
    }
}




