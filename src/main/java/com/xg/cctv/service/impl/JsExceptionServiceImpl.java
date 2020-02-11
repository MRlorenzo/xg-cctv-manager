package com.xg.cctv.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xg.cctv.mybatis.po.JsException;
import com.xg.cctv.mybatis.mapper.JsExceptionMapper;
import com.xg.cctv.service.JsExceptionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lorenzo
 * @since 2020-02-11
 */
@Service
@Transactional
public class JsExceptionServiceImpl extends ServiceImpl<JsExceptionMapper, JsException> implements JsExceptionService {

    @Autowired
    private JsExceptionMapper jsExceptionMapper;

    @Override
    public IPage<JsException> selectPage(Page<JsException> page, JsException jsException) {
        QueryWrapper<JsException> queryWrapper = new QueryWrapper<JsException>();
        getQueryWrapper(queryWrapper,jsException);
        return jsExceptionMapper.selectPage(page,queryWrapper);
    }

    @Override
    public IPage<JsException> selectPage(Page<JsException> page, Map<String, Object> params) {
        QueryWrapper<JsException> queryWrapper = new QueryWrapper<JsException>();
        getQueryWrapper(queryWrapper , params);
        return jsExceptionMapper.selectPage(page , queryWrapper);
    }

    @Override
    public List<JsException> selectList(JsException jsException) {
        QueryWrapper<JsException> queryWrapper = new QueryWrapper<JsException>();
        getQueryWrapper(queryWrapper , jsException);
        return jsExceptionMapper.selectList(queryWrapper);
    }

    @Override
    public List<JsException> selectList(Map<String , Object> params) {
        QueryWrapper<JsException> queryWrapper = new QueryWrapper<JsException>();
        getQueryWrapper(queryWrapper , params);
        return jsExceptionMapper.selectList(queryWrapper);
    }

    /**
     *  公共查询条件
     * @param queryWrapper
     * @return
     */
    public QueryWrapper<JsException> getQueryWrapper(QueryWrapper<JsException> queryWrapper,JsException jsException){
        //条件拼接
    
        return queryWrapper;
    }

    public QueryWrapper<JsException> getQueryWrapper(QueryWrapper<JsException> queryWrapper,Map<String , Object> params){
    //条件拼接
    return queryWrapper;
    }
}
