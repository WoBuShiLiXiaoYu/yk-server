package com.work.ykserver.ykapps.mapper;

import com.work.ykserver.ykapps.bo.Page;
import com.work.ykserver.ykapps.pojo.Clue;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.work.ykserver.ykapps.query.ClueQuery;
import com.work.ykserver.ykapps.vo.ClueVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author 胡国海
* @description 针对表【t_clue(线索表)】的数据库操作Mapper
* @createDate 2024-05-09 21:34:33
* @Entity com.work.ykserver.ykapps.pojo.Clue
*/
public interface ClueMapper extends BaseMapper<Clue> {

    int saveClueByImport(@Param("cachedDataList") List<Clue> cachedDataList);

    List<ClueVO> selectClueListByPage(@Param("query") ClueQuery query, @Param("page") Page page);

    int getCountByPage(@Param("query") ClueQuery query);

    int selectCountAll();

    int selectCountByPhone(@Param("phone") String phone);

    int saveClue(@Param("clue") Clue clue);

    int updateClue(@Param("clue") Clue clue);

    ClueVO selectClueDetailInfoById(@Param("id") Integer id);

    int deleteByIds(@Param("idList") List<Integer> idList);

    int updateStateById(@Param("clueId") Integer clueId);
}




