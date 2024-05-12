package com.work.ykserver.ykapps.mapper;

import com.work.ykserver.ykapps.bo.Page;
import com.work.ykserver.ykapps.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author 胡国海
* @description 针对表【t_user(用户表)】的数据库操作Mapper
* @createDate 2024-05-09 21:34:33
* @Entity com.work.ykserver.ykapps.pojo.User
*/
public interface UserMapper extends BaseMapper<User> {

    /**
     * 根据用户账号获取用户
     * @param username
     * @return
     */
    User getUserByName(@Param("username") String username);

    List<User> selectUserListByPage(@Param("page") Page page);

    Integer selectRowCount();
}




