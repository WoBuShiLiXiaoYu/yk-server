package com.work.ykserver.ykapps.mapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RoleMapperTest {

    @Resource
    private RoleMapper roleMapper;

    @Test
    public void getRoleByUserIdTest() {
        List<String> roleList = roleMapper.getRoleByUserId(1);
        Assertions.assertNotNull(roleList);
    }
}