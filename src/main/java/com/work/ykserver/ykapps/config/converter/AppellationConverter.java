package com.work.ykserver.ykapps.config.converter;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.data.ReadCellData;
import com.alibaba.excel.metadata.property.ExcelContentProperty;
import com.work.ykserver.YkServerApplication;
import com.work.ykserver.ykapps.common.DicEnum;
import com.work.ykserver.ykapps.pojo.DicValue;

import java.util.List;

/**
 * 称呼转换器
 *
 * Excel "教授" ---> java 5
 * Excel "博士" ---> java 11
 * Excel "先生" ---> java 18
 * Excel "夫人" ---> java 20
 * Excel "先生" ---> java 18
 */
public class AppellationConverter implements Converter<Integer> {
    /**
     * 将 Excel 中的数据转换为 java 中的数据
     * @param cellData
     * @param contentProperty
     * @param globalConfiguration
     * @return
     * @throws Exception
     */
    @Override
    public Integer convertToJavaData(ReadCellData<?> cellData, ExcelContentProperty contentProperty, GlobalConfiguration globalConfiguration) throws Exception {
        // 获取注解的值
        String cellAppellationName = cellData.getStringValue();
        // 从 map 缓存中获取 typeValue
        List<DicValue> dicValueList = (List<DicValue>) YkServerApplication.cacheMap.get(DicEnum.APPELLATION.getCode());
        for (DicValue dicValue : dicValueList) {
            Integer id = dicValue.getId();
            String typeValue = dicValue.getTypeValue();
            if (cellAppellationName.equals(typeValue)) {
                return id;
            }
        }
        return -1;
    }
}
