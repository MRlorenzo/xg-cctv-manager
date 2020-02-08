package com.xg.cctv.common.dto;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.Map;

public interface VoService<T> {

    IPage<T> selectVoPage(Page<T> page , Map<String , Object> params);

    QueryWrapper<T> getQueryWrapper(QueryWrapper<T> queryWrapper, Map<String , Object> params);

}
