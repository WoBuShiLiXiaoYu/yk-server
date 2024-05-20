package com.work.ykserver.ykapps.query;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class ClueQuery extends BaseQuery{
    /**
     * 主键，自动增长，线索ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 线索所属人ID
     */
    @TableField(value = "owner_id")
    private Integer ownerId;

    /**
     * 活动ID
     */
    @TableField(value = "activity_id")
    private Integer activityId;

    /**
     * 姓名
     */
    @TableField(value = "full_name")
    private String fullName;

    /**
     * 称呼
     */
    @TableField(value = "appellation")
    private Integer appellation;

    /**
     * 手机号
     */
    @TableField(value = "phone")
    private String phone;

    /**
     * 微信号
     */
    @TableField(value = "weixin")
    private String weixin;

    /**
     * QQ号
     */
    @TableField(value = "qq")
    private String qq;

    /**
     * 邮箱
     */
    @TableField(value = "email")
    private String email;

    /**
     * 年龄
     */
    @TableField(value = "age")
    private Integer age;

    /**
     * 职业
     */
    @TableField(value = "job")
    private String job;

    /**
     * 年收入
     */
    @TableField(value = "year_income")
    private BigDecimal yearIncome;

    /**
     * 地址
     */
    @TableField(value = "address")
    private String address;

    /**
     * 是否需要贷款（0不需要，1需要）
     */
    @TableField(value = "need_loan")
    private Integer needLoan;

    /**
     * 意向状态
     */
    @TableField(value = "intention_state")
    private Integer intentionState;

    /**
     * 意向产品
     */
    @TableField(value = "intention_product")
    private Integer intentionProduct;

    /**
     * 线索状态
     */
    @TableField(value = "state")
    private Integer state;

    /**
     * 线索来源
     */
    @TableField(value = "source")
    private Integer source;

    /**
     * 线索描述
     */
    @TableField(value = "description")
    private String description;

    /**
     * 下次联系时间
     */
    @TableField(value = "next_contact_time")
    private Date nextContactTime;

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
}
