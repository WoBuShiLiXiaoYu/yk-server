package com.work.ykserver.ykapps.util;

import com.work.ykserver.ykapps.common.CodeEnum;
import com.work.ykserver.ykapps.vo.Result;

public class ResultUtils {
    private ResultUtils() {}

    /**
     * 成功
     * @param code
     * @param msg
     * @param data
     * @return
     */
    public static Result success(int code, String msg, Object data) {
        return new Result(code, msg, data);
    }

    public static Result success(int code, String msg) {
        return new Result(code, msg);
    }

    public static Result success(Object data) {
        return new Result(data);
    }

    public static Result success(CodeEnum codeEnum) {
        return new Result(codeEnum.getCode(), codeEnum.getMsg());
    }


    /**
     * 失败
     * @return
     */
    public static Result fail() {
        return new Result(CodeEnum.FAIL.getCode(), CodeEnum.FAIL.getMsg());
    }

    public static Result fail(String msg) {
        return new Result(CodeEnum.FAIL.getCode(), msg);
    }

    public static Result fail(CodeEnum codeEnum) {
        return new Result(codeEnum);
    }
}
