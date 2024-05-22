package com.work.ykserver.ykapps.query;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

@Data
public class ClueRemarkQuery extends BaseQuery{
    /**
     * 主键，自动增长，线索备注ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 线索ID
     */
    @TableField(value = "clue_id")
    private Integer clueId;

    /**
     * 跟踪方式
     */
    @TableField(value = "note_way")
    private Integer noteWay;

    /**
     * 跟踪内容
     */
    @TableField(value = "note_content")
    private String noteContent;

    /**
     * 跟踪时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 跟踪人
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
     * 删除状态（0正常，1删除）
     */
    @TableField(value = "deleted")
    private Integer deleted;
}
