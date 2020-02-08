package com.xg.cctv.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xg.cctv.mybatis.po.HighAction;
import com.xg.cctv.mybatis.mapper.HighActionMapper;
import com.xg.cctv.service.HighActionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 西港监控部高额活动表 服务实现类
 * </p>
 *
 * @author lorenzo
 * @since 2020-02-05
 */
@Service
@Transactional
public class HighActionServiceImpl extends ServiceImpl<HighActionMapper, HighAction> implements HighActionService {

    @Autowired
    private HighActionMapper highActionMapper;

    @Override
    public IPage<HighAction> selectPage(Page<HighAction> page, HighAction highAction) {
        QueryWrapper<HighAction> queryWrapper = new QueryWrapper<HighAction>();
        getQueryWrapper(queryWrapper,highAction);
        return highActionMapper.selectPage(page,queryWrapper);
    }

    @Override
    public IPage<HighAction> selectPage(Page<HighAction> page, Map<String, Object> params) {
        QueryWrapper<HighAction> queryWrapper = new QueryWrapper<HighAction>();
        getMapQueryWrapper(queryWrapper , params);
        return highActionMapper.selectPage(page,queryWrapper);
    }

    @Override
    public List<HighAction> selectList(HighAction highAction) {
        QueryWrapper<HighAction> queryWrapper = new QueryWrapper<HighAction>();
        getQueryWrapper(queryWrapper,highAction);
        return highActionMapper.selectList(queryWrapper);
    }

    /**
     *  公共查询条件
     * @param queryWrapper
     * @return
     */
    public QueryWrapper<HighAction> getQueryWrapper(QueryWrapper<HighAction> queryWrapper,HighAction highAction){
        //条件拼接
        if (highAction == null){
            return queryWrapper;
        }

        if (highAction.getTableCode() != null){
            queryWrapper.like("table_code" , highAction.getTableCode());
        }

        if (highAction.getCoinCode() != null){
            queryWrapper.eq("coin_code" , highAction.getCoinCode());
        }

        if (highAction.getInvolveUid() != null){
            queryWrapper.eq("involve_uid" , highAction.getInvolveUid());
        }

        if (highAction.getMonitor() != null){
            queryWrapper.eq("monitor" , highAction.getMonitor());
        }
    
        return queryWrapper;
    }

    public QueryWrapper<HighAction> getMapQueryWrapper(QueryWrapper<HighAction> queryWrapper,Map<String , Object> params){
        //条件拼接
        return queryWrapper;
    }
}
