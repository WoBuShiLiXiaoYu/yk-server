package com.work.ykserver.ykapps.aop;

import com.work.ykserver.ykapps.constant.RequestConstants;
import com.work.ykserver.ykapps.common.DataScope;
import com.work.ykserver.ykapps.pojo.User;
import com.work.ykserver.ykapps.query.BaseQuery;
import com.work.ykserver.ykapps.util.JWTUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Aspect
@Component
@Slf4j
public class DataScopeAop {

    // 切入点
    @Pointcut(value = "@annotation(com.work.ykserver.ykapps.common.DataScope)")
    public void annotationPointCut() {}

    @Around(value = "annotationPointCut()")
    public Object dataScopeAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
        // 获取注解
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        DataScope annotation = signature.getMethod().getDeclaredAnnotation(DataScope.class);
        // 获取注解的值
        String myTableAlias = annotation.myTableAlias();
        String myTableField = annotation.myTableField();
        // 获取 request 对象
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        String token = request.getHeader(RequestConstants.HEADER_TOKEN_NAME);
        // 解析 token，获取用户信息
        User user = JWTUtils.parseUserFromJWT(token);
        // 获取用户角色
        List<String> roleList = user.getRoleList();
        log.info("roleList: " + roleList);
        // 不包含该角色查用户自己数据，否则查所有
        if (!roleList.contains("admin")) {
            // 获取目标方法的参数
            Object param = joinPoint.getArgs()[0];
            if (param instanceof BaseQuery) {
                BaseQuery query = (BaseQuery) param;
                // 设置查询语句过滤条件
                query.setFilterSQL(" and " + myTableAlias + "." + myTableField + "=" + user.getId());
            }
        }

        System.out.println("======目标方法执行前======");
        Object result = joinPoint.proceed();
        System.out.println("======目标方法执行后======");

        return result;

    }
}
