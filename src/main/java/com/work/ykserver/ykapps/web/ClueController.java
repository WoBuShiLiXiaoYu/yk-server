package com.work.ykserver.ykapps.web;

import cn.hutool.core.util.ObjectUtil;
import com.work.ykserver.ykapps.common.CodeEnum;
import com.work.ykserver.ykapps.constant.RequestConstants;
import com.work.ykserver.ykapps.query.ClueQuery;
import com.work.ykserver.ykapps.service.ClueService;
import com.work.ykserver.ykapps.service.DicValueService;
import com.work.ykserver.ykapps.util.ResultUtils;
import com.work.ykserver.ykapps.vo.Result;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("/api/clue")
public class ClueController {

    @Resource
    private ClueService clueService;
    @Resource
    private DicValueService dicValueService;

    @PreAuthorize(value = "hasAnyAuthority('clue:list')")
    @GetMapping("/getClueList")
    public Result getClueList(@RequestParam(value = "currentPage") Integer currentPage) {
        if (ObjectUtil.isEmpty(currentPage)) {
            currentPage = 1;
        }
        return clueService.getClueListByPage(currentPage);
    }

    @PreAuthorize(value = "hasAnyAuthority('clue:import')")
    @PostMapping("/importExcel")
    public Result importExcel(MultipartFile file, @RequestHeader(RequestConstants.HEADER_TOKEN_NAME) String token) throws IOException {
        InputStream fileInputStream = file.getInputStream();
        return clueService.importExcel(fileInputStream, token);
    }

    @GetMapping("/checkPhone/{phone}")
    public Result checkPhone(@PathVariable(value = "phone") String phone) {
        return clueService.checkPhone(phone);
    }

    @PreAuthorize(value = "hasAnyAuthority('clue:add')")
    @PostMapping("/addClue")
    public Result addClue(ClueQuery clueQuery, @RequestHeader(value = RequestConstants.HEADER_TOKEN_NAME) String token) {
        clueQuery.setToken(token);
        return clueService.addClue(clueQuery);
    }

    @GetMapping("/getClueInfo/{id}")
    public Result getClueInfo(@PathVariable(value = "id") Integer id) {
        return clueService.getClueInfoById(id);
    }

    @PreAuthorize(value = "hasAnyAuthority('clue:edit')")
    @PutMapping("/editClue")
    public Result editClue(ClueQuery clueQuery, @RequestHeader(value = RequestConstants.HEADER_TOKEN_NAME) String token) {
        clueQuery.setToken(token);
        return clueService.editClue(clueQuery);
    }

    @PreAuthorize(value = "hasAnyAuthority('clue:view')")
    @GetMapping("/getClueDetailInfo/{id}")
    public Result getClueDetailInfo(@PathVariable(value = "id") Integer id) {
        return clueService.getClueDetailInfo(id);
    }

    @PreAuthorize(value = "hasAnyAuthority('clue:delete')")
    @DeleteMapping("/deleteClue/{id}")
    public Result deleteClue(@PathVariable(value = "id") Integer id) {
        return clueService.deleteClueById(id);
    }

    @PreAuthorize(value = "hasAnyAuthority('clue:delete')")
    @DeleteMapping("/batchDeleteClue")
    public Result batchDeleteClue(@RequestParam(value = "ids") String[] ids) {
        return clueService.batchDeleteClue(ids);
    }

}
