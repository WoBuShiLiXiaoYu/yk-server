package com.work.ykserver.ykapps.mapper;

import com.work.ykserver.ykapps.bo.Page;
import com.work.ykserver.ykapps.common.DataScope;
import com.work.ykserver.ykapps.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.work.ykserver.ykapps.query.BaseQuery;
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

    @DataScope(myTableAlias = "tu", myTableField = "id")
    //tu.id =
    List<User> selectUserListByPage(@Param("query") BaseQuery query, @Param("page") Page page);

    @DataScope(myTableAlias = "tu", myTableField = "id")
    Integer selectRowCount(@Param("query") BaseQuery query);

    User selectUserDetailById(@Param("id") Integer id);

    int addUser(@Param("user") User user);

    int updateUserById(@Param("user") User user);

    int batchDeleteByIds(@Param("userIds") List<Integer> userIds);

    List<User> selectUserList();
}




