package com.work.ykserver.ykapps.query;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

@Data
public class ActivityRemarkQuery extends BaseQuery{

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
    private Integer deleted;
}
