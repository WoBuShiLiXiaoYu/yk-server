package com.work.ykserver.ykapps.service;

import com.work.ykserver.ykapps.pojo.ClueRemark;
import com.baomidou.mybatisplus.extension.service.IService;
import com.work.ykserver.ykapps.query.ClueRemarkQuery;
import com.work.ykserver.ykapps.vo.Result;

/**
* @author 胡国海
* @description 针对表【t_clue_remark(线索跟踪记录表)】的数据库操作Service
* @createDate 2024-05-09 21:34:33
*/
public interface ClueRemarkService extends IService<ClueRemark> {

    Result addClueRemark(ClueRemarkQuery clueRemarkQuery);

    Result getClueRemarkListByPage(Integer clueId, Integer currentPage);

    Result getNoteContentById(Integer id);

    Result editClueRemark(ClueRemarkQuery clueRemarkQuery) throws Exception;

    Result deleteClueRemarkById(Integer id);
}
