package com.work.ykserver.ykapps.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.work.ykserver.ykapps.common.CodeEnum;
import com.work.ykserver.ykapps.pojo.ActivityRemark;
import com.work.ykserver.ykapps.pojo.User;
import com.work.ykserver.ykapps.query.ActivityRemarkQuery;
import com.work.ykserver.ykapps.service.ActivityRemarkService;
import com.work.ykserver.ykapps.mapper.ActivityRemarkMapper;
import com.work.ykserver.ykapps.util.JWTUtils;
import com.work.ykserver.ykapps.util.ResultUtils;
import com.work.ykserver.ykapps.vo.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
* @author 胡国海
* @description 针对表【t_activity_remark(市场活动备注表)】的数据库操作Service实现
* @createDate 2024-05-09 21:34:33
*/
@Service
public class ActivityRemarkServiceImpl extends ServiceImpl<ActivityRemarkMapper, ActivityRemark>
    implements ActivityRemarkService{

    @Resource
    private ActivityRemarkMapper activityRemarkMapper;

    @Override
    public Result saveActivityRemark(ActivityRemarkQuery activityRemarkQuery) {
        User loginUser = JWTUtils.parseUserFromJWT(activityRemarkQuery.getToken());
        ActivityRemark activityRemark = new ActivityRemark();
        BeanUtil.copyProperties(activityRemarkQuery, activityRemark);
        activityRemark.setCreateBy(loginUser.getId());
        activityRemark.setCreateTime(new Date());
        int result = activityRemarkMapper.insertActivityRemark(activityRemark);
        if (result < 1) {
            return ResultUtils.fail(CodeEnum.SAVE_ACTIVITY_REMARK_FAIL);
        }
        return ResultUtils.success(CodeEnum.OK);
    }
}




