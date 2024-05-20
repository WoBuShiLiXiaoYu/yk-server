package com.work.ykserver.ykapps.vo;

import com.work.ykserver.ykapps.pojo.Clue;
import lombok.Data;

@Data
public class ClueVO extends Clue {
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


}
