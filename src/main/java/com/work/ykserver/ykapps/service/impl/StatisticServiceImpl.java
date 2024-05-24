package com.work.ykserver.ykapps.service.impl;

import com.work.ykserver.ykapps.manager.StatisticManager;
import com.work.ykserver.ykapps.service.StatisticService;
import com.work.ykserver.ykapps.vo.NameAndValueVO;
import com.work.ykserver.ykapps.vo.SummaryDataVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class StatisticServiceImpl implements StatisticService {
    @Resource
    private StatisticManager statisticManager;

    @Override
    public List<NameAndValueVO> getSourcePieData() {
        return statisticManager.loadSourcePieData();
    }

    @Override
    public List<NameAndValueVO> getSaleFunnelData() {
        return statisticManager.loadSaleFunnelData();
    }

    @Override
    public SummaryDataVO getSummaryData() {
        return statisticManager.loadSummaryData();
    }
}
