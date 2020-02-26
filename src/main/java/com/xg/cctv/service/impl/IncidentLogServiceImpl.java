package com.xg.cctv.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xg.cctv.common.dto.IncidentLogVo;
import com.xg.cctv.mybatis.po.IncidentLog;
import com.xg.cctv.mybatis.mapper.IncidentLogMapper;
import com.xg.cctv.service.IncidentLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 西港监控部事件报告表 服务实现类
 * </p>
 *
 * @author lorenzo
 * @since 2020-02-05
 */
@Service
@Transactional
public class IncidentLogServiceImpl extends ServiceImpl<IncidentLogMapper, IncidentLog> implements IncidentLogService {

    @Autowired
    private IncidentLogMapper incidentLogMapper;

    @Override
    public IPage<IncidentLogVo> selectVoPage(Page<IncidentLogVo> page, Map<String, Object> params) {
        QueryWrapper<IncidentLogVo> queryWrapper = new QueryWrapper<>();
        getVoQueryWrapper(queryWrapper , params);
        return incidentLogMapper.selectVoPage(page , queryWrapper);
    }

    @Override
    public List<IncidentLogVo> selectVoList(Map<String, Object> params) {
        QueryWrapper<IncidentLogVo> queryWrapper = new QueryWrapper<>();
        getVoQueryWrapper(queryWrapper , params);
        return incidentLogMapper.selectVoList(queryWrapper);
    }

    @Override
    public QueryWrapper<IncidentLogVo> getVoQueryWrapper(QueryWrapper<IncidentLogVo> queryWrapper, Map<String, Object> params) {
        return getWrapper(queryWrapper , params);
    }

    @Override
    public IPage<IncidentLog> selectPage(Page<IncidentLog> page, IncidentLog incidentLog) {
        QueryWrapper<IncidentLog> queryWrapper = new QueryWrapper<IncidentLog>();
        getQueryWrapper(queryWrapper,incidentLog);
        return incidentLogMapper.selectPage(page,queryWrapper);
    }

    @Override
    public IPage<IncidentLog> selectPage(Page<IncidentLog> page, Map<String, Object> params) {
        QueryWrapper<IncidentLog> queryWrapper = new QueryWrapper<IncidentLog>();
        getQueryWrapper(queryWrapper , params);
        return incidentLogMapper.selectPage( page, queryWrapper);
    }

    @Override
    public List<IncidentLog> selectList(IncidentLog incidentLog) {
        QueryWrapper<IncidentLog> queryWrapper = new QueryWrapper<IncidentLog>();
        getQueryWrapper(queryWrapper,incidentLog);
        return incidentLogMapper.selectList(queryWrapper);
    }

    @Override
    public List<IncidentLog> selectList(Map<String, Object> params) {
        QueryWrapper<IncidentLog> queryWrapper = new QueryWrapper<IncidentLog>();
        getQueryWrapper(queryWrapper, params);
        return incidentLogMapper.selectList(queryWrapper);
    }

    /**
     *  公共查询条件
     * @param queryWrapper
     * @return
     */
    public QueryWrapper<IncidentLog> getQueryWrapper(QueryWrapper<IncidentLog> queryWrapper,IncidentLog incidentLog){
        //条件拼接
        if (incidentLog == null){
            return queryWrapper;
        }

        Map<String , Object> params = new HashMap<>();

        if (incidentLog.getTableCode() != null){
            params.put("tableCode" , incidentLog.getTableCode());
        }

        if (incidentLog.getCode() != null){
            params.put("code" , incidentLog.getCode());
        }

        if (incidentLog.getCoinCode() != null){
            params.put("coinCode" , incidentLog.getCoinCode());
        }

        if (incidentLog.getInvolveUid() != null){
            params.put("involveUid" , incidentLog.getInvolveUid());
        }

        if (incidentLog.getDepartmentId() != null){
            params.put("departmentId" , incidentLog.getDepartmentId());
        }

        if (incidentLog.getMonitor() != null){
            params.put("monitor" , incidentLog.getMonitor());
        }

        return getWrapper(queryWrapper , params);
    }

    public QueryWrapper<IncidentLog> getQueryWrapper(QueryWrapper<IncidentLog> queryWrapper, Map<String , Object> params){
        return getWrapper(queryWrapper , params);
    }

    private QueryWrapper getWrapper(QueryWrapper queryWrapper , Map<String , Object> params){
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

        if (params.get("code") != null){
            queryWrapper.like("code" , params.get("code"));
        }

        if (params.get("coinCode") != null){
            queryWrapper.eq("coin_code" , params.get("coinCode"));
        }

        if (params.get("involveUid") != null){
            queryWrapper.eq("involve_uid", params.get("involveUid"));
        }

        if (params.get("departmentId") != null){
            queryWrapper.eq("i.department_id" , params.get("departmentId"));
        }

        if (params.get("departmentName") != null && StringUtils.isNotEmpty(params.get("departmentName").toString())){
            queryWrapper.inSql("i.department_id" ,
                    "SELECT department_id FROM sys_department WHERE department_code LIKE '%"
                            + params.get("departmentName").toString()
                            +"%'");
        }

        if (params.get("monitor") != null){
            queryWrapper.eq("monitor" , params.get("monitor"));
        }
        return queryWrapper;
    }
}
