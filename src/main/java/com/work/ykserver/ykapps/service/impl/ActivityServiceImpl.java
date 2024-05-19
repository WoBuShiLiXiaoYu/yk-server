package com.work.ykserver.ykapps.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.work.ykserver.ykapps.bo.Page;
import com.work.ykserver.ykapps.common.CodeEnum;
import com.work.ykserver.ykapps.pojo.Activity;
import com.work.ykserver.ykapps.pojo.User;
import com.work.ykserver.ykapps.query.ActivityQuery;
import com.work.ykserver.ykapps.query.BaseQuery;
import com.work.ykserver.ykapps.service.ActivityService;
import com.work.ykserver.ykapps.mapper.ActivityMapper;
import com.work.ykserver.ykapps.util.JWTUtils;
import com.work.ykserver.ykapps.util.PageUtils;
import com.work.ykserver.ykapps.util.ResultUtils;
import com.work.ykserver.ykapps.vo.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
* @author 胡国海
* @description 针对表【t_activity(市场活动表)】的数据库操作Service实现
* @createDate 2024-05-09 21:34:33
*/
@Service
public class ActivityServiceImpl extends ServiceImpl<ActivityMapper, Activity>
    implements ActivityService{

    @Resource
    private ActivityMapper activityMapper;

    @Override
    public Page getActivityListByPage(Integer currentPage, ActivityQuery query) {
        // 获取分页对象
        Page page = PageUtils.getPage(currentPage);

        // 查询活动列表
        List<Activity> activityList = activityMapper.selectActivityListByPage(query, page);
        // 查询总记录条数
        int pageCount = activityMapper.selectRowCount(query);
        // 设置 Page 对象
        page = PageUtils.pageSetting(page, activityList, pageCount);
        return page;
    }

    @Override
    public Result saveActivity(ActivityQuery activityQuery) {
        // 解析 token，获取创建人 id
        User loginUser = JWTUtils.parseUserFromJWT(activityQuery.getToken());
        Activity activity = new Activity();
        BeanUtil.copyProperties(activityQuery, activity);
        activity.setCreateTime(new Date());
        activity.setCreateBy(loginUser.getId());
        int result = activityMapper.insert(activity);
        if (result < 1) {
            return ResultUtils.fail(CodeEnum.SAVE_ACTIVITY_FAIL);
        }
        return ResultUtils.success(CodeEnum.OK);
    }

    @Override
    public Result getActivityById(Integer id) {
        Activity activity = activityMapper.selectById(id);
        return ResultUtils.success(activity);
    }

    @Override
    public Result editActivity(ActivityQuery activityQuery) {
        // 解析 token
        User loginUser = JWTUtils.parseUserFromJWT(activityQuery.getToken());
        Activity activity = new Activity();
        BeanUtil.copyProperties(activityQuery, activity);
        activity.setEditBy(loginUser.getId());
        activity.setEditTime(new Date());
        int result = activityMapper.updateActivity(activity);
        if (result < 1) {
            return ResultUtils.fail(CodeEnum.EDIT_ACTIVITY_FAIL);
        }
        return ResultUtils.success(CodeEnum.OK);
    }

    @Override
    public Result getActivityDetailInfoById(Integer id) {
        Activity activity = activityMapper.selectActivityDetailInfoById(id);
        return ResultUtils.success(activity);
    }
}




