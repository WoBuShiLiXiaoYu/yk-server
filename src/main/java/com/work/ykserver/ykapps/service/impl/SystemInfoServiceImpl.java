package com.work.ykserver.ykapps.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.work.ykserver.ykapps.pojo.SystemInfo;
import com.work.ykserver.ykapps.service.SystemInfoService;
import com.work.ykserver.ykapps.mapper.SystemInfoMapper;
import org.springframework.stereotype.Service;

/**
* @author 胡国海
* @description 针对表【t_system_info(系统信息表)】的数据库操作Service实现
* @createDate 2024-05-09 21:34:33
*/
@Service
public class SystemInfoServiceImpl extends ServiceImpl<SystemInfoMapper, SystemInfo>
    implements SystemInfoService{

}




