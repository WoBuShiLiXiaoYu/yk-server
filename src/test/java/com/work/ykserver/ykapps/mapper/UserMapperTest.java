package com.work.ykserver.ykapps.mapper;

import com.work.ykserver.ykapps.bo.Page;
import com.work.ykserver.ykapps.pojo.User;
import com.work.ykserver.ykapps.query.BaseQuery;
import com.work.ykserver.ykapps.util.PageUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Slf4j
class UserMapperTest {

    @Resource
    private UserMapper userMapper;

    @Test
    public void updateUserByIdTest() {
        User user = new User();
        user.setId(33);
        user.setLoginAct("zssTest11");
        user.setLoginPwd("123123");
        user.setEmail("123123@163.com");
        user.setName("张思思");
        user.setPhone("12345678903");
        user.setAccountNoExpired(1);
        user.setAccountNoExpired(1);
        user.setAccountNoLocked(0);
        user.setAccountEnabled(0);
        user.setEditBy(1);
        user.setEditTime(new Date());
        int result = userMapper.updateUserById(user);
        Assertions.assertEquals(1, result);
    }

    @Test
    public void addUserTest() {
        /*
        login_act,login_pwd,
        name,phone,email,
        account_no_expired,credentials_no_expired,account_no_locked,
        account_enabled,create_time,create_by,
         */
        User user = new User();
        user.setLoginAct("zss");
        user.setLoginPwd("123123");
        user.setEmail("123123@163.com");
        user.setName("张思思");
        user.setPhone("12345678903");
        user.setAccountNoExpired(0);
        user.setAccountNoExpired(0);
        user.setAccountNoLocked(0);
        user.setAccountEnabled(0);
        user.setCreateBy(1);
        user.setCreateTime(new Date());
        int result = userMapper.addUser(user);
        Assertions.assertEquals(1, result);
    }

    @Test
    public void selectUserDetailByIdTest() {
        User user = userMapper.selectUserDetailById(1);
        Assertions.assertNotNull(user);
        log.info("user: " + user);
    }

    @Test
    public void selectRowCount() {
        Integer count = userMapper.selectRowCount(new BaseQuery());
        Assertions.assertEquals(25, count);
    }

    @Test
    public void selectUserListByPageTest() {
        Page page = PageUtils.getPage(1);
        BaseQuery query = new BaseQuery();
        List<User> userList = userMapper.selectUserListByPage(query, page);
        Assertions.assertNotNull(userList);
        log.info("userList:" + userList);
    }

    @Test
    public void selectByUserNameTest() {
        User user = userMapper.getUserByName("admin");
        Assertions.assertNotNull(user);
    }

}