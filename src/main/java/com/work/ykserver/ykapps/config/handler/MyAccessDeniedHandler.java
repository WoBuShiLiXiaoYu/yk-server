package com.work.ykserver.ykapps.config.handler;


import com.work.ykserver.ykapps.common.CodeEnum;
import com.work.ykserver.ykapps.util.JSONUtils;
import com.work.ykserver.ykapps.util.ResponseUtils;
import com.work.ykserver.ykapps.util.ResultUtils;
import com.work.ykserver.ykapps.vo.Result;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 没有权限时的处理器
 *
 */
@Component
public class MyAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {

        //登录失败的统一结果
        Result result = ResultUtils.fail(CodeEnum.ACCESS_DENIED);

        //转成 json
        String resultJSON = JSONUtils.toJSON(result);

        //返回给前端
        ResponseUtils.write(response, resultJSON);
    }
}
