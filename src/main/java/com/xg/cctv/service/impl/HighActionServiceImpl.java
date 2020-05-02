package com.xg.cctv.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xg.cctv.common.CommonEnum;
import com.xg.cctv.common.dto.HighActionVo;
import com.xg.cctv.common.util.ShiroUtils;
import com.xg.cctv.mybatis.po.HighAction;
import com.xg.cctv.mybatis.mapper.HighActionMapper;
import com.xg.cctv.mybatis.po.StaffLog;
import com.xg.cctv.mybatis.po.SysStaff;
import com.xg.cctv.service.HighActionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xg.cctv.service.StaffLogService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

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

    @Autowired
    private StaffLogService staffLogService;

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

    @Override
    public boolean saveOrUpdate(HighAction entity) {
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
            param.put("type" , CommonEnum.PositionLogType.HIGH.getValue());
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
            sl.setType(CommonEnum.PositionLogType.HIGH.getValue());
            userPositions.add(sl);
        }
        return userPositions;
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


//        if (highAction.getInvolveUid() != null){
//            params.put("involveUid" , highAction.getInvolveUid());
//        }

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

        if (params.get("involveUid") != null){
            queryWrapper.eq("involve_uid" , params.get("involveUid"));
        }

        if (params.get("monitor") != null){
            queryWrapper.eq("monitor" , params.get("monitor"));
        }
        if (params.get("staffId") != null && StringUtils.isNotEmpty(params.get("staffId").toString())){
            queryWrapper.inSql("id" ,
                    "select log_id from staff_log where type = 2 and staff_id="
                            +params.get("staffId").toString()
            );
        }
        queryWrapper.orderByDesc("id");
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
