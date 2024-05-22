package com.work.ykserver.ykapps.web;

import com.work.ykserver.ykapps.service.ProductService;
import com.work.ykserver.ykapps.vo.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Resource
    private ProductService productService;

    @GetMapping("/getProductNameList")
    public Result getProductNameList() {
        return productService.getOnSaleProductNameList();
    }
}
