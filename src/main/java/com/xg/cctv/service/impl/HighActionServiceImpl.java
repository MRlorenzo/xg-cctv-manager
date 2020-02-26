package com.xg.cctv.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xg.cctv.common.dto.HighActionVo;
import com.xg.cctv.mybatis.po.HighAction;
import com.xg.cctv.mybatis.mapper.HighActionMapper;
import com.xg.cctv.service.HighActionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
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
        getQueryWrapper(queryWrapper , params);
        return highActionMapper.selectPage(page,queryWrapper);
    }

    @Override
    public List<HighAction> selectList(HighAction highAction) {
        QueryWrapper<HighAction> queryWrapper = new QueryWrapper<HighAction>();
        getQueryWrapper(queryWrapper,highAction);
        return highActionMapper.selectList(queryWrapper);
    }

    @Override
    public List<HighAction> selectList(Map<String, Object> params) {
        QueryWrapper<HighAction> queryWrapper = new QueryWrapper<HighAction>();
        getQueryWrapper(queryWrapper , params);
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

        Map<String , Object> params = new HashMap<>();

        if (highAction.getTableCode() != null){
            params.put("tableCode" , highAction.getTableCode());
        }

        if (highAction.getCoinCode() != null){
            params.put("coinCode" , highAction.getCoinCode());
        }

        if (highAction.getInvolveUid() != null){
            params.put("involveUid" , highAction.getInvolveUid());
        }

        if (highAction.getMonitor() != null){
            params.put("monitor" , highAction.getMonitor());
        }
    
        return getWrapper(queryWrapper , params);
    }

    public QueryWrapper<HighAction> getQueryWrapper(QueryWrapper<HighAction> queryWrapper, Map<String , Object> params){
        return getWrapper(queryWrapper , params);
    }

    private QueryWrapper getWrapper(QueryWrapper queryWrapper , Map<String , Object> params){
        //条件拼接
        if (params == null){
            return queryWrapper;
        }

        if (params.get("startDate") != null){
            queryWrapper.apply("UNIX_TIMESTAMP(create_time) >= UNIX_TIMESTAMP('{0}')" , params.get("startDate"));
        }

        if (params.get("endDate") != null){
            queryWrapper.apply("UNIX_TIMESTAMP(create_time) <= UNIX_TIMESTAMP('{0}')" , params.get("endDate"));
        }

        if (params.get("tableCode") != null){
            queryWrapper.like("table_code" , params.get("tableCode"));
        }

        if (params.get("coinCode") != null){
            queryWrapper.eq("coin_code" , params.get("coinCode"));
        }

        if (params.get("involveUid") != null){
            queryWrapper.eq("involve_uid" , params.get("involveUid"));
        }

        if (params.get("monitor") != null){
            queryWrapper.eq("monitor" , params.get("monitor"));
        }
        return queryWrapper;
    }

    @Override
    public IPage<HighActionVo> selectVoPage(Page<HighActionVo> page, Map<String, Object> params) {
        QueryWrapper<HighActionVo> queryWrapper = new QueryWrapper<HighActionVo>();
        getVoQueryWrapper(queryWrapper , params);
        return highActionMapper.selectVoPage(page,queryWrapper);
    }

    @Override
    public List<HighActionVo> selectVoList(Map<String, Object> params) {
        QueryWrapper<HighActionVo> queryWrapper = new QueryWrapper<HighActionVo>();
        getVoQueryWrapper(queryWrapper , params);
        return highActionMapper.selectVoList(queryWrapper);
    }

    @Override
    public QueryWrapper<HighActionVo> getVoQueryWrapper(QueryWrapper<HighActionVo> queryWrapper, Map<String, Object> params) {
        return getWrapper(queryWrapper , params);
    }
}
