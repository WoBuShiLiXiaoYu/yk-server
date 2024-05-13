package com.work.ykserver.ykapps.service;

import com.work.ykserver.ykapps.bo.Page;
import com.work.ykserver.ykapps.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.work.ykserver.ykapps.query.UserQuery;
import com.work.ykserver.ykapps.vo.Result;

import java.util.List;

/**
* @author 胡国海
* @description 针对表【t_user(用户表)】的数据库操作Service
* @createDate 2024-05-09 21:34:33
*/
public interface UserService  extends IService<User>{

    Page getUserListByPage(Integer currentPage);

    User getUserDetailInfoById(Integer id);

    Result userSave(UserQuery userQuery);

}
