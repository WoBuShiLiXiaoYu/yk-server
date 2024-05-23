package com.work.ykserver.ykapps.web;

import cn.hutool.core.util.ObjectUtil;
import com.work.ykserver.ykapps.constant.RequestConstants;
import com.work.ykserver.ykapps.query.ClueRemarkQuery;
import com.work.ykserver.ykapps.service.ClueRemarkService;
import com.work.ykserver.ykapps.vo.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/clueRemark")
public class ClueRemarkController {

    @Resource
    private ClueRemarkService clueRemarkService;

    @PostMapping("/addClueRemark")
    private Result addClueRemark(@RequestBody  ClueRemarkQuery clueRemarkQuery, @RequestHeader(value = RequestConstants.HEADER_TOKEN_NAME) String token) {
        clueRemarkQuery.setToken(token);
        return clueRemarkService.addClueRemark(clueRemarkQuery);
    }

    @GetMapping("/getClueRemarkList/{clueId}")
    private Result getClueRemarkList(@PathVariable(value = "clueId") Integer clueId,
                                     @RequestParam(value = "currentPage") Integer currentPage) {
        if (ObjectUtil.isEmpty(currentPage)) {
            currentPage = 1;
        }
        return clueRemarkService.getClueRemarkListByPage(clueId, currentPage);
    }

    @GetMapping("/getNoteContent/{id}")
    public Result getNoteContent(@PathVariable(value = "id") Integer id) {
        return clueRemarkService.getNoteContentById(id);
    }

    @PutMapping("/editClueRemark")
    public Result editClueRemark(@RequestBody ClueRemarkQuery clueRemarkQuery, @RequestHeader(value = RequestConstants.HEADER_TOKEN_NAME) String token) throws Exception {
        clueRemarkQuery.setToken(token);
        return clueRemarkService.editClueRemark(clueRemarkQuery);
    }

    @DeleteMapping("/deleteClueRemark/{id}")
    public Result deleteClueRemark(@PathVariable(value = "id") Integer id) {
        return clueRemarkService.deleteClueRemarkById(id);
    }
}
