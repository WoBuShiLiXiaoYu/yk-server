package com.work.ykserver.ykapps.mapper;

import com.work.ykserver.ykapps.pojo.ActivityRemark;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.work.ykserver.ykapps.query.ActivityRemarkQuery;
import org.apache.ibatis.annotations.Param;

/**
* @author 胡国海
* @description 针对表【t_activity_remark(市场活动备注表)】的数据库操作Mapper
* @createDate 2024-05-09 21:34:33
* @Entity com.work.ykserver.ykapps.pojo.ActivityRemark
*/
public interface ActivityRemarkMapper extends BaseMapper<ActivityRemark> {

    int insertActivityRemark(@Param("activityRemark") ActivityRemark activityRemark);

}




