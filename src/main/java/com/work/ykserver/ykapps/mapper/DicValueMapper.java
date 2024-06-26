package com.work.ykserver.ykapps.mapper;

import com.work.ykserver.ykapps.pojo.DicValue;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author 胡国海
* @description 针对表【t_dic_value(字典值表)】的数据库操作Mapper
* @createDate 2024-05-09 21:34:33
* @Entity com.work.ykserver.ykapps.pojo.DicValue
*/
public interface DicValueMapper extends BaseMapper<DicValue> {

    List<DicValue> selectDicValueList(@Param("typeCode") String typeCode);
}




