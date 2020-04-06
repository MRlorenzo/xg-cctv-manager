package com.xg.cctv.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xg.cctv.mybatis.po.SysPosition;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 职位表 服务类
 * </p>
 *
 * @author lorenzo
 * @since 2020-04-04
 */
public interface SysPositionService extends IService<SysPosition> {

    /**
     * 分页查询
     * @param page
     * @param sysPosition
     * @return
     */
    IPage<SysPosition> selectPage(Page<SysPosition> page, SysPosition sysPosition);

    IPage<SysPosition> selectPage(Page<SysPosition> page, Map<String, Object> params);

    /**
     * 查询所有
     * @param sysPosition
     */
    List<SysPosition> selectList(SysPosition sysPosition);
}
