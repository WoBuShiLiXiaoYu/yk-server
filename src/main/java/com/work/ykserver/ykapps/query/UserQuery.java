package com.work.ykserver.ykapps.query;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class UserQuery extends BaseQuery {
    /**
     * 主键，自动增长，登录用户ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 登录账号
     */
    @TableField(value = "login_act")
    private String loginAct;

    /**
     * 登录密码
     */
    @TableField(value = "login_pwd")
    private String loginPwd;

    /**
     * 用户姓名
     */
    @TableField(value = "name")
    private String name;

    /**
     * 用户手机
     */
    @TableField(value = "phone")
    private String phone;

    /**
     * 用户邮箱
     */
    @TableField(value = "email")
    private String email;

    /**
     * 账户是否没有过期，0已过期 1正常
     */
    @TableField(value = "account_no_expired")
    private Integer accountNoExpired;

    /**
     * 密码是否没有过期，0已过期 1正常
     */
    @TableField(value = "credentials_no_expired")
    private Integer credentialsNoExpired;

    /**
     * 账号是否没有锁定，0已锁定 1正常
     */
    @TableField(value = "account_no_locked")
    private Integer accountNoLocked;

    /**
     * 账号是否启用，0禁用 1启用
     */
    @TableField(value = "account_enabled")
    private Integer accountEnabled;
}
