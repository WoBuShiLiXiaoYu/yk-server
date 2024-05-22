package com.work.ykserver.ykapps.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.work.ykserver.ykapps.bo.Page;
import com.work.ykserver.ykapps.common.CodeEnum;
import com.work.ykserver.ykapps.pojo.ClueRemark;
import com.work.ykserver.ykapps.pojo.User;
import com.work.ykserver.ykapps.query.ClueRemarkQuery;
import com.work.ykserver.ykapps.service.ClueRemarkService;
import com.work.ykserver.ykapps.mapper.ClueRemarkMapper;
import com.work.ykserver.ykapps.util.JWTUtils;
import com.work.ykserver.ykapps.util.PageUtils;
import com.work.ykserver.ykapps.util.ResultUtils;
import com.work.ykserver.ykapps.vo.ClueRemarkVO;
import com.work.ykserver.ykapps.vo.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
* @author 胡国海
* @description 针对表【t_clue_remark(线索跟踪记录表)】的数据库操作Service实现
* @createDate 2024-05-09 21:34:33
*/
@Service
public class ClueRemarkServiceImpl extends ServiceImpl<ClueRemarkMapper, ClueRemark>
    implements ClueRemarkService{

    @Resource
    private ClueRemarkMapper clueRemarkMapper;

    @Override
    public Result addClueRemark(ClueRemarkQuery clueRemarkQuery) {
        // 解析
        User loginUser = JWTUtils.parseUserFromJWT(clueRemarkQuery.getToken());
        ClueRemark clueRemark = new ClueRemark();
        BeanUtil.copyProperties(clueRemarkQuery, clueRemark);
        clueRemark.setCreateBy(loginUser.getId());
        clueRemark.setCreateTime(new Date());
        clueRemark.setDeleted(0);
        int result = clueRemarkMapper.insert(clueRemark);
        if (result != 1) {
            return ResultUtils.fail(CodeEnum.SAVE_CLUE_REMARK_FAIL);
        }
        return ResultUtils.success(CodeEnum.OK);
    }

    @Override
    public Result getClueRemarkListByPage(Integer clueId, Integer currentPage) {
        Page page = PageUtils.getPage(currentPage);
        ClueRemarkQuery clueRemarkQuery = new ClueRemarkQuery();
        clueRemarkQuery.setClueId(clueId);
        // 查询线索跟踪历史列表
        List<ClueRemarkVO> clueRemarkVOList = clueRemarkMapper.selectClueRemarkListByPage(clueRemarkQuery, page);
        // 查询总记录行数
        int total = clueRemarkMapper.selectCountByPage(clueRemarkQuery);
        page = PageUtils.pageSetting(page, clueRemarkVOList, total);
        return ResultUtils.success(page);
    }
}




