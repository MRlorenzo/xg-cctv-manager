package com.xg.cctv.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xg.cctv.common.dto.DailyLogVo;
import com.xg.cctv.common.dto.VoService;
import com.xg.cctv.mybatis.po.DailyLog;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 西港监控部日志表 服务类
 * </p>
 *
 * @author lorenzo
 * @since 2020-02-05
 */
public interface DailyLogService extends IService<DailyLog>, VoService<DailyLogVo> {

    /**
     * 分页查询
     * @param page
     * @param dailyLog
     * @return
     */
    IPage<DailyLog> selectPage(Page<DailyLog> page,DailyLog dailyLog);

    IPage<DailyLog> selectPage(Page<DailyLog> page,Map<String , Object> params);

    /**
     * 查询所有
     * @param dailyLog
     */
    List<DailyLog> selectList(DailyLog dailyLog);
}
