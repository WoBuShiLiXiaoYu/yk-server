package com.work.ykserver.ykapps.util;

import cn.hutool.core.util.ObjectUtil;
import com.work.ykserver.ykapps.constant.CharConstants;
import com.work.ykserver.ykapps.vo.CustomerExcel;

public class CustomerExcelUtils {
    private CustomerExcelUtils() {}

    // 将对象中的 null 值转换为 '' 字符串
    public static CustomerExcel customerExcelNullConversion(CustomerExcel customerExcel) {
        customerExcel.setOwnerName(ObjectUtil.isEmpty(customerExcel.getOwnerName()) ? CharConstants.EMPTY : customerExcel.getOwnerName());
        customerExcel.setActivityName(ObjectUtil.isEmpty(customerExcel.getActivityName()) ? CharConstants.EMPTY : customerExcel.getActivityName());
        customerExcel.setFullName(ObjectUtil.isEmpty(customerExcel.getFullName()) ? CharConstants.EMPTY : customerExcel.getFullName());
        customerExcel.setAppellationName(ObjectUtil.isEmpty(customerExcel.getAppellationName()) ? CharConstants.EMPTY : customerExcel.getAppellationName());
        customerExcel.setPhone(ObjectUtil.isEmpty(customerExcel.getPhone()) ? CharConstants.EMPTY : customerExcel.getPhone());
        customerExcel.setWeixin(ObjectUtil.isEmpty(customerExcel.getWeixin()) ? CharConstants.EMPTY : customerExcel.getWeixin());
        customerExcel.setQq(ObjectUtil.isEmpty(customerExcel.getQq()) ? CharConstants.EMPTY : customerExcel.getQq());
        customerExcel.setEmail(ObjectUtil.isEmpty(customerExcel.getEmail()) ? CharConstants.EMPTY : customerExcel.getEmail());
        customerExcel.setJob(ObjectUtil.isEmpty(customerExcel.getJob()) ? CharConstants.EMPTY : customerExcel.getJob());
        customerExcel.setAddress(ObjectUtil.isEmpty(customerExcel.getAddress()) ? CharConstants.EMPTY : customerExcel.getAddress());
        customerExcel.setNeedLoadName(ObjectUtil.isEmpty(customerExcel.getNeedLoadName()) ? CharConstants.EMPTY : customerExcel.getNeedLoadName());
        customerExcel.setDescription(ObjectUtil.isEmpty(customerExcel.getDescription()) ? CharConstants.EMPTY : customerExcel.getDescription());

        return customerExcel;
    }
}
