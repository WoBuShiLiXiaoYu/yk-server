package com.work.ykserver.ykapps.web;

import com.work.ykserver.ykapps.constant.RequestConstants;
import com.work.ykserver.ykapps.query.ActivityRemarkQuery;
import com.work.ykserver.ykapps.service.ActivityRemarkService;
import com.work.ykserver.ykapps.vo.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/activityRemark")
public class ActivityRemarkController {

    @Resource
    private ActivityRemarkService activityRemarkService;

    @PostMapping("/addActivityRemark")
    public Result addActivityRemark(@RequestBody ActivityRemarkQuery activityRemarkQuery,
                                    @RequestHeader(value = RequestConstants.HEADER_TOKEN_NAME) String token) {
        activityRemarkQuery.setToken(token);
        return activityRemarkService.saveActivityRemark(activityRemarkQuery);
    }
}
