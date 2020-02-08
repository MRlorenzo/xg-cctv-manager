package com.xg.cctv.mybatis.mapper;

import com.xg.cctv.common.dto.DailyLogVo;
import com.xg.cctv.common.dto.VoMapper;
import com.xg.cctv.mybatis.po.DailyLog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 西港监控部日志表 Mapper 接口
 * </p>
 *
 * @author lorenzo
 * @since 2020-02-05
 */
public interface DailyLogMapper extends BaseMapper<DailyLog>,VoMapper<DailyLogVo> {

}
