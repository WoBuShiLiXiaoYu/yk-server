package com.work.ykserver.ykapps.web;

import cn.hutool.core.util.ObjectUtil;
import com.work.ykserver.ykapps.bo.Page;
import com.work.ykserver.ykapps.common.CodeEnum;
import com.work.ykserver.ykapps.constant.RequestConstants;
import com.work.ykserver.ykapps.query.ActivityQuery;
import com.work.ykserver.ykapps.service.ActivityService;
import com.work.ykserver.ykapps.util.ResultUtils;
import com.work.ykserver.ykapps.vo.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/activity")
public class ActivityController {

    @Resource
    private ActivityService activityService;

    @GetMapping("/activityList")
    public Result getActivityList(@RequestParam(value = "currentPage", required = false)
                                  Integer currentPage, ActivityQuery activityQuery) {
        // 判断当前页是否有值
        if (currentPage == null) {
            currentPage = 1;
        }
        Page page = activityService.getActivityListByPage(currentPage, activityQuery);
        if (ObjectUtil.isEmpty(page)) {
            return ResultUtils.fail(CodeEnum.GET_ACTIVITY_LIST_IS_NULL);
        }
        return ResultUtils.success(CodeEnum.OK.getCode(), "", page);
    }

    @PostMapping("/addActivity")
    public Result addActivity(ActivityQuery activityQuery, @RequestHeader(value = RequestConstants.HEADER_TOKEN_NAME) String token) {
        if (ObjectUtil.isEmpty(activityQuery)) {
            return ResultUtils.fail(CodeEnum.PARAMETERS_IS_NULL);
        }
        activityQuery.setToken(token);
        return activityService.saveActivity(activityQuery);
    }

    @GetMapping("/getActivityInfo/{id}")
    public Result getActivityInfo(@PathVariable(value = "id") Integer id) {
        if (ObjectUtil.isEmpty(id)) {
            return ResultUtils.fail(CodeEnum.PARAMETERS_IS_NULL);
        }
        return activityService.getActivityById(id);
    }

    @PutMapping("/editActivity")
    public Result editActivity(ActivityQuery activityQuery, @RequestHeader(value = RequestConstants.HEADER_TOKEN_NAME) String token) throws Exception {
        if (ObjectUtil.isEmpty(activityQuery)) {
            return ResultUtils.fail(CodeEnum.PARAMETERS_IS_NULL);
        }
        activityQuery.setToken(token);
        return activityService.editActivity(activityQuery);
    }

    @GetMapping("/activityDetailInfo/{id}")
    public Result getActivityDetailInfo(@PathVariable(value = "id") Integer id) {
        if (ObjectUtil.isEmpty(id)) {
            return ResultUtils.fail(CodeEnum.PARAMETERS_IS_NULL);
        }
        return activityService.getActivityDetailInfoById(id);
    }

    @DeleteMapping("/deleteActivity")
    public Result deleteActivity(@RequestParam(value = "id") Integer id) {
        if (ObjectUtil.isEmpty(id)) {
            return ResultUtils.fail(CodeEnum.PARAMETERS_IS_NULL);
        }
        return activityService.deleteActivityById(id);
    }

    @DeleteMapping("/batchDeleteActivity")
    public Result batchDeleteActivity(@RequestParam(value = "ids") String[] ids) {
        if (ObjectUtil.isEmpty(ids)) {
            return ResultUtils.fail(CodeEnum.PARAMETERS_IS_NULL);
        }
        return activityService.batchDeleteActivityByIds(ids);
    }
}
