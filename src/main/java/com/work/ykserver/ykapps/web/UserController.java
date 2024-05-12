package com.work.ykserver.ykapps.web;

import cn.hutool.core.util.ObjectUtil;
import com.work.ykserver.ykapps.bo.Page;
import com.work.ykserver.ykapps.common.CodeEnum;
import com.work.ykserver.ykapps.pojo.User;
import com.work.ykserver.ykapps.service.UserService;
import com.work.ykserver.ykapps.util.JSONUtils;
import com.work.ykserver.ykapps.util.ResultUtils;
import com.work.ykserver.ykapps.vo.Result;
import com.work.ykserver.ykapps.vo.SecurityUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
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
}
