package com.work.ykserver.ykapps.mapper;

import com.work.ykserver.ykapps.bo.Page;
import com.work.ykserver.ykapps.common.DataScope;
import com.work.ykserver.ykapps.pojo.Activity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.work.ykserver.ykapps.query.ActivityQuery;
import com.work.ykserver.ykapps.query.BaseQuery;
import com.work.ykserver.ykapps.vo.Result;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author 胡国海
* @description 针对表【t_activity(市场活动表)】的数据库操作Mapper
* @createDate 2024-05-09 21:34:33
* @Entity com.work.ykserver.ykapps.pojo.Activity
*/
public interface ActivityMapper extends BaseMapper<Activity> {

    @DataScope(myTableAlias = "ta", myTableField = "owner_id")
    List<Activity> selectActivityListByPage(@Param("query") ActivityQuery query, @Param("page") Page page);

    @DataScope(myTableAlias = "ta", myTableField = "owner_id")
    int selectRowCount(@Param("query") ActivityQuery query);

    int updateActivity(@Param("activity") Activity activity);

    Activity selectActivityDetailInfoById(@Param("id") Integer id);

    int batchDeleteByIds(@Param("activityIds") List<Integer> activityIds);

    List<Activity> selectActivityName();


}




