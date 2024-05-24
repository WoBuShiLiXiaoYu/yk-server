package com.work.ykserver.ykapps.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SummaryDataVO {
    // 有效市场活动总数
    private Integer effectiveActivityCount;

    // 市场活动总数
    private Integer totalActivityCount;

    // 线索总数
    private Integer totalClueCount;

    // 客户总数
    private Integer totalCustomerCount;

    // 成功交易额
    private BigDecimal successTranAmount;

    // 总交易额
    private BigDecimal totalTranAmount;
}
