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
 * 线索状态转换器
 */
public class StateConverter implements Converter<Integer> {
    @Override
    public Integer convertToJavaData(ReadCellData<?> cellData, ExcelContentProperty contentProperty, GlobalConfiguration globalConfiguration) throws Exception {
        String cellDataStringValue = cellData.getStringValue();
        List<DicValue> dicValueList = (List<DicValue>) YkServerApplication.cacheMap.get(DicEnum.STATE.getCode());
        for (DicValue dicValue : dicValueList) {
            Integer id = dicValue.getId();
            String typeValue = dicValue.getTypeValue();
            if (cellDataStringValue.equals(typeValue)) {
                return id;
            }
        }
        return -1;
    }
}
