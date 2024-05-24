package com.work.ykserver.ykapps.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.work.ykserver.ykapps.vo.PermissionVO;
import lombok.Data;

/**
 * 用户表
 * @TableName t_user
 */
@TableName(value ="t_user")
@Data
public class User implements Serializable {
    /**
     * 主键，自动增长，用户ID
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

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 创建人
     */
    @TableField(value = "create_by")
    private Integer createBy;

    /**
     * 编辑时间
     */
    @TableField(value = "edit_time")
    private Date editTime;

    /**
     * 编辑人
     */
    @TableField(value = "edit_by")
    private Integer editBy;

    /**
     * 最近登录时间
     */
    @TableField(value = "last_login_time")
    private Date lastLoginTime;

    @TableField(exist = false)
    private String createName;
    @TableField(exist = false)
    private String editName;
    @TableField(exist = false)
    private List<String> roleList;
    @TableField(exist = false)
    private List<String> permissionList;
    @TableField(exist = false)
    private List<PermissionVO> menuPermissionList;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;


}