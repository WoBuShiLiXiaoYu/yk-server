package com.work.ykserver.ykapps.mapper;

import com.work.ykserver.ykapps.pojo.Tran;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.math.BigDecimal;

/**
* @author 胡国海
* @description 针对表【t_tran(交易表)】的数据库操作Mapper
* @createDate 2024-05-09 21:34:33
* @Entity com.work.ykserver.ykapps.pojo.Tran
*/
public interface TranMapper extends BaseMapper<Tran> {

    BigDecimal selectSuccessTranAmount();

    BigDecimal selectTotalTranAmount();

    Integer selectTotalTranCount();

    Integer selectSuccessTranCount();
}




