package com.work.ykserver.ykapps.service;

import com.work.ykserver.ykapps.pojo.Clue;
import com.baomidou.mybatisplus.extension.service.IService;
import com.work.ykserver.ykapps.vo.Result;

/**
* @author 胡国海
* @description 针对表【t_clue(线索表)】的数据库操作Service
* @createDate 2024-05-09 21:34:33
*/
public interface ClueService extends IService<Clue> {

    Result getClueListByPage(Integer currentPage);

}
