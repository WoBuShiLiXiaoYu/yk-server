package com.work.ykserver.ykapps.manager;

import com.work.ykserver.ykapps.mapper.ActivityMapper;
import com.work.ykserver.ykapps.mapper.ClueMapper;
import com.work.ykserver.ykapps.mapper.CustomerMapper;
import com.work.ykserver.ykapps.mapper.TranMapper;
import com.work.ykserver.ykapps.vo.NameAndValueVO;
import com.work.ykserver.ykapps.vo.SummaryDataVO;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class StatisticManager {

    @Resource
    private ActivityMapper activityMapper;
    @Resource
    private ClueMapper clueMapper;
    @Resource
    private CustomerMapper customerMapper;
    @Resource
    private TranMapper tranMapper;

    public SummaryDataVO loadSummaryData() {
        Integer effectiveActivityCount = activityMapper.selectEffectiveActivityCount();

        Integer totalActivityCount = activityMapper.selectTotalActivityCount();

        Integer totalClueCount = clueMapper.selectTotalClueCount();

        Integer totalCustomerCount = customerMapper.selectTotalCustomerCount();

        BigDecimal successTranAmount = tranMapper.selectSuccessTranAmount();

        BigDecimal totalTranAmount = tranMapper.selectTotalTranAmount();

        SummaryDataVO summaryDataVO = new SummaryDataVO(effectiveActivityCount, totalActivityCount, totalClueCount,
                totalCustomerCount, successTranAmount, totalTranAmount);

        return summaryDataVO;
    }

    public List<NameAndValueVO> loadSaleFunnelData() {
        // 查询线索总人数
        Integer totalClueCount = clueMapper.selectTotalClueCount();
        // 查询客户总人数
        Integer totalCustomerCount = customerMapper.selectTotalCustomerCount();
        // 查询交易总人数
        Integer totalTranCount = tranMapper.selectTotalTranCount();
        // 查询成交总人数
        Integer successTranCount = tranMapper.selectSuccessTranCount();

        NameAndValueVO clue = new NameAndValueVO("线索", totalClueCount);
        NameAndValueVO customer = new NameAndValueVO("客户", totalCustomerCount);
        NameAndValueVO tran = new NameAndValueVO("交易人数", totalTranCount);
        NameAndValueVO successTran = new NameAndValueVO("成交人数", successTranCount);

        List<NameAndValueVO> nameAndValueVOList = new ArrayList<>();
        nameAndValueVOList.add(clue);
        nameAndValueVOList.add(customer);
        nameAndValueVOList.add(tran);
        nameAndValueVOList.add(successTran);

        return nameAndValueVOList;

    }

    public List<NameAndValueVO> loadSourcePieData() {
        return clueMapper.selectClueBySource();
    }
}
