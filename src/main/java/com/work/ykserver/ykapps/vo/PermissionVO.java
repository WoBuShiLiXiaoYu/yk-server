package com.work.ykserver.ykapps.vo;

import com.work.ykserver.ykapps.pojo.Permission;
import lombok.Data;

import java.util.List;

@Data
public class PermissionVO extends Permission {

    // 子权限列表
    private List<Permission> childList;
}
