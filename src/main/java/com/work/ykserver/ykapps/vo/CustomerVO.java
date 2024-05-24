package com.work.ykserver.ykapps.vo;

import com.work.ykserver.ykapps.pojo.Customer;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class CustomerVO extends Customer {

    private Integer clueId;

    /**
     * 负责人
     */
    private String ownerName;
    /**
     * 所属活动
     */
    private String activityName;
    /**
     * 称呼
     */
    private String appellationName;
    /**
     * 是否贷款
     */
    private String isNeedLoan;
    /**
     * 意向状态
     */
    private String intentionStateType;
    /**
     * 意向产品
     */
    private String intentionProductName;
    /**
     * 线索状态
     */
    private String clueState;
    /**
     * 线索来源
     */
    private String clueSource;

    private String phone;
    private String weixin;
    private String fullName;
    private String qq;
    private String email;
    private Integer age;
    private String job;
    private BigDecimal yearIncome;
    private String address;
    private String description;
    private Date nextContactTime;
    private String createName;
    private String editName;

}
