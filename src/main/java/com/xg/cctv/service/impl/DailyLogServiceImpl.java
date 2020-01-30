package com.xg.cctv.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xg.cctv.mybatis.po.DailyLog;
import com.xg.cctv.mybatis.mapper.DailyLogMapper;
import com.xg.cctv.service.DailyLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
/**
 * <p>
 * 西港监控部日志表 服务实现类
 * </p>
 *
 * @author lorenzo
 * @since 2020-01-30
 */
@Service
@Transactional
public class DailyLogServiceImpl extends ServiceImpl<DailyLogMapper, DailyLog> implements DailyLogService {

    @Autowired
    private DailyLogMapper dailyLogMapper;

    @Override
    public IPage<DailyLog> selectPage(Page<DailyLog> page, DailyLog dailyLog) {
        QueryWrapper<DailyLog> queryWrapper = new QueryWrapper<DailyLog>();
        getQueryWrapper(queryWrapper,dailyLog);
        return dailyLogMapper.selectPage(page,queryWrapper);
    }

    @Override
    public List<DailyLog> selectList(DailyLog dailyLog) {
        QueryWrapper<DailyLog> queryWrapper = new QueryWrapper<DailyLog>();
        getQueryWrapper(queryWrapper,dailyLog);
        return dailyLogMapper.selectList(queryWrapper);
    }

    /**
     *  公共查询条件
     * @param queryWrapper
     * @return
     */
    public QueryWrapper<DailyLog> getQueryWrapper(QueryWrapper<DailyLog> queryWrapper,DailyLog dailyLog){
        //条件拼接
    
        return queryWrapper;
    }
}
