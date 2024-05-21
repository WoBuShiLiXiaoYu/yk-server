package com.work.ykserver.ykapps.mapper;

import com.work.ykserver.ykapps.vo.DicTypeVo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class DicTypeMapperTest {

    @Resource
    private DicTypeMapper dicTypeMapper;
    @Test
    public void selectAllDicDataTest() {

        List<DicTypeVo> dicTypeVos = dicTypeMapper.selectAllDicData();
        Assertions.assertNotNull(dicTypeVos);
        dicTypeVos.forEach(dicTypeVo -> {
            log.info("dicTypeVo======>");
            System.out.println(dicTypeVo.getId());
            System.out.println(dicTypeVo.getTypeCode());
            System.out.println(dicTypeVo.getTypeName());
            System.out.println(dicTypeVo.getRemark());
            System.out.println(dicTypeVo.getDicValueList());
        });
    }
}