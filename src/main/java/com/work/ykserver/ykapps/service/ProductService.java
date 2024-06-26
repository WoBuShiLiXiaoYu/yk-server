package com.work.ykserver.ykapps.service;

import com.work.ykserver.ykapps.pojo.Product;
import com.baomidou.mybatisplus.extension.service.IService;
import com.work.ykserver.ykapps.vo.Result;

import java.util.List;

/**
* @author 胡国海
* @description 针对表【t_product(产品表)】的数据库操作Service
* @createDate 2024-05-09 21:34:33
*/
public interface ProductService extends IService<Product> {

    List<Product> getOnSaleProductList();


    Result getOnSaleProductNameList();
}
