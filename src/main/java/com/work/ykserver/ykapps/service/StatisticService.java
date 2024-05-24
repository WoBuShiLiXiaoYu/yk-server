package com.work.ykserver.ykapps.service;


import com.work.ykserver.ykapps.vo.NameAndValueVO;
import com.work.ykserver.ykapps.vo.SummaryDataVO;

import java.util.List;

public interface StatisticService {
    SummaryDataVO getSummaryData();

    List<NameAndValueVO> getSaleFunnelData();

    List<NameAndValueVO> getSourcePieData();

}
