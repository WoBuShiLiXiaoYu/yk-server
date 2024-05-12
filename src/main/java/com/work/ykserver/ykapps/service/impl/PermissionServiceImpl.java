package com.work.ykserver.ykapps.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.work.ykserver.ykapps.pojo.Permission;
import com.work.ykserver.ykapps.service.PermissionService;
import com.work.ykserver.ykapps.mapper.PermissionMapper;
import org.springframework.stereotype.Service;

/**
* @author 胡国海
* @description 针对表【t_permission(权限表)】的数据库操作Service实现
* @createDate 2024-05-09 21:34:33
*/
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission>
    implements PermissionService{

}




