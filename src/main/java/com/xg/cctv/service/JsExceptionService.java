package com.xg.cctv.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xg.cctv.mybatis.po.JsException;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lorenzo
 * @since 2020-01-19
 */
public interface JsExceptionService extends IService<JsException> {

    /**
     * 分页查询
     * @param page
     * @param jsException
     * @return
     */
    IPage<JsException> selectPage(Page<JsException> page,JsException jsException);

    /**
     * 查询所有
     * @param jsException
     */
    List<JsException> selectList(JsException jsException);
}
