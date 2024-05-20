package com.work.ykserver.ykapps.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.work.ykserver.ykapps.bo.Page;
import com.work.ykserver.ykapps.pojo.Clue;
import com.work.ykserver.ykapps.query.ClueQuery;
import com.work.ykserver.ykapps.service.ClueService;
import com.work.ykserver.ykapps.mapper.ClueMapper;
import com.work.ykserver.ykapps.util.PageUtils;
import com.work.ykserver.ykapps.util.RedisUtils;
import com.work.ykserver.ykapps.util.ResultUtils;
import com.work.ykserver.ykapps.vo.ClueVO;
import com.work.ykserver.ykapps.vo.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
* @author 胡国海
* @description 针对表【t_clue(线索表)】的数据库操作Service实现
* @createDate 2024-05-09 21:34:33
*/
@Service
public class ClueServiceImpl extends ServiceImpl<ClueMapper, Clue>
    implements ClueService{

    @Resource
    private ClueMapper clueMapper;

    @Override
    public Result getClueListByPage(Integer currentPage) {
        Page page = PageUtils.getPage(currentPage);
        ClueQuery query = new ClueQuery();
        // 获取线索列表
        List<ClueVO> clueList = clueMapper.selectClueListByPage(query, page);
        // 获取总记录条数
        int total = clueMapper.getCountByPage(query);

        page = PageUtils.pageSetting(page, clueList, total);

        return ResultUtils.success(page);
    }
}




