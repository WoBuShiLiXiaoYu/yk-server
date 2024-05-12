package com.work.ykserver.ykapps.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.work.ykserver.ykapps.pojo.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
* @author 胡国海
* @description 针对表【t_role(角色表)】的数据库操作Mapper
* @createDate 2024-05-09 21:34:33
* @Entity com.work.ykserver.ykapps.pojo.Role
*/
public interface RoleMapper extends BaseMapper<Role> {

    List<String> getRoleByUserId(@Param("userId") Integer userId);
}




