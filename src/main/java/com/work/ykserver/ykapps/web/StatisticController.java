package com.work.ykserver.ykapps.web;

import com.work.ykserver.ykapps.service.StatisticService;
import com.work.ykserver.ykapps.util.ResultUtils;
import com.work.ykserver.ykapps.vo.Result;
import com.work.ykserver.ykapps.vo.NameAndValueVO;
import com.work.ykserver.ykapps.vo.SummaryDataVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/statistic")
public class StatisticController {
    @Resource
    private StatisticService statisticService;

    @GetMapping("/summary/getData")
    public Result getSummaryData() {
        SummaryDataVO summaryDataVO = statisticService.getSummaryData();
        return ResultUtils.success(summaryDataVO);
    }

    @GetMapping("/saleFunnel/getSaleFunnelData")
    public Result getSaleFunnelData() {
        List<NameAndValueVO> saleFunnelList = statisticService.getSaleFunnelData();
        return ResultUtils.success(saleFunnelList);
    }

    @GetMapping("/sourcePie/getSourcePieData")
    public Result getSourcePieData() {
        List<NameAndValueVO> sourcePieList = statisticService.getSourcePieData();
        return ResultUtils.success(sourcePieList);
    }
}
