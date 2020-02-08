package com.xg.cctv.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xg.cctv.common.dto.DailyLogVo;
import com.xg.cctv.common.dto.VoService;
import com.xg.cctv.mybatis.po.DailyLog;
import com.xg.cctv.mybatis.mapper.DailyLogMapper;
import com.xg.cctv.service.DailyLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 西港监控部日志表 服务实现类
 * </p>
 *
 * @author lorenzo
 * @since 2020-02-05
 */
@Service
@Transactional
public class DailyLogServiceImpl extends ServiceImpl<DailyLogMapper, DailyLog> implements DailyLogService {

    @Autowired
    private DailyLogMapper dailyLogMapper;

    @Override
    public IPage<DailyLogVo> selectVoPage(Page<DailyLogVo> page, Map<String, Object> params) {
        QueryWrapper<DailyLogVo> queryWrapper = new QueryWrapper<>();
        getQueryWrapper(queryWrapper , params);
        return dailyLogMapper.selectVoPage(page , queryWrapper);
    }

    @Override
    public QueryWrapper<DailyLogVo> getQueryWrapper(QueryWrapper<DailyLogVo> queryWrapper, Map<String, Object> params) {
        return queryWrapper;
    }

    @Override
    public IPage<DailyLog> selectPage(Page<DailyLog> page, DailyLog dailyLog) {
        QueryWrapper<DailyLog> queryWrapper = new QueryWrapper<DailyLog>();
        getQueryWrapper(queryWrapper,dailyLog);
        return dailyLogMapper.selectPage(page,queryWrapper);
    }

    @Override
    public IPage<DailyLog> selectPage(Page<DailyLog> page, Map<String, Object> params) {
        QueryWrapper<DailyLog> queryWrapper = new QueryWrapper<DailyLog>();
        getMapQueryWrapper(queryWrapper , params);
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
        if (dailyLog == null){
            return queryWrapper;
        }

        if (dailyLog.getTableCode() != null){
            queryWrapper.like("table_code" , dailyLog.getTableCode());
        }

        if (dailyLog.getSubject() != null){
            queryWrapper.eq("subject" , dailyLog.getSubject());
        }

        if (dailyLog.getDepartmentId() != null){
            queryWrapper.eq("department_id" , dailyLog.getDepartmentId());
        }

        if (dailyLog.getMonitor() != null){
            queryWrapper.eq("monitor" , dailyLog.getMonitor());
        }
    
        return queryWrapper;
    }

    public QueryWrapper<DailyLog> getMapQueryWrapper(QueryWrapper<DailyLog> queryWrapper,Map<String , Object> params){
        //条件拼接
        return queryWrapper;
    }
}
