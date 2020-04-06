package com.xg.cctv.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xg.cctv.mybatis.po.SysPosition;
import com.xg.cctv.mybatis.mapper.SysPositionMapper;
import com.xg.cctv.service.SysPositionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;
/**
 * <p>
 * 职位表 服务实现类
 * </p>
 *
 * @author lorenzo
 * @since 2020-04-04
 */
@Service
@Transactional
public class SysPositionServiceImpl extends ServiceImpl<SysPositionMapper, SysPosition> implements SysPositionService {

    @Autowired
    private SysPositionMapper sysPositionMapper;

    @Override
    public IPage<SysPosition> selectPage(Page<SysPosition> page, SysPosition sysPosition) {
        QueryWrapper<SysPosition> queryWrapper = new QueryWrapper<SysPosition>();
        getQueryWrapper(queryWrapper,sysPosition);
        return sysPositionMapper.selectPage(page,queryWrapper);
    }

    @Override
    public IPage<SysPosition> selectPage(Page<SysPosition> page, Map<String, Object> params) {
        QueryWrapper<SysPosition> queryWrapper = new QueryWrapper<SysPosition>();
        getQueryWrapper(queryWrapper , params);
        return sysPositionMapper.selectPage(page , queryWrapper);
    }

    @Override
    public List<SysPosition> selectList(SysPosition sysPosition) {
        QueryWrapper<SysPosition> queryWrapper = new QueryWrapper<SysPosition>();
        getQueryWrapper(queryWrapper , sysPosition);
        return sysPositionMapper.selectList(queryWrapper);
    }

    /**
     *  公共查询条件
     * @param queryWrapper
     * @return
     */
    public QueryWrapper<SysPosition> getQueryWrapper(QueryWrapper<SysPosition> queryWrapper,SysPosition sysPosition){
        //条件拼接
    
        return queryWrapper;
    }

    public QueryWrapper<SysPosition> getQueryWrapper(QueryWrapper<SysPosition> queryWrapper,Map<String , Object> params){
    //条件拼接
    return queryWrapper;
    }
}
