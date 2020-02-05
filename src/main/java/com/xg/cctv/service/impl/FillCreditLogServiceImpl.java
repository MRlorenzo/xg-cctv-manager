package com.xg.cctv.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xg.cctv.mybatis.po.FillCreditLog;
import com.xg.cctv.mybatis.mapper.FillCreditLogMapper;
import com.xg.cctv.service.FillCreditLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
/**
 * <p>
 * 西港监控部日常加彩/缴码表 服务实现类
 * </p>
 *
 * @author lorenzo
 * @since 2020-02-01
 */
@Service
@Transactional
public class FillCreditLogServiceImpl extends ServiceImpl<FillCreditLogMapper, FillCreditLog> implements FillCreditLogService {

    @Autowired
    private FillCreditLogMapper fillCreditLogMapper;

    @Override
    public IPage<FillCreditLog> selectPage(Page<FillCreditLog> page, FillCreditLog fillCreditLog) {
        QueryWrapper<FillCreditLog> queryWrapper = new QueryWrapper<FillCreditLog>();
        getQueryWrapper(queryWrapper,fillCreditLog);
        return fillCreditLogMapper.selectPage(page,queryWrapper);
    }

    @Override
    public List<FillCreditLog> selectList(FillCreditLog fillCreditLog) {
        QueryWrapper<FillCreditLog> queryWrapper = new QueryWrapper<FillCreditLog>();
        getQueryWrapper(queryWrapper,fillCreditLog);
        return fillCreditLogMapper.selectList(queryWrapper);
    }

    /**
     *  公共查询条件
     * @param queryWrapper
     * @return
     */
    public QueryWrapper<FillCreditLog> getQueryWrapper(QueryWrapper<FillCreditLog> queryWrapper,FillCreditLog fillCreditLog){
        //条件拼接
        if (fillCreditLog == null){
            return queryWrapper;
        }

        if (fillCreditLog.getTableCode() != null){
            queryWrapper.like("table_code" , fillCreditLog.getTableCode());
        }

        if (fillCreditLog.getCoinCode() != null){
            queryWrapper.eq("coin_code" , fillCreditLog.getCoinCode());
        }
    
        return queryWrapper;
    }
}
