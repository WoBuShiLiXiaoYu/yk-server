package com.work.ykserver.ykapps.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.work.ykserver.ykapps.bo.Page;
import com.work.ykserver.ykapps.common.CodeEnum;
import com.work.ykserver.ykapps.constant.RedisConstants;
import com.work.ykserver.ykapps.manager.RedisManager;
import com.work.ykserver.ykapps.pojo.Activity;
import com.work.ykserver.ykapps.pojo.User;
import com.work.ykserver.ykapps.query.ActivityQuery;
import com.work.ykserver.ykapps.query.BaseQuery;
import com.work.ykserver.ykapps.service.ActivityService;
import com.work.ykserver.ykapps.mapper.ActivityMapper;
import com.work.ykserver.ykapps.util.CacheUtils;
import com.work.ykserver.ykapps.util.JWTUtils;
import com.work.ykserver.ykapps.util.PageUtils;
import com.work.ykserver.ykapps.util.ResultUtils;
import com.work.ykserver.ykapps.vo.Result;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
    @Resource
    private RedisManager redisManager;

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
    @Transactional(rollbackFor = Exception.class)
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
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Result editActivity(ActivityQuery activityQuery) throws Exception {
        // 解析 token
        User loginUser = JWTUtils.parseUserFromJWT(activityQuery.getToken());
        Activity activity = new Activity();
        BeanUtil.copyProperties(activityQuery, activity);
        activity.setEditBy(loginUser.getId());
        activity.setEditTime(new Date());
        int result = activityMapper.updateActivity(activity);
        if (result != 1) {
            throw new Exception("更新市场活动信息失败！");
        }
        return ResultUtils.success(CodeEnum.OK);
    }

    @Override
    public Result getActivityDetailInfoById(Integer id) {
        Activity activity = activityMapper.selectActivityDetailInfoById(id);
        return ResultUtils.success(activity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result deleteActivityById(Integer id) {
        int result = activityMapper.deleteActivityById(id);
        if (result != 1) {
            return ResultUtils.fail(CodeEnum.DELETE_ACTIVITY_FAIL);
        }
        return ResultUtils.success(CodeEnum.OK);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result batchDeleteActivityByIds(String[] ids) {
        List<Integer> activityIds = Arrays.stream(ids).map(Integer::parseInt).collect(Collectors.toList());
        int result = activityMapper.batchDeleteByIds(activityIds);
        if (result != activityIds.size()) {
            return ResultUtils.fail(CodeEnum.DELETE_ACTIVITY_FAIL);
        }
        return ResultUtils.success(CodeEnum.OK);
    }

    @Override
    public Result getActivityName() {
        List<Activity> activityNameList = CacheUtils.getCacheData(() -> {
                    // 为缓存生产者提供数据
                    return redisManager.getValue(RedisConstants.REDIS_CACHE_ACTIVITY_KEY, Activity.class);
                },
                () -> {
                    // 为数据库生产者提供数据
                    return activityMapper.selectActivityName();
                },
                (t) -> {
                    // 为缓存消费者提供数据
                    redisManager.setValue(RedisConstants.REDIS_CACHE_ACTIVITY_KEY, t);
                });

        return ResultUtils.success(activityNameList);
    }

}




