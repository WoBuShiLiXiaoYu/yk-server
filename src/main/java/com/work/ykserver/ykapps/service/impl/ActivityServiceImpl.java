package com.work.ykserver.ykapps.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.work.ykserver.ykapps.bo.Page;
import com.work.ykserver.ykapps.pojo.Activity;
import com.work.ykserver.ykapps.query.BaseQuery;
import com.work.ykserver.ykapps.service.ActivityService;
import com.work.ykserver.ykapps.mapper.ActivityMapper;
import com.work.ykserver.ykapps.util.PageUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
    public Page getActivityListByPage(Integer currentPage) {
        // 获取分页对象
        Page page = PageUtils.getPage(currentPage);
        // 设置查询对象
        BaseQuery query = new BaseQuery();
        // 查询活动列表
        List<Activity> activityList = activityMapper.selectActivityListByPage(query, page);
        // 查询总记录条数
        int pageCount = activityMapper.selectRowCount(query);
        // 设置 Page 对象
        page = PageUtils.pageSetting(page, activityList, pageCount);
        return page;
    }
}




