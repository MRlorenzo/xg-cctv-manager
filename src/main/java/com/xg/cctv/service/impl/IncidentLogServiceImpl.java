package com.xg.cctv.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xg.cctv.common.dto.IncidentLogVo;
import com.xg.cctv.common.util.ShiroUtils;
import com.xg.cctv.mybatis.po.*;
import com.xg.cctv.mybatis.mapper.IncidentLogMapper;
import com.xg.cctv.service.IncidentLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xg.cctv.common.CommonEnum;
import com.xg.cctv.service.StaffLogService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

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

    @Autowired
    private StaffLogService staffLogService;

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
    public IPage<IncidentLogVo> selectAllPage(Page<IncidentLogVo> page, Map<String, Object> params) {
        QueryWrapper<IncidentLogVo> queryWrapper = new QueryWrapper<>();
        getVoQueryWrapper(queryWrapper , params);
        return incidentLogMapper.selectAllPage(page , queryWrapper);
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
    public boolean saveOrUpdate(IncidentLog entity) {
        boolean isUpdate = entity.getId() != null;
        if (!isUpdate){
            entity.setCreateUid(ShiroUtils.getUserId());
            entity.setCreateTime(new Date());
        }
        boolean b = super.saveOrUpdate(entity);
        List<SysStaff> staffs = entity.getStaffs();

        // 更新账号与角色关联表
        if (isUpdate){
            // 删除原关联
            Map<String , Object> param = new HashMap<>();
            param.put("log_id" , entity.getId());
            param.put("type" , CommonEnum.PositionLogType.INCIDENT.getValue());
            staffLogService.removeByMap(param);
        }

        if (staffs != null && !staffs.isEmpty()){
            // 保存映射关系
            staffLogService.saveBatch(mapPositionLog(entity.getId() , staffs));
        }

        return b;
    }

    private List<StaffLog> mapPositionLog(Long logId , List<SysStaff> staffs){
        List<StaffLog> userPositions = new ArrayList<>();
        for (SysStaff sysStaff : staffs) {
            StaffLog sl = new StaffLog();
            sl.setLogId(logId);
            sl.setStaffId(sysStaff.getStaffId());
            sl.setType(CommonEnum.PositionLogType.INCIDENT.getValue());
            userPositions.add(sl);
        }
        return userPositions;
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

        if (params.get("staffId") != null && StringUtils.isNotEmpty(params.get("staffId").toString())){
            queryWrapper.inSql("id" ,
                    "select log_id from staff_log where type = 1 and staff_id="
                    +params.get("staffId").toString()
            );
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
        queryWrapper.orderByDesc("id");
        return queryWrapper;
    }
}
