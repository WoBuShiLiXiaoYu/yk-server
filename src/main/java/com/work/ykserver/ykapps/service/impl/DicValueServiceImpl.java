package com.work.ykserver.ykapps.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.work.ykserver.ykapps.constant.RedisConstants;
import com.work.ykserver.ykapps.manager.RedisManager;
import com.work.ykserver.ykapps.pojo.Activity;
import com.work.ykserver.ykapps.pojo.DicValue;
import com.work.ykserver.ykapps.service.DicValueService;
import com.work.ykserver.ykapps.mapper.DicValueMapper;
import com.work.ykserver.ykapps.util.CacheUtils;
import com.work.ykserver.ykapps.util.ResultUtils;
import com.work.ykserver.ykapps.vo.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
* @author 胡国海
* @description 针对表【t_dic_value(字典值表)】的数据库操作Service实现
* @createDate 2024-05-09 21:34:33
*/
@Service
public class DicValueServiceImpl extends ServiceImpl<DicValueMapper, DicValue>
    implements DicValueService{

    @Resource
    private DicValueMapper dicValueMapper;
    @Resource
    private RedisManager redisManager;

    @Override
    public Result getDicValueList(String typeCode) {
        List<DicValue> dicValueList = CacheUtils.getCacheData(() -> {
                    // 为缓存生产者提供数据
                    return redisManager.getValue(RedisConstants.REDIS_CACHE_DIC_VALUE_KEY + typeCode, DicValue.class);
                },
                () -> {
                    // 为数据库生产者提供数据
                    return dicValueMapper.selectDicValueList(typeCode);
                },
                (t) -> {
                    // 为缓存消费者提供数据
                    redisManager.setValue(RedisConstants.REDIS_CACHE_DIC_VALUE_KEY + typeCode, t);
                });
        return ResultUtils.success(dicValueList);
    }
}




