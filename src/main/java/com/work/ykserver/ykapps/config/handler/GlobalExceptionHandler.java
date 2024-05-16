package com.work.ykserver.ykapps.config.handler;

import com.work.ykserver.ykapps.common.CodeEnum;
import com.work.ykserver.ykapps.util.ResultUtils;
import com.work.ykserver.ykapps.vo.Result;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.NonTransientDataAccessException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public Result handlerException(Exception e) {
        e.printStackTrace();
        return ResultUtils.fail(e.getMessage());
    }

    @ExceptionHandler(value = DataAccessException.class)
    public Result handlerException2(DataAccessException e) {
        e.printStackTrace();
        return ResultUtils.fail(CodeEnum.DATA_ACCESS_EXCEPTION);
    }
}
