package com.work.ykserver.ykapps.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.work.ykserver.ykapps.pojo.DicType;
import com.work.ykserver.ykapps.pojo.DicValue;
import lombok.Data;

import java.util.List;

@Data
public class DicTypeVo extends DicType {
    private static final long serialVersionUID = 2181796577864774770L;

    private List<DicValue> dicValueList;
}
