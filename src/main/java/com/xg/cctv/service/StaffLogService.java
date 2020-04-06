package com.xg.cctv.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xg.cctv.mybatis.po.StaffLog;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 职位和事件、高额日志对应关系 服务类
 * </p>
 *
 * @author lorenzo
 * @since 2020-04-05
 */
public interface StaffLogService extends IService<StaffLog> {

    /**
     * 分页查询
     * @param page
     * @param staffLog
     * @return
     */
    IPage<StaffLog> selectPage(Page<StaffLog> page, StaffLog staffLog);

    IPage<StaffLog> selectPage(Page<StaffLog> page, Map<String, Object> params);

    /**
     * 查询所有
     * @param staffLog
     */
    List<StaffLog> selectList(StaffLog staffLog);

    List<StaffLog> selectList(Map<String, Object> params);
}
