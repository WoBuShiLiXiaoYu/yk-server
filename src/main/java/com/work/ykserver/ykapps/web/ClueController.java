package com.work.ykserver.ykapps.web;

import cn.hutool.core.util.ObjectUtil;
import com.work.ykserver.ykapps.service.ClueService;
import com.work.ykserver.ykapps.vo.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/clue")
public class ClueController {

    @Resource
    private ClueService clueService;

    @GetMapping("/getClueList")
    public Result getClueList(@RequestParam(value = "currentPage") Integer currentPage) {
        if (ObjectUtil.isEmpty(currentPage)) {
            currentPage = 1;
        }
        return clueService.getClueListByPage(currentPage);
    }
}
