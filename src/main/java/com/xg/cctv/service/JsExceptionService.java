package com.xg.cctv.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xg.cctv.mybatis.po.JsException;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lorenzo
 * @since 2020-02-11
 */
public interface JsExceptionService extends IService<JsException> {

    /**
     * 分页查询
     * @param page
     * @param jsException
     * @return
     */
    IPage<JsException> selectPage(Page<JsException> page,JsException jsException);

    IPage<JsException> selectPage(Page<JsException> page,Map<String , Object> params);

    /**
     * 查询所有
     * @param jsException
     */
    List<JsException> selectList(JsException jsException);

    List<JsException> selectList(Map<String , Object> params);
}
