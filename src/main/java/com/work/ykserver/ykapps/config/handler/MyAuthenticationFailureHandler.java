package com.work.ykserver.ykapps.config.handler;

import cn.hutool.json.JSONUtil;
import com.work.ykserver.ykapps.util.JSONUtils;
import com.work.ykserver.ykapps.util.ResponseUtils;
import com.work.ykserver.ykapps.util.ResultUtils;
import com.work.ykserver.ykapps.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Slf4j
public class MyAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        // 登录失败
        Result result = ResultUtils.fail(exception.getMessage());
        // String resultJson = JSONUtil.toJsonStr(result);
        String resultJson = JSONUtils.toJSON(result);
        log.info(resultJson);
        ResponseUtils.write(response, resultJson);
    }
}
