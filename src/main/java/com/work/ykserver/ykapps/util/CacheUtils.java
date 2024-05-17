package com.work.ykserver.ykapps.util;

import cn.hutool.core.util.ObjectUtil;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * 缓存数据工具类
 */
public class CacheUtils {

    private CacheUtils() {}

    public static <T> T getCacheData(Supplier<T> cacheSelector, Supplier<T> databaseSelector, Consumer<T> cacheSave) {
        // 从 redis 中查找数据
        T data = cacheSelector.get();
        if (ObjectUtil.isEmpty(data)) {
            // 没有查询到数据，则从数据库中查询
            data = databaseSelector.get();
            if (!ObjectUtil.isEmpty(data)) {
                // 数据库查询到数据后存放进 redis
                cacheSave.accept(data);
            }
        }
        return data;
    }
}
