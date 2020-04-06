package com.xg.cctv.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xg.cctv.mybatis.po.StaffLog;
import com.xg.cctv.mybatis.mapper.StaffLogMapper;
import com.xg.cctv.service.StaffLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;
/**
 * <p>
 * 职位和事件、高额日志对应关系 服务实现类
 * </p>
 *
 * @author lorenzo
 * @since 2020-04-05
 */
@Service
@Transactional
public class StaffLogServiceImpl extends ServiceImpl<StaffLogMapper, StaffLog> implements StaffLogService {

    @Autowired
    private StaffLogMapper staffLogMapper;

    @Override
    public IPage<StaffLog> selectPage(Page<StaffLog> page, StaffLog staffLog) {
        QueryWrapper<StaffLog> queryWrapper = new QueryWrapper<StaffLog>();
        getQueryWrapper(queryWrapper,staffLog);
        return staffLogMapper.selectPage(page,queryWrapper);
    }

    @Override
    public IPage<StaffLog> selectPage(Page<StaffLog> page, Map<String, Object> params) {
        QueryWrapper<StaffLog> queryWrapper = new QueryWrapper<StaffLog>();
        getQueryWrapper(queryWrapper , params);
        return staffLogMapper.selectPage(page , queryWrapper);
    }

    @Override
    public List<StaffLog> selectList(StaffLog staffLog) {
        QueryWrapper<StaffLog> queryWrapper = new QueryWrapper<StaffLog>();
        getQueryWrapper(queryWrapper , staffLog);
        return staffLogMapper.selectList(queryWrapper);
    }

    @Override
    public List<StaffLog> selectList(Map<String , Object> params) {
        QueryWrapper<StaffLog> queryWrapper = new QueryWrapper<StaffLog>();
        getQueryWrapper(queryWrapper , params);
        return staffLogMapper.selectList(queryWrapper);
    }

    /**
     *  公共查询条件
     * @param queryWrapper
     * @return
     */
    public QueryWrapper<StaffLog> getQueryWrapper(QueryWrapper<StaffLog> queryWrapper,StaffLog staffLog){
        //条件拼接
    
        return queryWrapper;
    }

    public QueryWrapper<StaffLog> getQueryWrapper(QueryWrapper<StaffLog> queryWrapper,Map<String , Object> params){
    //条件拼接
    return queryWrapper;
    }
}
