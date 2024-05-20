package com.work.ykserver.ykapps.service;

import com.work.ykserver.ykapps.bo.Page;
import com.work.ykserver.ykapps.pojo.Activity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.work.ykserver.ykapps.query.ActivityQuery;
import com.work.ykserver.ykapps.query.BaseQuery;
import com.work.ykserver.ykapps.vo.Result;

/**
* @author 胡国海
* @description 针对表【t_activity(市场活动表)】的数据库操作Service
* @createDate 2024-05-09 21:34:33
*/
public interface ActivityService extends IService<Activity> {

    Page getActivityListByPage(Integer currentPage, ActivityQuery query);

    Result saveActivity(ActivityQuery activityQuery);

    Result getActivityById(Integer id);

    Result editActivity(ActivityQuery activityQuery) throws Exception;

    Result getActivityDetailInfoById(Integer id);

    Result deleteActivityById(Integer id);

    Result batchDeleteActivityByIds(String[] ids);
}
