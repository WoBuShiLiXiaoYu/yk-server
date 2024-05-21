package com.work.ykserver.ykapps.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.work.ykserver.ykapps.pojo.DicType;
import com.work.ykserver.ykapps.service.DicTypeService;
import com.work.ykserver.ykapps.mapper.DicTypeMapper;
import com.work.ykserver.ykapps.vo.DicTypeVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
* @author 胡国海
* @description 针对表【t_dic_type(字典类型表)】的数据库操作Service实现
* @createDate 2024-05-09 21:34:33
*/
@Service
public class DicTypeServiceImpl extends ServiceImpl<DicTypeMapper, DicType>
    implements DicTypeService{

    @Resource
    private DicTypeMapper dicTypeMapper;

    @Override
    public List<DicTypeVo> loadAllDicData() {
        return dicTypeMapper.selectAllDicData();
    }
}




