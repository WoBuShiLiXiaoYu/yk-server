package com.work.ykserver.ykapps.common;

import java.lang.annotation.*;

/**
 * 数据范围
 */
@Target(value = ElementType.METHOD)
@Retention(value = RetentionPolicy.RUNTIME)
@Documented
public @interface DataScope {

    /**
     * 表的别名
     */
    public String myTableAlias() default "";

    /**
     * 表的字段名
     */
    public String myTableField() default "";
}
