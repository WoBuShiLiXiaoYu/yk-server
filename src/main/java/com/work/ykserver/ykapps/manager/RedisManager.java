package com.work.ykserver.ykapps.manager;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.work.ykserver.ykapps.constant.RedisConstants;
import com.work.ykserver.ykapps.pojo.User;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
public class RedisManager {
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    // 从 redis 中查询数据
    public List<User> getValue(String redisKey) {
        String resultJson = stringRedisTemplate.opsForValue().get(redisKey);
        List<User> userList = JSONUtil.toList(resultJson, User.class);
        return userList;
    }

    // 向 redis 中存储数据
    public <T> void setValue(String redisKey, List<T> list) {
        String jsonStr = JSONUtil.toJsonStr(list);
        stringRedisTemplate.opsForValue().set(redisKey, jsonStr, RedisConstants.DEFAULT_CACHE_EXPIRE_TIME, TimeUnit.SECONDS);
    }
}
