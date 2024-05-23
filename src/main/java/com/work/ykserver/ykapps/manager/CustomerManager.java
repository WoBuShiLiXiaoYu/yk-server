package com.work.ykserver.ykapps.manager;

import cn.hutool.core.bean.BeanUtil;
import com.work.ykserver.ykapps.common.CodeEnum;
import com.work.ykserver.ykapps.mapper.ClueMapper;
import com.work.ykserver.ykapps.mapper.CustomerMapper;
import com.work.ykserver.ykapps.pojo.Clue;
import com.work.ykserver.ykapps.pojo.Customer;
import com.work.ykserver.ykapps.pojo.User;
import com.work.ykserver.ykapps.query.CustomerQuery;
import com.work.ykserver.ykapps.util.JWTUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

@Component
public class CustomerManager {

    @Resource
    private CustomerMapper customerMapper;
    @Resource
    private ClueMapper clueMapper;

    @Transactional(rollbackFor = Exception.class)
    public Boolean getConversionCustomerResult(CustomerQuery customerQuery) {
        boolean flag = false;
        // 查询线索状态
        Clue clue = clueMapper.selectById(customerQuery.getClueId());
        if (clue.getState() == -1) {
            throw new RuntimeException("该线索已转换为客户");
        }
        // 添加客户信息
        User loginUser = JWTUtils.parseUserFromJWT(customerQuery.getToken());
        Customer customer = new Customer();
        BeanUtil.copyProperties(customerQuery, customer);
        customer.setCreateBy(loginUser.getId());
        customer.setCreateTime(new Date());
        int insert = customerMapper.insert(customer);
        if (insert != 1) {
            throw new RuntimeException(CodeEnum.CLUE_TO_CUSTOMER_FAIL.getMsg());
        }
        // 将线索状态改变
        int update = clueMapper.updateStateById(customer.getClueId());
        if (update != 1) {
            throw new RuntimeException(CodeEnum.CLUE_TO_CUSTOMER_FAIL.getMsg());
        }
        if (insert >= 1 && update >= 1) {
            flag = true;
        }
        return flag;
    }
}
