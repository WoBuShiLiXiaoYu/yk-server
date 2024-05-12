package com.work.ykserver.ykapps.Constant;

public class RedisConstants {

    // key
    public static final String REDIS_JWT_KEY = "yk:user:login:";

    // 过期时间
    public static final Long EXPIRE_TIME = 7 * 24 * 60 * 60L;
    public static final Long DEFAULT_EXPIRE_TIME = 30 * 60L;
}
