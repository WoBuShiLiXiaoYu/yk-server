package com.work.ykserver.ykapps.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.work.ykserver.ykapps.bo.Page;
import com.work.ykserver.ykapps.common.CodeEnum;
import com.work.ykserver.ykapps.constant.RedisConstants;
import com.work.ykserver.ykapps.manager.RedisManager;
import com.work.ykserver.ykapps.mapper.PermissionMapper;
import com.work.ykserver.ykapps.mapper.RoleMapper;
import com.work.ykserver.ykapps.pojo.User;
import com.work.ykserver.ykapps.query.BaseQuery;
import com.work.ykserver.ykapps.query.UserQuery;
import com.work.ykserver.ykapps.service.UserService;
import com.work.ykserver.ykapps.mapper.UserMapper;
import com.work.ykserver.ykapps.util.CacheUtils;
import com.work.ykserver.ykapps.util.JWTUtils;
import com.work.ykserver.ykapps.util.PageUtils;
import com.work.ykserver.ykapps.util.ResultUtils;
import com.work.ykserver.ykapps.vo.Result;
import com.work.ykserver.ykapps.vo.SecurityUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
    @Resource
    private PasswordEncoder passwordEncoder;
    @Resource
    private RedisManager redisManager;

    /**
     * 登录查询
     * @param username
     * @return 安全用户对象
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.getUserByName(username);
        if (ObjectUtil.isNull(user)) {
            throw new UsernameNotFoundException("用户账号为空！");
        }

        // 查找用户角色、权限
        List<String> roles = roleMapper.getRoleByUserId(user.getId());
        List<String> permissions = permissionMapper.getPermissionByUserId(user.getId().toString());
        user.setRoleList(roles);
        user.setPermissionList(permissions);

        log.info("roles: " + roles);
        log.info("permissions: " + permissions);

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        // 设置角色
        if (!ObjectUtils.isEmpty(roles)) {
            for (String role : roles) {
                if (!ObjectUtils.isEmpty(role)) {
                    authorities.add(new SimpleGrantedAuthority(role));
                }

            }
        }
        // 设置权限
        if (!ObjectUtils.isEmpty(permissions)) {
            for (String permission : permissions) {
                // 不为空的值添加
                if (!ObjectUtils.isEmpty(permission)) {
                    authorities.add(new SimpleGrantedAuthority(permission));
                }
            }
        }

        log.info("user:" + user);
        log.info("authorities:" + authorities);
        SecurityUser securityUser = new SecurityUser(user);
        securityUser.setAuthorityList(authorities);
        log.info("securityUser:" + securityUser);
        return securityUser;
    }

    @Override
    public Page getUserListByPage(Integer currentPage) {
        Page page = PageUtils.getPage(currentPage);
        BaseQuery query = new BaseQuery();
        // 查询用户列表
        List<User> userList = userMapper.selectUserListByPage(query, page);
        // 查询总行数
        Integer total = userMapper.selectRowCount(query);
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
        // 从 token 中解析登录用户
        User loginUser = JWTUtils.parseUserFromJWT(userQuery.getToken());
        // 转换为 User 对象
        User user = new User();
        BeanUtil.copyProperties(userQuery, user);
        String newPwd = passwordEncoder.encode(user.getLoginPwd());
        user.setLoginPwd(newPwd);
        user.setCreateBy(loginUser.getId());
        user.setCreateTime(new Date());
        int result = userMapper.addUser(user);
        if (!(result >= 1)) {
            return ResultUtils.fail(CodeEnum.SAVE_USER_FAIL);
        }
        return ResultUtils.success(CodeEnum.OK);
    }

    @Override
    public Result userEdit(UserQuery userQuery) {
        // 从 token 中解析登录用户
        User loginUser = JWTUtils.parseUserFromJWT(userQuery.getToken());
        // 转换 User 对象
        User user = new User();
        BeanUtil.copyProperties(userQuery, user);
        if (StringUtils.hasText(user.getLoginPwd())) {
            // 修改密码
            String newPwd = passwordEncoder.encode(user.getLoginPwd());
            user.setLoginPwd(newPwd);
        }
        user.setEditBy(loginUser.getId());
        user.setEditTime(new Date());
        int result = userMapper.updateUserById(user);
        if (!(result >=1)) {
            return ResultUtils.fail(CodeEnum.EDIT_USER_FAIL);
        }
        return ResultUtils.success(CodeEnum.OK);
    }

    @Override
    public Result deleteUserById(Integer id) {
        int result = userMapper.deleteById(id);
        if (!(result >= 1)) {
            return ResultUtils.fail(CodeEnum.DELETE_USER_FAIL);
        }
        return ResultUtils.success(CodeEnum.OK);
    }

    @Override
    public Result deleteUserByIds(String[] ids) {
        List<Integer> userIds = Arrays.stream(ids).map(Integer::parseInt).collect(Collectors.toList());
        int result = userMapper.batchDeleteByIds(userIds);
        if (result <= 0) {
            return ResultUtils.fail(CodeEnum.DELETE_USER_FAIL);
        }
        return ResultUtils.success(CodeEnum.OK);
    }

    @Override
    public Result getOwnerList() {
        // 从 redis 中获取用户姓名
        // redis 中没有缓存用户姓名则从数据库中获取
        // 设置有效期缓存进 redis
        List<User> list = CacheUtils.getCacheData(() -> {
                    // 为缓存生产者提供数据
                    return redisManager.getValue(RedisConstants.REDIS_CACHE_KEY);
                },
                () -> {
                    // 为数据库生产者提供数据
                    return userMapper.selectUserList();
                },
                (t) -> {
                    // 为缓存消费者提供数据
                    redisManager.setValue(RedisConstants.REDIS_CACHE_KEY, t);
                });
        return ResultUtils.success(list);
    }
}




