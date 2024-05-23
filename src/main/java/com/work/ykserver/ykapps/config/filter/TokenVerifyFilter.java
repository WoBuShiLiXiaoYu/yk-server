package com.work.ykserver.ykapps.config.filter;

import cn.hutool.core.util.StrUtil;
import com.work.ykserver.ykapps.constant.RedisConstants;
import com.work.ykserver.ykapps.constant.RequestConstants;
import com.work.ykserver.ykapps.common.CodeEnum;
import com.work.ykserver.ykapps.pojo.User;
import com.work.ykserver.ykapps.util.*;
import com.work.ykserver.ykapps.vo.Result;
import com.work.ykserver.ykapps.vo.SecurityUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;


@Component
@Slf4j
public class TokenVerifyFilter extends OncePerRequestFilter {

    @Resource
    private RedisUtils redisUtils;

    // springboot 提供的线程池
    @Resource
    private ThreadPoolTaskExecutor poolTaskExecutor;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 如果为登录请求则放行
        if (RequestConstants.LOGIN_URI.equals(request.getRequestURI())) {
         filterChain.doFilter(request, response);
        }
        String token = null;
        if (RequestConstants.BATCH_EXPORT_EXCEL_URI.equals(request.getRequestURI())) {
            // 批量导出 Excel 请求，从请求路径中获取 token
            token = request.getParameter(RequestConstants.HEADER_TOKEN_NAME);
        } else if (RequestConstants.CHOOSE_EXPORT_EXCEL_URI.equals(request.getRequestURI())){
            // 选择导出
            token = request.getParameter(RequestConstants.HEADER_TOKEN_NAME);
        } else {
            // 一般请求从请求头获取 token
            token = request.getHeader(RequestConstants.HEADER_TOKEN_NAME);
        }

        // 验证 token 是否为空
        if (StrUtil.isEmpty(token)) {
            Result result = ResultUtils.fail(CodeEnum.TOKEN_IS_EMPTY);
            String resultJson = JSONUtils.toJSON(result);
            ResponseUtils.write(response, resultJson);
            return;
        }
        // 验证 token 是否正确
        if (!JWTUtils.verifyJWT(token)) {
            Result result = ResultUtils.fail(CodeEnum.TOKEN_IS_ERROR);
            String resultJson = JSONUtils.toJSON(result);
            ResponseUtils.write(response, resultJson);
            return;
        }
        // 解析 token
        //SecurityUser securityUser = JWTUtils.parseUserFromJWT(token);
        String userInfoFromJWT = JWTUtils.getUserInfoFromJWT(token);
        log.info("userInfoFromJWT：" + userInfoFromJWT);
        List<String> authorListFromJWT = JWTUtils.getAuthorListFromJWT(token);
        //SecurityUser securityUser = JSONUtils.toBean(userInfoFromJWT, SecurityUser.class);
        //User user = objectMapper.readValue(userInfoFromJWT, User.class);
        User user = JSONUtils.toBean(userInfoFromJWT, User.class);
        SecurityUser securityUser = new SecurityUser(user);
        log.info("securityUserJWT：" + securityUser);
        //List<SimpleGrantedAuthority> authorityList = authorListFromJWT.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();
        for (String authority : authorListFromJWT) {
             authorityList.add(new SimpleGrantedAuthority(authority));
        }
        // 从 redis 中获取存储的 token
        String redisToken = redisUtils.getRedisValue(RedisConstants.REDIS_JWT_KEY + securityUser.getUser().getId());
        // 判断 token 是否过期
        if (StrUtil.isEmpty(redisToken)) {
            Result result = ResultUtils.fail(CodeEnum.TOKEN_IS_EXPIRED);
            String resultJson = JSONUtils.toJSON(result);
            ResponseUtils.write(response, resultJson);
            return;
        }
        // 判断 token 是否一致
        if (!redisToken.equals(token)) {
            Result result = ResultUtils.fail(CodeEnum.TOKEN_IS_NONE_MATCH);
            String resultJson = JSONUtils.toJSON(result);
            ResponseUtils.write(response, resultJson);
            return;
        }
        // 验证通过
        log.info("验证jwt_user：" + securityUser);
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(securityUser, securityUser.getUser().getLoginPwd(), authorityList);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        // 刷新 token 时间，异步
        /*new Thread(() -> {
            String rememberMe = request.getHeader("rememberme");
            if (Boolean.parseBoolean(rememberMe)) {
                redisUtils.expire(RedisConstants.REDIS_JWT_KEY + securityUser.getUser().getId(),
                        RedisConstants.EXPIRE_TIME, TimeUnit.SECONDS);
            } else {
                redisUtils.expire(RedisConstants.REDIS_JWT_KEY + securityUser.getUser().getId(),
                        RedisConstants.DEFAULT_EXPIRE_TIME, TimeUnit.SECONDS);
            }
        }).start();*/

        poolTaskExecutor.execute(() -> {
            String rememberMe = request.getHeader(RequestConstants.HEADER_REMEMBER);
            if (Boolean.parseBoolean(rememberMe)) {
                redisUtils.expire(RedisConstants.REDIS_JWT_KEY + securityUser.getUser().getId(),
                        RedisConstants.EXPIRE_TIME, TimeUnit.SECONDS);
            } else {
                redisUtils.expire(RedisConstants.REDIS_JWT_KEY + securityUser.getUser().getId(),
                        RedisConstants.DEFAULT_EXPIRE_TIME, TimeUnit.SECONDS);
            }
        });


        filterChain.doFilter(request, response);
    }
}
