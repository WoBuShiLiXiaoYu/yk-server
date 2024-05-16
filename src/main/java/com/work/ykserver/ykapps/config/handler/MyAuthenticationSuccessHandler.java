package com.work.ykserver.ykapps.config.handler;

import cn.hutool.core.convert.Convert;
import cn.hutool.json.JSONUtil;
import com.work.ykserver.ykapps.Constant.RedisConstants;
import com.work.ykserver.ykapps.Constant.RequestConstants;
import com.work.ykserver.ykapps.util.*;
import com.work.ykserver.ykapps.vo.Result;
import com.work.ykserver.ykapps.vo.SecurityUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Resource
    private RedisUtils redisUtils;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        // 获取当前用户信息
        SecurityUser securityUser = (SecurityUser) authentication.getPrincipal();
        log.info("token SecurityUser: " + securityUser);

        // 登录成功生成 jwt
        //String userJson = JSONUtils.toJSON(securityUser);
        // 用户信息
        String userInfo = JSONUtils.toJSON(securityUser.getUser());
        //String jwt = JWTUtils.createJWT(userJson);
        // 权限信息
        List<SimpleGrantedAuthority> authorityList = securityUser.getAuthorityList();
        List<String> authorList = new ArrayList<>();
        for (SimpleGrantedAuthority authority : authorityList) {
            String author = authority.getAuthority();
            authorList.add(author);
        }
        String jwt = JWTUtils.createJWT(userInfo, authorList);


        // 将 jwt 存储到 redis 中
        redisUtils.setRedisValue(RedisConstants.REDIS_JWT_KEY + securityUser.getUser().getId(), jwt);
        // 是否免登录
        String rememberMe = request.getParameter(RequestConstants.PARAM_REMEMBER);
        if (Boolean.parseBoolean(rememberMe)) {
            redisUtils.expire(RedisConstants.REDIS_JWT_KEY + securityUser.getUser().getId(),
                    RedisConstants.EXPIRE_TIME, TimeUnit.SECONDS);
        } else {
            redisUtils.expire(RedisConstants.REDIS_JWT_KEY + securityUser.getUser().getId(),
                    RedisConstants.DEFAULT_EXPIRE_TIME, TimeUnit.SECONDS);
        }


        // 登录成功返回信息
        Result result = ResultUtils.success(jwt);
        log.info(result.toString());
        // String resultJson = JSONUtil.toJsonStr(result);
        String resultJson = JSONUtils.toJSON(result);
        log.info(resultJson);
        ResponseUtils.write(response, resultJson);
    }
}
