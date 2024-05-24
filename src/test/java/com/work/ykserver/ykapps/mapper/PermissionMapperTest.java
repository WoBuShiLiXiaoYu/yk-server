package com.work.ykserver.ykapps.mapper;

import com.work.ykserver.ykapps.vo.PermissionVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import java.util.List;

@SpringBootTest
@Slf4j
class PermissionMapperTest {

    @Resource
    private PermissionMapper permissionMapper;

    @Test
    public void getPermissionMapperTest() {
        List<PermissionVO> permissionVOList = permissionMapper.getMenuPermissionByUserId(1);
        Assertions.assertNotNull(permissionVOList);
        for (PermissionVO permission : permissionVOList) {
            log.info("permissionId======> " + permission.getId());
            log.info("permissionName======> " + permission.getName());
            log.info("permissionType======> " + permission.getType());
            log.info("permissionUrl======> " + permission.getUrl());
            log.info("permissionCode======> " + permission.getCode());
            log.info("permissionIcon======> " + permission.getIcon());
            log.info("permissionOrderNo======> " + permission.getOrderNo());
            log.info("permissionParentId======> " + permission.getParentId());
            log.info("permissionChildList======> " + permission.getChildList());
        }
    }

}