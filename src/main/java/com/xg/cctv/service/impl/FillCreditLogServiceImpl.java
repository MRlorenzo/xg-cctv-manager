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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public IPage<FillCreditLog> selectPage(Page<FillCreditLog> page, Map<String, Object> params) {
        QueryWrapper<FillCreditLog> queryWrapper = new QueryWrapper<FillCreditLog>();
        getQueryWrapper(queryWrapper , params);
        return fillCreditLogMapper.selectPage(page,queryWrapper);
    }

    @Override
    public List<FillCreditLog> selectList(FillCreditLog fillCreditLog) {
        QueryWrapper<FillCreditLog> queryWrapper = new QueryWrapper<FillCreditLog>();
        getQueryWrapper(queryWrapper,fillCreditLog);
        return fillCreditLogMapper.selectList(queryWrapper);
    }

    @Override
    public List<FillCreditLog> selectList(Map<String, Object> params) {
        QueryWrapper<FillCreditLog> queryWrapper = new QueryWrapper<FillCreditLog>();
        getQueryWrapper(queryWrapper , params);
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

        Map<String , Object> params = new HashMap<>();

        if (fillCreditLog.getTableCode() != null){
            params.put("tableCode" , fillCreditLog.getTableCode());
        }

        if (fillCreditLog.getCoinCode() != null){
            params.put("coinCode" , fillCreditLog.getCoinCode());
        }
    
        return getQueryWrapper(queryWrapper , params);
    }

    public QueryWrapper<FillCreditLog> getQueryWrapper(QueryWrapper<FillCreditLog> queryWrapper, Map<String , Object> params){
        //条件拼接
        if (params == null){
            return queryWrapper;
        }

        if (params.get("startDate") != null){
            queryWrapper.ge(true , "create_time" , params.get("startDate"));
            // queryWrapper.apply("UNIX_TIMESTAMP(create_time) >= UNIX_TIMESTAMP('{0}')" , params.get("startDate"));
        }

        if (params.get("endDate") != null){
            queryWrapper.le(true , "create_time" , params.get("endDate"));
            // queryWrapper.apply("UNIX_TIMESTAMP(create_time) <= UNIX_TIMESTAMP('{0}')" , params.get("endDate"));
        }

        if (params.get("tableCode") != null){
            queryWrapper.like("table_code" , params.get("tableCode"));
        }

        if (params.get("coinCode") != null){
            queryWrapper.eq("coin_code" , params.get("coinCode"));
        }

        if (params.get("type") != null){
            queryWrapper.eq("type" , params.get("type"));
        }
        return queryWrapper;
    }
}
