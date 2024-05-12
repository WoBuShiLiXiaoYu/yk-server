package com.work.ykserver.ykapps.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.work.ykserver.ykapps.pojo.Activity;
import com.work.ykserver.ykapps.service.ActivityService;
import com.work.ykserver.ykapps.mapper.ActivityMapper;
import org.springframework.stereotype.Service;

/**
* @author 胡国海
* @description 针对表【t_activity(市场活动表)】的数据库操作Service实现
* @createDate 2024-05-09 21:34:33
*/
@Service
public class ActivityServiceImpl extends ServiceImpl<ActivityMapper, Activity>
    implements ActivityService{

}




