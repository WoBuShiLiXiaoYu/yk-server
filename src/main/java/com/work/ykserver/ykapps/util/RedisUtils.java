package com.work.ykserver.ykapps.util;

import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Component
public class RedisUtils {

    private StringRedisTemplate stringRedisTemplate;

    public RedisUtils(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    // 存入
    public void setRedisValue(String key, String value) {
        stringRedisTemplate.opsForValue().set(key, value);
    }

    // 取出
    public String getRedisValue(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }

    // 移除
    public Boolean removeRedisValue(String key) {
        return stringRedisTemplate.delete(key);
    }

    // 设置过期时间
    public Boolean expire(String key, Long time, TimeUnit timeUnit) {
        return stringRedisTemplate.expire(key, time, timeUnit);
    }
}
