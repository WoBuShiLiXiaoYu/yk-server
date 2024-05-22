package com.work.ykserver.ykapps.service;

import com.work.ykserver.ykapps.pojo.DicValue;
import com.baomidou.mybatisplus.extension.service.IService;
import com.work.ykserver.ykapps.vo.Result;

/**
* @author 胡国海
* @description 针对表【t_dic_value(字典值表)】的数据库操作Service
* @createDate 2024-05-09 21:34:33
*/
public interface DicValueService extends IService<DicValue> {

    Result getDicValueList(String typeCode);
}
