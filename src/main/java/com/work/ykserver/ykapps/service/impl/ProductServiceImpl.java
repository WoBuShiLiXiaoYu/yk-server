package com.work.ykserver.ykapps.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.work.ykserver.ykapps.constant.RedisConstants;
import com.work.ykserver.ykapps.manager.RedisManager;
import com.work.ykserver.ykapps.pojo.Activity;
import com.work.ykserver.ykapps.pojo.Product;
import com.work.ykserver.ykapps.service.ProductService;
import com.work.ykserver.ykapps.mapper.ProductMapper;
import com.work.ykserver.ykapps.util.CacheUtils;
import com.work.ykserver.ykapps.util.RedisUtils;
import com.work.ykserver.ykapps.util.ResultUtils;
import com.work.ykserver.ykapps.vo.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
* @author 胡国海
* @description 针对表【t_product(产品表)】的数据库操作Service实现
* @createDate 2024-05-09 21:34:33
*/
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product>
    implements ProductService{

    @Resource
    private ProductMapper productMapper;
    @Resource
    private RedisManager redisManager;

    @Override
    public List<Product> getOnSaleProductList() {
        return productMapper.selectOnSalProductList();
    }

    @Override
    public Result getOnSaleProductNameList() {
        List<Product> productList = CacheUtils.getCacheData(() -> {
                    // 为缓存生产者提供数据
                    return redisManager.getValue(RedisConstants.REDIS_CACHE_PRODUCT_KEY, Product.class);
                },
                () -> {
                    // 为数据库生产者提供数据
                    return productMapper.selectOnSalProductNameList();
                },
                (t) -> {
                    // 为消费者提供数据
                    redisManager.setValue(RedisConstants.REDIS_CACHE_PRODUCT_KEY, t);
                });

        return ResultUtils.success(productList);
    }
}




