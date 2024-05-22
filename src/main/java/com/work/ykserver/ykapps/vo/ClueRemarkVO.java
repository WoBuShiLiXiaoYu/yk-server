package com.work.ykserver.ykapps.vo;

import com.work.ykserver.ykapps.pojo.ClueRemark;
import lombok.Data;

@Data
public class ClueRemarkVO extends ClueRemark {

    private String noteWayValue;
    private String createName;
    private String editName;

}
