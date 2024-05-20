package com.work.ykserver.ykapps.pojo;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 市场活动备注表
 * @TableName t_activity_remark
 */
@TableName(value ="t_activity_remark")
@Data
public class ActivityRemark implements Serializable {
    /**
     * 主键，自动增长，活动备注ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 活动ID
     */
    @TableField(value = "activity_id")
    private Integer activityId;

    /**
     * 备注内容
     */
    @TableField(value = "note_content")
    private String noteContent;

    /**
     * 备注创建时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 备注创建人
     */
    @TableField(value = "create_by")
    private Integer createBy;

    /**
     * 备注编辑时间
     */
    @TableField(value = "edit_time")
    private Date editTime;

    /**
     * 备注编辑人
     */
    @TableField(value = "edit_by")
    private Integer editBy;

    /**
     * 删除状态（0正常，1删除）
     */
    @TableField(value = "deleted")
    @TableLogic
    private Integer deleted;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @TableField(exist = false)
    private String createName;
    @TableField(exist = false)
    private String editName;

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
        ActivityRemark other = (ActivityRemark) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getActivityId() == null ? other.getActivityId() == null : this.getActivityId().equals(other.getActivityId()))
            && (this.getNoteContent() == null ? other.getNoteContent() == null : this.getNoteContent().equals(other.getNoteContent()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getCreateBy() == null ? other.getCreateBy() == null : this.getCreateBy().equals(other.getCreateBy()))
            && (this.getEditTime() == null ? other.getEditTime() == null : this.getEditTime().equals(other.getEditTime()))
            && (this.getEditBy() == null ? other.getEditBy() == null : this.getEditBy().equals(other.getEditBy()))
            && (this.getDeleted() == null ? other.getDeleted() == null : this.getDeleted().equals(other.getDeleted()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getActivityId() == null) ? 0 : getActivityId().hashCode());
        result = prime * result + ((getNoteContent() == null) ? 0 : getNoteContent().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getCreateBy() == null) ? 0 : getCreateBy().hashCode());
        result = prime * result + ((getEditTime() == null) ? 0 : getEditTime().hashCode());
        result = prime * result + ((getEditBy() == null) ? 0 : getEditBy().hashCode());
        result = prime * result + ((getDeleted() == null) ? 0 : getDeleted().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", activityId=").append(activityId);
        sb.append(", noteContent=").append(noteContent);
        sb.append(", createTime=").append(createTime);
        sb.append(", createBy=").append(createBy);
        sb.append(", editTime=").append(editTime);
        sb.append(", editBy=").append(editBy);
        sb.append(", deleted=").append(deleted);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}