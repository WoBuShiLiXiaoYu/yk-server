package com.work.ykserver.ykapps.common;

import lombok.Data;
import lombok.Getter;

@Getter
public enum DicEnum {
    APPELLATION("appellation"),
    NEEDLOAN("needLoan"),
    INTENTIONSTATE("intentionState"),
    INTENTIONPRODUCT("intentionProduct"),
    STATE("clueState"),
    SOURCE("source"),
    ;

    private String code;

    DicEnum() {
    }

    DicEnum(String code) {
        this.code = code;
    }

}
