package com.work.ykserver.ykapps.common;

import lombok.Getter;
import lombok.Setter;

@Getter
public enum CodeEnum {
    OK(00000, "成功"),
    FAIL(500000, "失败"),
    TOKEN_IS_EMPTY(40901, "token 参数为空，请登录！"),
    TOKEN_IS_ERROR(40801, "token 参数有误！"),
    TOKEN_IS_EXPIRED(40701, "token 已过期！"),
    TOKEN_IS_NONE_MATCH(40601, "token 不一致"),
    LOGOUT_SUCCESS(00000, "退出成功！"),
    GET_USER_LIST_IS_NULL(30001, "查询用户列表为空！")
    ;

    private final int code;
    private final String msg;

    CodeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
