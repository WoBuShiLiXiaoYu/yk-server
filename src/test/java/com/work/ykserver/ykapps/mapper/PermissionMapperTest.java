package com.work.ykserver.ykapps.mapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import java.util.List;

@SpringBootTest
class PermissionMapperTest {

    @Resource
    private PermissionMapper permissionMapper;

    @Test
    public void getPermissionMapperTest() {
        List<String> permissions = this.permissionMapper.getPermissionByUserId("1");
        Assertions.assertNotNull(permissions);

    }

}