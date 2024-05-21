package com.work.ykserver.ykapps.service;

import com.work.ykserver.ykapps.pojo.DicType;
import com.baomidou.mybatisplus.extension.service.IService;
import com.work.ykserver.ykapps.vo.DicTypeVo;

import java.util.List;

/**
* @author 胡国海
* @description 针对表【t_dic_type(字典类型表)】的数据库操作Service
* @createDate 2024-05-09 21:34:33
*/
public interface DicTypeService extends IService<DicType> {

    List<DicTypeVo> loadAllDicData();

}
