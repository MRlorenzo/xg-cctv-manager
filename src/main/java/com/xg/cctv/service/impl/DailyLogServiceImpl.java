package com.xg.cctv.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xg.cctv.common.dto.DailyLogVo;
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
        getVoQueryWrapper(queryWrapper , params);
        return dailyLogMapper.selectVoPage(page , queryWrapper);
    }

    @Override
    public List<DailyLogVo> selectVoList(Map<String, Object> params) {
        QueryWrapper<DailyLogVo> queryWrapper = new QueryWrapper<>();
        getVoQueryWrapper(queryWrapper , params);
        return dailyLogMapper.selectVoList(queryWrapper);
    }

    @Override
    public QueryWrapper<DailyLogVo> getVoQueryWrapper(QueryWrapper<DailyLogVo> queryWrapper, Map<String, Object> params) {
        return getWrapper(queryWrapper , params);
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
        getQueryWrapper(queryWrapper , params);
        return dailyLogMapper.selectPage(page,queryWrapper);
    }

    @Override
    public List<DailyLog> selectList(DailyLog dailyLog) {
        QueryWrapper<DailyLog> queryWrapper = new QueryWrapper<DailyLog>();
        getQueryWrapper(queryWrapper,dailyLog);
        return dailyLogMapper.selectList(queryWrapper);
    }

    @Override
    public List<DailyLog> selectList(Map<String, Object> params) {
        QueryWrapper<DailyLog> queryWrapper = new QueryWrapper<DailyLog>();
        getQueryWrapper(queryWrapper , params);
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

    public QueryWrapper<DailyLog> getQueryWrapper(QueryWrapper<DailyLog> queryWrapper, Map<String , Object> params){
        return getWrapper(queryWrapper , params);
    }

    private QueryWrapper getWrapper(QueryWrapper queryWrapper , Map<String , Object> params){
        //条件拼接
        if (params == null){
            return queryWrapper;
        }

        if (params.get("tableCode") != null){
            queryWrapper.like("table_code" , params.get("tableCode"));
        }

        if (params.get("subject") != null){
            queryWrapper.eq("subject" , params.get("subject"));
        }

        if (params.get("departmentId") != null){
            queryWrapper.eq("department_id" , params.get("departmentId"));
        }

        if (params.get("monitor") != null){
            queryWrapper.eq("monitor" , params.get("monitor"));
        }

        if (params.get("startDate") != null){
            queryWrapper.apply("UNIX_TIMESTAMP(create_time) >= UNIX_TIMESTAMP('{0}')" , params.get("startDate"));
        }

        if (params.get("endDate") != null){
            queryWrapper.apply("UNIX_TIMESTAMP(create_time) <= UNIX_TIMESTAMP('{0}')" , params.get("endDate"));
        }

        return queryWrapper;
    }
}
