package com.work.ykserver;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.work.ykserver.ykapps.mapper")
@SpringBootApplication
public class YkServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(YkServerApplication.class, args);
    }

}
