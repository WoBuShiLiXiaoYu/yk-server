package com.work.ykserver.ykapps.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.work.ykserver.ykapps.pojo.Product;
import com.work.ykserver.ykapps.service.ProductService;
import com.work.ykserver.ykapps.mapper.ProductMapper;
import org.springframework.stereotype.Service;

/**
* @author 胡国海
* @description 针对表【t_product(产品表)】的数据库操作Service实现
* @createDate 2024-05-09 21:34:33
*/
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product>
    implements ProductService{

}




