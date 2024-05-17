package com.work.ykserver.ykapps.web;

import cn.hutool.core.util.ObjectUtil;
import com.work.ykserver.ykapps.bo.Page;
import com.work.ykserver.ykapps.common.CodeEnum;
import com.work.ykserver.ykapps.service.ActivityService;
import com.work.ykserver.ykapps.util.ResultUtils;
import com.work.ykserver.ykapps.vo.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/activity")
public class ActivityController {

    @Resource
    private ActivityService activityService;

    @GetMapping("/activityList")
    public Result getActivityList(@RequestParam(value = "currentPage", required = false)
                                  Integer currentPage) {
        // 判断当前页是否有值
        if (currentPage == null) {
            currentPage = 1;
        }
        Page page = activityService.getActivityListByPage(currentPage);
        if (ObjectUtil.isEmpty(page)) {
            return ResultUtils.fail(CodeEnum.GET_ACTIVITY_LIST_IS_NULL);
        }
        return ResultUtils.success(CodeEnum.OK.getCode(), "", page);
    }
}
