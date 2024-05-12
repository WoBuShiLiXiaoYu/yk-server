package com.work.ykserver.ykapps.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Page<T> {
    // 显示条数
    private Integer pageSize;
    // 当前页
    private Integer pageNum;
    // 总记录条数
    private Integer total;
    // 总页数
    private Integer pageCount;
    // 查询集合
    private List<T> list;

}
