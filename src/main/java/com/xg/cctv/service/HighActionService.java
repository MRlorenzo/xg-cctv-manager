package com.xg.cctv.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xg.cctv.mybatis.po.HighAction;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * <p>
 * 西港监控部高额活动表 服务类
 * </p>
 *
 * @author lorenzo
 * @since 2020-02-05
 */
public interface HighActionService extends IService<HighAction> {

    /**
     * 分页查询
     * @param page
     * @param highAction
     * @return
     */
    IPage<HighAction> selectPage(Page<HighAction> page,HighAction highAction);

    /**
     * 查询所有
     * @param highAction
     */
    List<HighAction> selectList(HighAction highAction);
}
