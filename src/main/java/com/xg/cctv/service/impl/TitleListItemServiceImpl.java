package com.xg.cctv.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xg.cctv.mybatis.po.TitleListItem;
import com.xg.cctv.mybatis.mapper.TitleListItemMapper;
import com.xg.cctv.service.TitleListItemService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
/**
 * <p>
 * 事件名称及编号 服务实现类
 * </p>
 *
 * @author lorenzo
 * @since 2020-01-30
 */
@Service
@Transactional
public class TitleListItemServiceImpl extends ServiceImpl<TitleListItemMapper, TitleListItem> implements TitleListItemService {

    @Autowired
    private TitleListItemMapper titleListItemMapper;

    @Override
    public IPage<TitleListItem> selectPage(Page<TitleListItem> page, TitleListItem titleListItem) {
        QueryWrapper<TitleListItem> queryWrapper = new QueryWrapper<TitleListItem>();
        getQueryWrapper(queryWrapper,titleListItem);
        return titleListItemMapper.selectPage(page,queryWrapper);
    }

    @Override
    public List<TitleListItem> selectList(TitleListItem titleListItem) {
        QueryWrapper<TitleListItem> queryWrapper = new QueryWrapper<TitleListItem>();
        getQueryWrapper(queryWrapper,titleListItem);
        return titleListItemMapper.selectList(queryWrapper);
    }

    /**
     *  公共查询条件
     * @param queryWrapper
     * @return
     */
    public QueryWrapper<TitleListItem> getQueryWrapper(QueryWrapper<TitleListItem> queryWrapper,TitleListItem titleListItem){
        //条件拼接
    
        return queryWrapper;
    }
}
