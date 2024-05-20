package com.work.ykserver.ykapps.web;

import cn.hutool.core.util.ObjectUtil;
import com.work.ykserver.ykapps.common.CodeEnum;
import com.work.ykserver.ykapps.constant.RequestConstants;
import com.work.ykserver.ykapps.query.ActivityRemarkQuery;
import com.work.ykserver.ykapps.service.ActivityRemarkService;
import com.work.ykserver.ykapps.util.ResultUtils;
import com.work.ykserver.ykapps.vo.Result;
import lombok.experimental.PackagePrivate;
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

    @GetMapping("/getActivityRemarkList/{activityId}")
    public Result getActivityRemarkList(@PathVariable(value = "activityId") Integer id, @RequestParam(value = "currentPage") Integer currentPage) {
        if (ObjectUtil.isEmpty(id)) {
            return ResultUtils.fail(CodeEnum.PARAMETERS_IS_NULL);
        }
        if (ObjectUtil.isEmpty(currentPage)) {
            currentPage = 1;
        }
        return activityRemarkService.getActivityRemarkListById(id, currentPage);
    }

    @GetMapping("/getNoteContent/{id}")
    public Result getNoteContent(@PathVariable(value = "id") Integer id) {
        if (ObjectUtil.isEmpty(id)) {
            return ResultUtils.fail(CodeEnum.PARAMETERS_IS_NULL);
        }
        return activityRemarkService.getNoteContentById(id);
    }

    @PutMapping("/editActivityRemark")
    public Result editActivityRemark(@RequestBody ActivityRemarkQuery activityRemarkQuery,
                                     @RequestHeader(value = RequestConstants.HEADER_TOKEN_NAME) String token) throws Exception {
        activityRemarkQuery.setToken(token);
        System.out.println(activityRemarkQuery);
        return activityRemarkService.editActivityRemarkById(activityRemarkQuery);
    }

    @DeleteMapping("deleteActivityRemark")
    public Result deleteActivityRemark(@RequestParam(value = "id") Integer id) {
        return activityRemarkService.deleteActivityRemark(id);
    }
}
