package com.work.ykserver.ykapps.mapper;

import com.work.ykserver.ykapps.pojo.DicValue;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Slf4j
class DicValueMapperTest {

    @Resource
    private DicValueMapper dicValueMapper;


    @Test
    public void selectDicValueListTest() {
        String typeCode = "source";
        List<DicValue> dicValueList = dicValueMapper.selectDicValueList(typeCode);
        Assertions.assertNotNull(dicValueList);
        dicValueList.forEach(dicValue -> {
            log.info("dicValue: " + dicValue);
        });
    }
}