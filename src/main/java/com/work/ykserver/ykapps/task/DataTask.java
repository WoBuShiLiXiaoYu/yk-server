package com.work.ykserver.ykapps.task;

import com.work.ykserver.YkServerApplication;
import com.work.ykserver.ykapps.common.DicEnum;
import com.work.ykserver.ykapps.pojo.DicType;
import com.work.ykserver.ykapps.pojo.DicValue;
import com.work.ykserver.ykapps.pojo.Product;
import com.work.ykserver.ykapps.service.DicTypeService;
import com.work.ykserver.ykapps.service.ProductService;
import com.work.ykserver.ykapps.vo.DicTypeVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class DataTask {

    @Resource
    private DicTypeService dicTypeService;
    @Resource
    private ProductService productService;

    @Scheduled(fixedDelayString = "${project.task.delay}", initialDelay = 1000L, zone = "Asia/Shanghai", timeUnit = TimeUnit.MILLISECONDS)
    public void getDataTask() {
        log.info("定时任务执行");
        //获取字典所有字段和字段值
        List<DicTypeVo> typeList = dicTypeService.loadAllDicData();
        typeList.forEach(dicTypeVo -> {
            String typeCode = dicTypeVo.getTypeCode();
            List<DicValue> dicValueList = dicTypeVo.getDicValueList();
            // 缓存进 map 中，方便转换器使用， map<字典码, 字典值>
            YkServerApplication.cacheMap.put(typeCode, dicValueList);
        });
        // 获取所以在售产品
        List<Product> productList = productService.getOnSaleProductList();
        // 缓存 map<产品名, 产品列表>
        YkServerApplication.cacheMap.put(DicEnum.INTENTIONPRODUCT.getCode(), productList);
    }
}
