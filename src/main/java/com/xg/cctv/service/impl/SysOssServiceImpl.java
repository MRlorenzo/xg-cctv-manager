package com.xg.cctv.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xg.cctv.mybatis.po.SysOss;
import com.xg.cctv.mybatis.mapper.SysOssMapper;
import com.xg.cctv.service.SysOssService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
/**
 * <p>
 * 文件上传 服务实现类
 * </p>
 *
 * @author lorenzo
 * @since 2020-02-04
 */
@Service
@Transactional
public class SysOssServiceImpl extends ServiceImpl<SysOssMapper, SysOss> implements SysOssService {

    @Autowired
    private SysOssMapper sysOssMapper;

    @Override
    public IPage<SysOss> selectPage(Page<SysOss> page, SysOss sysOss) {
        QueryWrapper<SysOss> queryWrapper = new QueryWrapper<SysOss>();
        getQueryWrapper(queryWrapper,sysOss);
        return sysOssMapper.selectPage(page,queryWrapper);
    }

    @Override
    public List<SysOss> selectList(SysOss sysOss) {
        QueryWrapper<SysOss> queryWrapper = new QueryWrapper<SysOss>();
        getQueryWrapper(queryWrapper,sysOss);
        return sysOssMapper.selectList(queryWrapper);
    }

    /**
     *  公共查询条件
     * @param queryWrapper
     * @return
     */
    public QueryWrapper<SysOss> getQueryWrapper(QueryWrapper<SysOss> queryWrapper,SysOss sysOss){
        //条件拼接
    
        return queryWrapper;
    }
}
