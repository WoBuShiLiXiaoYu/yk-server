package com.work.ykserver.ykapps.constant;

public class RedisConstants {

    // key
    public static final String REDIS_JWT_KEY = "yk:user:login:";
    public static final String REDIS_CACHE_OWNER_KEY = "yk:user:cacheOwner";
    public static final String REDIS_CACHE_ACTIVITY_KEY = "yk:user:cacheActivityName";
    public static final String REDIS_CACHE_DIC_VALUE_KEY = "yk:user:cacheDicValue:";
    public static final String REDIS_CACHE_PRODUCT_KEY = "yk:user:cacheProduct";


    // 过期时间
    public static final Long EXPIRE_TIME = 7 * 24 * 60 * 60L;
    public static final Long DEFAULT_EXPIRE_TIME = 30 * 60L;
    public static final Long DEFAULT_CACHE_EXPIRE_TIME = 5 * 60L;
}
