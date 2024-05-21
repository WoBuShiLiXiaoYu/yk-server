package com.work.ykserver.ykapps.config.converter;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.data.ReadCellData;
import com.alibaba.excel.metadata.property.ExcelContentProperty;
import com.work.ykserver.YkServerApplication;
import com.work.ykserver.ykapps.common.DicEnum;
import com.work.ykserver.ykapps.pojo.Product;

import java.util.List;

/**
 * 意向产品转换器
 */
public class IntentionProductConverter implements Converter<Integer> {
    @Override
    public Integer convertToJavaData(ReadCellData<?> cellData, ExcelContentProperty contentProperty, GlobalConfiguration globalConfiguration) throws Exception {
        String cellDataStringValue = cellData.getStringValue();
        List<Product> productList = (List<Product>) YkServerApplication.cacheMap.get(DicEnum.INTENTIONPRODUCT.getCode());
        for (Product product : productList) {
            Integer id = product.getId();
            String name = product.getName();
            if (cellDataStringValue.equals(name)) {
                return id;
            }
        }
        return -1;
    }
}
