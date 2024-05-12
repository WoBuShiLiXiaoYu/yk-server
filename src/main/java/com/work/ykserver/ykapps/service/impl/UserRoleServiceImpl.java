package com.work.ykserver.ykapps.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.work.ykserver.ykapps.pojo.UserRole;
import com.work.ykserver.ykapps.service.UserRoleService;
import com.work.ykserver.ykapps.mapper.UserRoleMapper;
import org.springframework.stereotype.Service;

/**
* @author 胡国海
* @description 针对表【t_user_role(用户角色关系表)】的数据库操作Service实现
* @createDate 2024-05-09 21:34:33
*/
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole>
    implements UserRoleService{

}




