package com.work.ykserver.ykapps.web;

import com.work.ykserver.ykapps.service.DicValueService;
import com.work.ykserver.ykapps.vo.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/dicValue")
public class DicValueController {

    @Resource
    private DicValueService dicValueService;

    @GetMapping("/getDicValue/{tyCode}")
    public Result getDicValue(@PathVariable(value = "tyCode") String typeCode) {
        return dicValueService.getDicValueList(typeCode);
    }
}
