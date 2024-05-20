package com.work.ykserver.ykapps.service;

import com.work.ykserver.ykapps.pojo.ActivityRemark;
import com.baomidou.mybatisplus.extension.service.IService;
import com.work.ykserver.ykapps.query.ActivityRemarkQuery;
import com.work.ykserver.ykapps.vo.Result;

/**
* @author 胡国海
* @description 针对表【t_activity_remark(市场活动备注表)】的数据库操作Service
* @createDate 2024-05-09 21:34:33
*/
public interface ActivityRemarkService extends IService<ActivityRemark> {

    Result saveActivityRemark(ActivityRemarkQuery activityRemarkQuery);

    Result getActivityRemarkListById(Integer id, Integer currentPage);

    Result getNoteContentById(Integer id);

    Result editActivityRemarkById(ActivityRemarkQuery activityRemarkQuery) throws Exception;

    Result deleteActivityRemark(Integer id);

}
