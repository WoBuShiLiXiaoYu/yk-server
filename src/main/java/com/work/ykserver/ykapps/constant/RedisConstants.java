package com.work.ykserver.ykapps.constant;

public class RedisConstants {

    // key
    public static final String REDIS_JWT_KEY = "yk:user:login:";
    public static final String REDIS_CACHE_KEY = "yk:user:cacheOwner";

    // 过期时间
    public static final Long EXPIRE_TIME = 7 * 24 * 60 * 60L;
    public static final Long DEFAULT_EXPIRE_TIME = 30 * 60L;
    public static final Long DEFAULT_CACHE_EXPIRE_TIME = 5 * 60L;
}
