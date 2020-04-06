package com.xg.cctv.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xg.cctv.mybatis.po.SysStaff;
import com.xg.cctv.mybatis.mapper.SysStaffMapper;
import com.xg.cctv.service.SysStaffService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;
/**
 * <p>
 * 员工表 服务实现类
 * </p>
 *
 * @author lorenzo
 * @since 2020-04-04
 */
@Service
@Transactional
public class SysStaffServiceImpl extends ServiceImpl<SysStaffMapper, SysStaff> implements SysStaffService {

    @Autowired
    private SysStaffMapper sysStaffMapper;

    @Override
    public IPage<SysStaff> selectPage(Page<SysStaff> page, SysStaff sysStaff) {
        QueryWrapper<SysStaff> queryWrapper = new QueryWrapper<SysStaff>();
        getQueryWrapper(queryWrapper,sysStaff);
        return sysStaffMapper.selectPage(page,queryWrapper);
    }

    @Override
    public IPage<SysStaff> selectPage(Page<SysStaff> page, Map<String, Object> params) {
        QueryWrapper<SysStaff> queryWrapper = new QueryWrapper<SysStaff>();
        getQueryWrapper(queryWrapper , params);
        return sysStaffMapper.selectPage(page , queryWrapper);
    }

    @Override
    public List<SysStaff> selectList(SysStaff sysStaff) {
        QueryWrapper<SysStaff> queryWrapper = new QueryWrapper<SysStaff>();
        getQueryWrapper(queryWrapper , sysStaff);
        return sysStaffMapper.selectList(queryWrapper);
    }

    @Override
    public List<SysStaff> selectList(Map<String , Object> params) {
        QueryWrapper<SysStaff> queryWrapper = new QueryWrapper<SysStaff>();
        getQueryWrapper(queryWrapper , params);
        return sysStaffMapper.selectList(queryWrapper);
    }

    @Override
    public IPage<SysStaff> selectAllInfoPage(Page<SysStaff> page, Map<String, Object> params) {
        QueryWrapper<SysStaff> queryWrapper = new QueryWrapper<SysStaff>();
        getQueryWrapper(queryWrapper , params);
        return sysStaffMapper.selectAllInfoPage(page , queryWrapper);
    }

    @Override
    public List<SysStaff> selectListByLikeName(String name) {
        QueryWrapper<SysStaff> queryWrapper = new QueryWrapper<SysStaff>();
        if (StringUtils.isNotEmpty(name)){
            queryWrapper.like("staff_name" , name);
        }
        return sysStaffMapper.selectList(queryWrapper);
    }
    /**
     *  公共查询条件
     * @param queryWrapper
     * @return
     */
    public QueryWrapper<SysStaff> getQueryWrapper(QueryWrapper<SysStaff> queryWrapper,SysStaff sysStaff){
        //条件拼接
    
        return queryWrapper;
    }


    public QueryWrapper<SysStaff> getQueryWrapper(QueryWrapper<SysStaff> queryWrapper,Map<String , Object> params){
        //条件拼接
        if (params == null){
            return queryWrapper;
        }
        if (params.get("startDate") != null){
            queryWrapper.ge(true , "hire_date" , params.get("startTime"));
        }

        if (params.get("endDate") != null){
            queryWrapper.le(true , "hire_date" , params.get("endTime"));
            // queryWrapper.apply("UNIX_TIMESTAMP(create_time) <= UNIX_TIMESTAMP('{0}')" , params.get("endDate"));
        }

        if (params.get("departmentId") != null){
            queryWrapper.eq("department_id" , params.get("departmentId"));
        }

        if (params.get("staffName") != null){
            queryWrapper.like("staff_name" , params.get("staffName"));
        }
        if (params.get("status") != null){
            queryWrapper.eq("status" , params.get("status"));
        }

        return queryWrapper;
    }
}
