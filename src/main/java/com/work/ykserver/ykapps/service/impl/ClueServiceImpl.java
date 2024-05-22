package com.work.ykserver.ykapps.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.work.ykserver.ykapps.bo.Page;
import com.work.ykserver.ykapps.common.CodeEnum;
import com.work.ykserver.ykapps.config.listener.UploadDataListener;
import com.work.ykserver.ykapps.pojo.Clue;
import com.work.ykserver.ykapps.pojo.User;
import com.work.ykserver.ykapps.query.ClueQuery;
import com.work.ykserver.ykapps.service.ClueService;
import com.work.ykserver.ykapps.mapper.ClueMapper;
import com.work.ykserver.ykapps.util.JWTUtils;
import com.work.ykserver.ykapps.util.PageUtils;
import com.work.ykserver.ykapps.util.RedisUtils;
import com.work.ykserver.ykapps.util.ResultUtils;
import com.work.ykserver.ykapps.vo.ClueVO;
import com.work.ykserver.ykapps.vo.Result;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

/**
* @author 胡国海
* @description 针对表【t_clue(线索表)】的数据库操作Service实现
* @createDate 2024-05-09 21:34:33
*/
@Service
public class ClueServiceImpl extends ServiceImpl<ClueMapper, Clue>
    implements ClueService{

    @Resource
    private ClueMapper clueMapper;

    @Override
    public Result getClueListByPage(Integer currentPage) {
        Page page = PageUtils.getPage(currentPage);
        ClueQuery query = new ClueQuery();
        // 获取线索列表
        List<ClueVO> clueList = clueMapper.selectClueListByPage(query, page);
        // 获取总记录条数
        int total = clueMapper.getCountByPage(query);

        page = PageUtils.pageSetting(page, clueList, total);

        return ResultUtils.success(page);
    }

    @Override
    public Result importExcel(InputStream fileInputStream, String token) {
        int before = clueMapper.selectCountAll();
        EasyExcel.read(fileInputStream, Clue.class, new UploadDataListener(clueMapper, token)).sheet().doRead();
        int after = clueMapper.selectCountAll();
        if (after > before) {
            return ResultUtils.success(CodeEnum.OK);
        }
        return ResultUtils.fail(CodeEnum.FAIL);
    }

    @Override
    public Result checkPhone(String phone) {
        int result = clueMapper.selectCountByPhone(phone);
        if (result > 0) {
            return ResultUtils.fail(CodeEnum.FAIL);
        }
        return ResultUtils.success(CodeEnum.OK);
    }

    @Override
    public Result addClue(ClueQuery clueQuery) {
        // 解析 token
        User loginUser = JWTUtils.parseUserFromJWT(clueQuery.getToken());
        Clue clue = new Clue();
        BeanUtil.copyProperties(clueQuery, clue);
        clue.setCreateBy(loginUser.getId());
        clue.setCreateTime(new Date());
        int result = clueMapper.saveClue(clue);
        if (result <= 0) {
            return ResultUtils.fail(CodeEnum.SAVE_CLUE_FAIL);
        }
        return ResultUtils.success(CodeEnum.OK);
    }

    @Override
    public Result getClueInfoById(Integer id) {
        Clue clue = clueMapper.selectById(id);
        if (ObjectUtil.isEmpty(clue)) {
            return ResultUtils.fail(CodeEnum.FAIL);
        }
        return ResultUtils.success(clue);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Result editClue(ClueQuery clueQuery) {
        User loginUser = JWTUtils.parseUserFromJWT(clueQuery.getToken());
        Clue clue = new Clue();
        BeanUtil.copyProperties(clueQuery, clue);
        clue.setEditBy(loginUser.getId());
        clue.setEditTime(new Date());
        int result = clueMapper.updateClue(clue);
        if (result != 1) {
            return ResultUtils.fail(CodeEnum.EDIT_CLUE_FAIL);
        }
        return ResultUtils.success(CodeEnum.OK);
    }

    @Override
    public Result getClueDetailInfo(Integer id) {
        ClueVO clueVO = clueMapper.selectClueDetailInfoById(id);
        return ResultUtils.success(clueVO);
    }
}




