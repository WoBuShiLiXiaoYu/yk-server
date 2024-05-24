package com.work.ykserver.ykapps.web;

import cn.hutool.core.util.ObjectUtil;
import com.work.ykserver.ykapps.bo.Page;
import com.work.ykserver.ykapps.common.CodeEnum;
import com.work.ykserver.ykapps.constant.RequestConstants;
import com.work.ykserver.ykapps.pojo.User;
import com.work.ykserver.ykapps.query.UserQuery;
import com.work.ykserver.ykapps.service.UserService;
import com.work.ykserver.ykapps.util.JSONUtils;
import com.work.ykserver.ykapps.util.ResultUtils;
import com.work.ykserver.ykapps.vo.Result;
import com.work.ykserver.ykapps.vo.SecurityUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping("/login/info")
    public Result getLoginUserInfo(Authentication authentication) {
        SecurityUser securityUser = (SecurityUser) authentication.getPrincipal();
        return ResultUtils.success(securityUser);
    }

    @GetMapping("/login/free")
    public Result freeLogin() {
        return ResultUtils.success(CodeEnum.OK.getCode(), "登录成功！");
    }

    @PreAuthorize(value = "hasAnyAuthority('user:list')")
    @GetMapping("/userList")
    public Result getUserList(@RequestParam(value = "currentPage", required = false)
                                          Integer currentPage) {
        log.info("currentPage:" + currentPage);
        // 判断当前页数是否为空，如果为空则默认为 1
        if (currentPage == null) {
            currentPage = 1;
        }
        Page page = userService.getUserListByPage(currentPage);

        if (ObjectUtil.isEmpty(page)) {
            return ResultUtils.fail(CodeEnum.GET_USER_LIST_IS_NULL);
        }
        return ResultUtils.success(CodeEnum.OK.getCode(), "", page);
    }


    @PreAuthorize(value = "hasAnyAuthority('user:view')")
    @GetMapping("/userDetailInfo")
    public Result getUserDetailInfo(@RequestParam(value = "id") Integer id) {
        if (id == null) {
            return ResultUtils.fail(CodeEnum.PARAMETERS_IS_NULL);
        }
        User user = userService.getUserDetailInfoById(id);
        return ResultUtils.success(user);
    }

    @PreAuthorize(value = "hasAnyAuthority('user:add')")
    @PostMapping("/userSave")
    public Result userSave(UserQuery userQuery, @RequestHeader(value = RequestConstants.HEADER_TOKEN_NAME) String token) {
        if (ObjectUtil.isEmpty(userQuery)) {
            return ResultUtils.fail(CodeEnum.PARAMETERS_IS_NULL);
        }
        userQuery.setToken(token);
        return userService.userSave(userQuery);
    }

    @PreAuthorize(value = "hasAnyAuthority('user:edit')")
    @PutMapping("/editUserInfo")
    public Result editUser(UserQuery userQuery, @RequestHeader(value = RequestConstants.HEADER_TOKEN_NAME) String token) throws Exception {
        if (ObjectUtil.isEmpty(userQuery)) {
            return ResultUtils.fail(CodeEnum.PARAMETERS_IS_NULL);
        }
        userQuery.setToken(token);
        return userService.userEdit(userQuery);
    }

    @PreAuthorize(value = "hasAnyAuthority('user:delete')")
    @DeleteMapping("/deleteUser")
    public Result deleteUser(@RequestParam(value = "id") Integer id) {
        if (ObjectUtil.isEmpty(id)) {
            return ResultUtils.fail(CodeEnum.PARAMETERS_IS_NULL);
        }
        return userService.deleteUserById(id);
    }

    @PreAuthorize(value = "hasAnyAuthority('user:delete')")
    @DeleteMapping("/batchDeleteUser")
    public Result batchDeleteUser(@RequestParam(value = "ids") String[] ids) {
        if (ObjectUtil.isEmpty(ids)) {
            return ResultUtils.fail(CodeEnum.PARAMETERS_IS_NULL);
        }
        return userService.deleteUserByIds(ids);
    }

    @GetMapping("/getOwner")
    public Result getOwner() {
        return userService.getOwnerList();
    }

}
