package com.work.ykserver.ykapps.pojo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.work.ykserver.ykapps.config.converter.*;
import lombok.Data;

/**
 * 线索表
 * @TableName t_clue
 */
@ExcelIgnoreUnannotated // 没有加 @ExcelProperty 的字段不参与 Excel 读写
@TableName(value ="t_clue")
@Data
public class Clue implements Serializable {
    /**
     * 主键，自动增长，线索ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 线索所属人ID
     */
    @TableField(value = "owner_id")
    @ExcelProperty(value = "负责人")
    private Integer ownerId;

    /**
     * 活动ID
     */
    @TableField(value = "activity_id")
    @ExcelProperty(value = "所属活动")
    private Integer activityId;

    /**
     * 姓名
     */
    @TableField(value = "full_name")
    @ExcelProperty(value = "姓名")
    private String fullName;

    /**
     * 称呼
     */
    @TableField(value = "appellation")
    @ExcelProperty(value = "称呼", converter = AppellationConverter.class)
    private Integer appellation;

    /**
     * 手机号
     */
    @TableField(value = "phone")
    @ExcelProperty(value = "手机号")
    private String phone;

    /**
     * 微信号
     */
    @TableField(value = "weixin")
    @ExcelProperty(value = "微信号")
    private String weixin;

    /**
     * QQ号
     */
    @TableField(value = "qq")
    @ExcelProperty(value = "QQ号")
    private String qq;

    /**
     * 邮箱
     */
    @TableField(value = "email")
    @ExcelProperty(value = "邮箱")
    private String email;

    /**
     * 年龄
     */
    @TableField(value = "age")
    @ExcelProperty(value = "年龄")
    private Integer age;

    /**
     * 职业
     */
    @TableField(value = "job")
    @ExcelProperty(value = "职业")
    private String job;

    /**
     * 年收入
     */
    @TableField(value = "year_income")
    @ExcelProperty(value = "年收入")
    private BigDecimal yearIncome;

    /**
     * 地址
     */
    @TableField(value = "address")
    @ExcelProperty(value = "地址")
    private String address;

    /**
     * 是否需要贷款（0不需要，1需要）
     */
    @TableField(value = "need_loan")
    @ExcelProperty(value = "是否贷款", converter = NeedLoanConverter.class)
    private Integer needLoan;

    /**
     * 意向状态
     */
    @TableField(value = "intention_state")
    @ExcelProperty(value = "意向状态", converter = IntentionStateConverter.class)
    private Integer intentionState;

    /**
     * 意向产品
     */
    @TableField(value = "intention_product")
    @ExcelProperty(value = "意向产品", converter = IntentionProductConverter.class)
    private Integer intentionProduct;

    /**
     * 线索状态
     */
    @TableField(value = "state")
    @ExcelProperty(value = "线索状态", converter = StateConverter.class)
    private Integer state;

    /**
     * 线索来源
     */
    @TableField(value = "source")
    @ExcelProperty(value = "线索来源", converter = SourceConverter.class)
    private Integer source;

    /**
     * 线索描述
     */
    @TableField(value = "description")
    @ExcelProperty(value = "线索描述")
    private String description;

    /**
     * 下次联系时间
     */
    @TableField(value = "next_contact_time")
    @ExcelProperty(value = "下次联系时间")
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

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Clue other = (Clue) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getOwnerId() == null ? other.getOwnerId() == null : this.getOwnerId().equals(other.getOwnerId()))
            && (this.getActivityId() == null ? other.getActivityId() == null : this.getActivityId().equals(other.getActivityId()))
            && (this.getFullName() == null ? other.getFullName() == null : this.getFullName().equals(other.getFullName()))
            && (this.getAppellation() == null ? other.getAppellation() == null : this.getAppellation().equals(other.getAppellation()))
            && (this.getPhone() == null ? other.getPhone() == null : this.getPhone().equals(other.getPhone()))
            && (this.getWeixin() == null ? other.getWeixin() == null : this.getWeixin().equals(other.getWeixin()))
            && (this.getQq() == null ? other.getQq() == null : this.getQq().equals(other.getQq()))
            && (this.getEmail() == null ? other.getEmail() == null : this.getEmail().equals(other.getEmail()))
            && (this.getAge() == null ? other.getAge() == null : this.getAge().equals(other.getAge()))
            && (this.getJob() == null ? other.getJob() == null : this.getJob().equals(other.getJob()))
            && (this.getYearIncome() == null ? other.getYearIncome() == null : this.getYearIncome().equals(other.getYearIncome()))
            && (this.getAddress() == null ? other.getAddress() == null : this.getAddress().equals(other.getAddress()))
            && (this.getNeedLoan() == null ? other.getNeedLoan() == null : this.getNeedLoan().equals(other.getNeedLoan()))
            && (this.getIntentionState() == null ? other.getIntentionState() == null : this.getIntentionState().equals(other.getIntentionState()))
            && (this.getIntentionProduct() == null ? other.getIntentionProduct() == null : this.getIntentionProduct().equals(other.getIntentionProduct()))
            && (this.getState() == null ? other.getState() == null : this.getState().equals(other.getState()))
            && (this.getSource() == null ? other.getSource() == null : this.getSource().equals(other.getSource()))
            && (this.getDescription() == null ? other.getDescription() == null : this.getDescription().equals(other.getDescription()))
            && (this.getNextContactTime() == null ? other.getNextContactTime() == null : this.getNextContactTime().equals(other.getNextContactTime()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getCreateBy() == null ? other.getCreateBy() == null : this.getCreateBy().equals(other.getCreateBy()))
            && (this.getEditTime() == null ? other.getEditTime() == null : this.getEditTime().equals(other.getEditTime()))
            && (this.getEditBy() == null ? other.getEditBy() == null : this.getEditBy().equals(other.getEditBy()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getOwnerId() == null) ? 0 : getOwnerId().hashCode());
        result = prime * result + ((getActivityId() == null) ? 0 : getActivityId().hashCode());
        result = prime * result + ((getFullName() == null) ? 0 : getFullName().hashCode());
        result = prime * result + ((getAppellation() == null) ? 0 : getAppellation().hashCode());
        result = prime * result + ((getPhone() == null) ? 0 : getPhone().hashCode());
        result = prime * result + ((getWeixin() == null) ? 0 : getWeixin().hashCode());
        result = prime * result + ((getQq() == null) ? 0 : getQq().hashCode());
        result = prime * result + ((getEmail() == null) ? 0 : getEmail().hashCode());
        result = prime * result + ((getAge() == null) ? 0 : getAge().hashCode());
        result = prime * result + ((getJob() == null) ? 0 : getJob().hashCode());
        result = prime * result + ((getYearIncome() == null) ? 0 : getYearIncome().hashCode());
        result = prime * result + ((getAddress() == null) ? 0 : getAddress().hashCode());
        result = prime * result + ((getNeedLoan() == null) ? 0 : getNeedLoan().hashCode());
        result = prime * result + ((getIntentionState() == null) ? 0 : getIntentionState().hashCode());
        result = prime * result + ((getIntentionProduct() == null) ? 0 : getIntentionProduct().hashCode());
        result = prime * result + ((getState() == null) ? 0 : getState().hashCode());
        result = prime * result + ((getSource() == null) ? 0 : getSource().hashCode());
        result = prime * result + ((getDescription() == null) ? 0 : getDescription().hashCode());
        result = prime * result + ((getNextContactTime() == null) ? 0 : getNextContactTime().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getCreateBy() == null) ? 0 : getCreateBy().hashCode());
        result = prime * result + ((getEditTime() == null) ? 0 : getEditTime().hashCode());
        result = prime * result + ((getEditBy() == null) ? 0 : getEditBy().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", ownerId=").append(ownerId);
        sb.append(", activityId=").append(activityId);
        sb.append(", fullName=").append(fullName);
        sb.append(", appellation=").append(appellation);
        sb.append(", phone=").append(phone);
        sb.append(", weixin=").append(weixin);
        sb.append(", qq=").append(qq);
        sb.append(", email=").append(email);
        sb.append(", age=").append(age);
        sb.append(", job=").append(job);
        sb.append(", yearIncome=").append(yearIncome);
        sb.append(", address=").append(address);
        sb.append(", needLoan=").append(needLoan);
        sb.append(", intentionState=").append(intentionState);
        sb.append(", intentionProduct=").append(intentionProduct);
        sb.append(", state=").append(state);
        sb.append(", source=").append(source);
        sb.append(", description=").append(description);
        sb.append(", nextContactTime=").append(nextContactTime);
        sb.append(", createTime=").append(createTime);
        sb.append(", createBy=").append(createBy);
        sb.append(", editTime=").append(editTime);
        sb.append(", editBy=").append(editBy);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}