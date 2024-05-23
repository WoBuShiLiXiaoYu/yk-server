package com.work.ykserver.ykapps.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.work.ykserver.ykapps.bo.Page;
import com.work.ykserver.ykapps.common.CodeEnum;
import com.work.ykserver.ykapps.pojo.ActivityRemark;
import com.work.ykserver.ykapps.pojo.User;
import com.work.ykserver.ykapps.query.ActivityRemarkQuery;
import com.work.ykserver.ykapps.service.ActivityRemarkService;
import com.work.ykserver.ykapps.mapper.ActivityRemarkMapper;
import com.work.ykserver.ykapps.util.JWTUtils;
import com.work.ykserver.ykapps.util.PageUtils;
import com.work.ykserver.ykapps.util.ResultUtils;
import com.work.ykserver.ykapps.vo.Result;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

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
    @Transactional(rollbackFor = Exception.class)
    public Result saveActivityRemark(ActivityRemarkQuery activityRemarkQuery) {
        User loginUser = JWTUtils.parseUserFromJWT(activityRemarkQuery.getToken());
        ActivityRemark activityRemark = new ActivityRemark();
        BeanUtil.copyProperties(activityRemarkQuery, activityRemark);
        activityRemark.setCreateBy(loginUser.getId());
        activityRemark.setCreateTime(new Date());
        activityRemark.setDeleted(0);
        int result = activityRemarkMapper.insertActivityRemark(activityRemark);
        if (result < 1) {
            return ResultUtils.fail(CodeEnum.SAVE_ACTIVITY_REMARK_FAIL);
        }
        return ResultUtils.success(CodeEnum.OK);
    }

    @Override
    public Result getActivityRemarkListById(Integer id, Integer currentPage) {
        Page page = PageUtils.getPage(currentPage);
        ActivityRemarkQuery activityRemarkQuery = new ActivityRemarkQuery();
        activityRemarkQuery.setActivityId(id);
        // 获取活动备注列表
        List<ActivityRemark> activityRemarkList = activityRemarkMapper.selectListForPageById(activityRemarkQuery, page);
        // 获取活动备注总条数
        int count = activityRemarkMapper.selectCountById(activityRemarkQuery);

        page = PageUtils.pageSetting(page, activityRemarkList, count);
        return ResultUtils.success(page);
    }

    @Override
    public Result getNoteContentById(Integer id) {
        ActivityRemark activityRemark = activityRemarkMapper.selectById(id);
        return ResultUtils.success(activityRemark);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Result editActivityRemarkById(ActivityRemarkQuery activityRemarkQuery) throws Exception {
        //解析 token
        User loginUser = JWTUtils.parseUserFromJWT(activityRemarkQuery.getToken());
        ActivityRemark activityRemark = new ActivityRemark();
        BeanUtil.copyProperties(activityRemarkQuery, activityRemark);
        activityRemark.setEditBy(loginUser.getId());
        activityRemark.setEditTime(new Date());
        int result = activityRemarkMapper.updateNoteContentById(activityRemark);
        if (result != 1) {
            throw new Exception("更新备注信息失败！");
        }
        return ResultUtils.success(CodeEnum.OK);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result deleteActivityRemark(Integer id) {
        int result = activityRemarkMapper.deleteById(id);
        if (result != 1) {
            return ResultUtils.fail(CodeEnum.DELETE_ACTIVITY_REMARK_FAIL);
        }
        return ResultUtils.fail(CodeEnum.OK);
    }
}




