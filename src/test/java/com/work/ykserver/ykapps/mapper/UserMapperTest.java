package com.work.ykserver.ykapps.mapper;

import com.work.ykserver.ykapps.bo.Page;
import com.work.ykserver.ykapps.pojo.User;
import com.work.ykserver.ykapps.util.PageUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Slf4j
class UserMapperTest {

    @Resource
    private UserMapper userMapper;

    @Test
    public void selectRowCount() {
        Integer count = userMapper.selectRowCount();
        Assertions.assertEquals(25, count);
    }

    @Test
    public void selectUserListByPageTest() {
        Page page = PageUtils.getPage(1);
        List<User> userList = userMapper.selectUserListByPage(page);
        Assertions.assertNotNull(userList);
        log.info("userList:" + userList);
    }

    @Test
    public void selectByUserNameTest() {
        User user = userMapper.getUserByName("admin");
        Assertions.assertNotNull(user);
    }

}