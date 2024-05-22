package com.work.ykserver.ykapps.mapper;

import com.work.ykserver.ykapps.bo.Page;
import com.work.ykserver.ykapps.common.DataScope;
import com.work.ykserver.ykapps.pojo.ClueRemark;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.work.ykserver.ykapps.query.ClueRemarkQuery;
import com.work.ykserver.ykapps.vo.ClueRemarkVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author 胡国海
* @description 针对表【t_clue_remark(线索跟踪记录表)】的数据库操作Mapper
* @createDate 2024-05-09 21:34:33
* @Entity com.work.ykserver.ykapps.pojo.ClueRemark
*/
public interface ClueRemarkMapper extends BaseMapper<ClueRemark> {

    @DataScope(myTableAlias = "tcr", myTableField = "create_by")
    List<ClueRemarkVO> selectClueRemarkListByPage(@Param("clueRemarkQuery") ClueRemarkQuery clueRemarkQuery, @Param("page") Page page);

    int selectCountByPage(@Param("clueRemarkQuery") ClueRemarkQuery clueRemarkQuery);
}




