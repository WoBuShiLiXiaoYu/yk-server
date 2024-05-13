package com.work.ykserver.ykapps.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.work.ykserver.ykapps.Constant.PageConstants;
import com.work.ykserver.ykapps.Constant.RedisConstants;
import com.work.ykserver.ykapps.bo.Page;
import com.work.ykserver.ykapps.common.CodeEnum;
import com.work.ykserver.ykapps.mapper.PermissionMapper;
import com.work.ykserver.ykapps.mapper.RoleMapper;
import com.work.ykserver.ykapps.pojo.User;
import com.work.ykserver.ykapps.query.UserQuery;
import com.work.ykserver.ykapps.service.UserService;
import com.work.ykserver.ykapps.mapper.UserMapper;
import com.work.ykserver.ykapps.util.PageUtils;
import com.work.ykserver.ykapps.util.ResultUtils;
import com.work.ykserver.ykapps.vo.Result;
import com.work.ykserver.ykapps.vo.SecurityUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
* @author 胡国海
* @description 针对表【t_user(用户表)】的数据库操作Service实现
* @createDate 2024-05-09 21:34:33
*/
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService, UserDetailsService {

    @Resource
    private UserMapper userMapper;
    @Resource
    private PermissionMapper permissionMapper;
    @Resource
    private RoleMapper roleMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.getUserByName(username);
        if (ObjectUtil.isNull(user)) {
            throw new UsernameNotFoundException("用户账号为空！");
        }

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        // 设置角色
        List<String> roles = roleMapper.getRoleByUserId(user.getId());

        if (ObjectUtil.isNotEmpty(roles)) {
            for (String role : roles) {
                if (StrUtil.isNotEmpty(role)) {
                    authorities.add(new SimpleGrantedAuthority(role));
                }
            }
        }

        // 设置权限
        List<String> permissions = permissionMapper.getPermissionByUserId(user.getId().toString());
        if (ObjectUtil.isNotEmpty(permissions)) {
            for (String permission : permissions) {
                if (StrUtil.isNotEmpty(permission)) {
                    authorities.add(new SimpleGrantedAuthority(permission));
                }
            }
        }

        log.info("authorities:" + authorities);
        SecurityUser securityUser = new SecurityUser(user);
        log.info("user:" + user);
        securityUser.setAuthorityList(authorities);
        return securityUser;
    }

    @Override
    public Page getUserListByPage(Integer currentPage) {
        Page page = PageUtils.getPage(currentPage);
        // 查询用户列表
        List<User> userList = userMapper.selectUserListByPage(page);
        // 查询总行数
        Integer total = userMapper.selectRowCount();
        // 设置 page
        page = PageUtils.pageSetting(page, userList, total);

        return page;
    }

    @Override
    public User getUserDetailInfoById(Integer id) {
        return userMapper.selectUserDetailById(id);
    }

    @Override
    public Result userSave(UserQuery userQuery) {
        // 转换为 User 对象
        User user = new User();
        BeanUtil.copyProperties(userQuery, user);
        user.setCreateBy(userQuery.getId());
        user.setCreateTime(new Date());
        int result = userMapper.addUser(user);
        if (!(result >= 1)) {
            return ResultUtils.fail(CodeEnum.SAVE_USER_FAIL);
        }
        return ResultUtils.success(CodeEnum.OK);
    }
}




