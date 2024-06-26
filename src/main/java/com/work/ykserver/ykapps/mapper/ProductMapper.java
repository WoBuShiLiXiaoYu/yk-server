package com.work.ykserver.ykapps.mapper;

import com.work.ykserver.ykapps.pojo.Product;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
* @author 胡国海
* @description 针对表【t_product(产品表)】的数据库操作Mapper
* @createDate 2024-05-09 21:34:33
* @Entity com.work.ykserver.ykapps.pojo.Product
*/
public interface ProductMapper extends BaseMapper<Product> {

    List<Product> selectOnSalProductList();

    List<Product> selectOnSalProductNameList();
}




