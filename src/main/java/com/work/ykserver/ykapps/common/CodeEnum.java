package com.work.ykserver.ykapps.common;

import lombok.Getter;
import lombok.Setter;

@Getter
public enum CodeEnum {
    OK(00000, "成功"),
    FAIL(50000, "失败"),
    TOKEN_IS_EMPTY(40901, "token 参数为空，请登录！"),
    TOKEN_IS_ERROR(40801, "token 参数有误！"),
    TOKEN_IS_EXPIRED(40701, "token 已过期！"),
    TOKEN_IS_NONE_MATCH(40601, "token 不一致"),
    LOGOUT_SUCCESS(00000, "退出成功！"),
    GET_USER_LIST_IS_NULL(30001, "查询用户列表为空！"),
    GET_ACTIVITY_LIST_IS_NULL(30101, "查询活动列表为空！"),
    SAVE_USER_FAIL(30002, "新增用户失败！"),
    SAVE_ACTIVITY_FAIL(30102, "录入市场活动失败！"),
    SAVE_ACTIVITY_REMARK_FAIL(30202, "添加市场活动备注失败！"),
    SAVE_CLUE_FAIL(30502, "录入线索失败！"),
    SAVE_CLUE_REMARK_FAIL(30602, "添加线索跟踪信息失败！"),
    EDIT_USER_FAIL(30003, "修改用户失败！"),
    EDIT_ACTIVITY_FAIL(30103, "修改市场活动失败！"),
    EDIT_ACTIVITY_REMARK_FAIL(30203, "修改市场活动备注失败！"),
    EDIT_CLUE_REMARK_FAIL(30603, "修改线索跟踪信息失败！"),
    EDIT_CLUE_FAIL(30503, "修改线索失败！"),
    DELETE_USER_FAIL(30004, "删除用户失败！"),
    DELETE_ACTIVITY_FAIL(30104, "删除市场活动失败！"),
    DELETE_ACTIVITY_REMARK_FAIL(30204, "删除市场活动备注失败！"),
    DELETE_CLUE_FAIL(30504, "删除线索失败！"),
    DELETE_CLUE_REMARK_FAIL(30604, "删除线索跟踪信息失败！"),
    CLUE_TO_CUSTOMER_FAIL(30801, "线索转换客户失败！"),
    PARAMETERS_IS_NULL(30100, "请求参数为空！"),
    DATA_ACCESS_EXCEPTION(10001, "数据库操作失败！"),
    ACCESS_DENIED(500, "权限不足！");
    ;

    private final int code;
    private final String msg;

    CodeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
