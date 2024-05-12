package com.work.ykserver.ykapps.vo;

import com.sun.org.apache.bcel.internal.classfile.Code;
import com.work.ykserver.ykapps.common.CodeEnum;
import lombok.ToString;

@ToString
public class Result {

    // 响应码
    private int code;

    // 返回的结果信息
    private String msg;

    // 返回的数据
    private Object data;

    public Result() {
    }

    public Result(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Result(int code, String msg) {
        this(code, msg, "");
    }

    public Result(Object data) {
        this.code = CodeEnum.OK.getCode();
        this.msg = CodeEnum.OK.getMsg();
        this.data = data;
    }

    public Result(CodeEnum codeEnum) {
        this.code = codeEnum.getCode();
        this.msg = codeEnum.getMsg();
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}
