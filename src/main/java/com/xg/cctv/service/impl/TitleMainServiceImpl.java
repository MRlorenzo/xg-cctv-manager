package com.xg.cctv.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xg.cctv.mybatis.po.TitleMain;
import com.xg.cctv.mybatis.mapper.TitleMainMapper;
import com.xg.cctv.service.TitleMainService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
/**
 * <p>
 * 事件记录主题名称表 服务实现类
 * </p>
 *
 * @author lorenzo
 * @since 2020-01-30
 */
@Service
@Transactional
public class TitleMainServiceImpl extends ServiceImpl<TitleMainMapper, TitleMain> implements TitleMainService {

    @Autowired
    private TitleMainMapper titleMainMapper;

    @Override
    public IPage<TitleMain> selectPage(Page<TitleMain> page, TitleMain titleMain) {
        QueryWrapper<TitleMain> queryWrapper = new QueryWrapper<TitleMain>();
        getQueryWrapper(queryWrapper,titleMain);
        return titleMainMapper.selectPage(page,queryWrapper);
    }

    @Override
    public List<TitleMain> selectList(TitleMain titleMain) {
        QueryWrapper<TitleMain> queryWrapper = new QueryWrapper<TitleMain>();
        getQueryWrapper(queryWrapper,titleMain);
        return titleMainMapper.selectList(queryWrapper);
    }

    /**
     *  公共查询条件
     * @param queryWrapper
     * @return
     */
    public QueryWrapper<TitleMain> getQueryWrapper(QueryWrapper<TitleMain> queryWrapper,TitleMain titleMain){
        //条件拼接
    
        return queryWrapper;
    }
}
