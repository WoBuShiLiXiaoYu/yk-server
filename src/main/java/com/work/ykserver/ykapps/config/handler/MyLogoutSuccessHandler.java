package com.work.ykserver.ykapps.config.handler;

import com.work.ykserver.ykapps.constant.RedisConstants;
import com.work.ykserver.ykapps.common.CodeEnum;
import com.work.ykserver.ykapps.util.JSONUtils;
import com.work.ykserver.ykapps.util.RedisUtils;
import com.work.ykserver.ykapps.util.ResponseUtils;
import com.work.ykserver.ykapps.util.ResultUtils;
import com.work.ykserver.ykapps.vo.Result;
import com.work.ykserver.ykapps.vo.SecurityUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class MyLogoutSuccessHandler implements LogoutSuccessHandler {

    @Resource
    private RedisUtils redisUtils;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        // 获取登录用户信息
        SecurityUser securityUser = (SecurityUser) authentication.getPrincipal();

        // 从 redis 中删除 token
        redisUtils.removeRedisValue(RedisConstants.REDIS_JWT_KEY + securityUser.getUser().getId());

        Result result = ResultUtils.success(CodeEnum.LOGOUT_SUCCESS);
        String resultJson = JSONUtils.toJSON(result);
        ResponseUtils.write(response, resultJson);

    }
}
