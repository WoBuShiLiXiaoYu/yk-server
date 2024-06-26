package com.work.ykserver.ykapps.mapper;

import com.work.ykserver.ykapps.pojo.Permission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.work.ykserver.ykapps.vo.PermissionVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author 胡国海
* @description 针对表【t_permission(权限表)】的数据库操作Mapper
* @createDate 2024-05-09 21:34:33
* @Entity com.work.ykserver.ykapps.pojo.Permission
*/
public interface PermissionMapper extends BaseMapper<Permission> {


    List<PermissionVO> getMenuPermissionByUserId(@Param("userId") Integer userId);

    /**
     * 根据用户 Id 获取该用户权限列表
     * @param id
     * @return
     */
    List<String> getPermissionByUserId(@Param("id") Integer id);
}




