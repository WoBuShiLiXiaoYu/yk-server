package com.work.ykserver;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

import java.util.HashMap;
import java.util.Map;

@EnableScheduling
@MapperScan("com.work.ykserver.ykapps.mapper")
@SpringBootApplication
public class YkServerApplication {

    // 缓存 map
    public static final Map<String, Object> cacheMap = new HashMap<>();

    public static void main(String[] args) {
        SpringApplication.run(YkServerApplication.class, args);

    }

}
