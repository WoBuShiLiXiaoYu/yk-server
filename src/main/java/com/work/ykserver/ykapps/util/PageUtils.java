package com.work.ykserver.ykapps.util;

import com.work.ykserver.ykapps.Constant.PageConstants;
import com.work.ykserver.ykapps.bo.Page;

import java.util.List;

public class PageUtils {
    private PageUtils() {
    }

    public static Page getPage(Integer current) {
        Page page = new Page();
        Integer pageNum = (current - 1) * PageConstants.DEFAULT_PAGE_SIZE;
        page.setPageNum(pageNum);
        page.setPageSize(PageConstants.DEFAULT_PAGE_SIZE);
        return page;
    }

    public static <T> Page pageSetting(Page page, List<T> list, Integer total) {
        page.setList(list);
        page.setTotal(total);
        Integer pageCount = total % page.getPageSize() == 0 ? total / page.getPageSize() : total / page.getPageSize() + 1;
        page.setPageCount(pageCount);
        return page;
    }
}
