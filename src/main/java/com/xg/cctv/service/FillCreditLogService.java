package com.xg.cctv.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xg.cctv.mybatis.po.FillCreditLog;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 西港监控部日常加彩/缴码表 服务类
 * </p>
 *
 * @author lorenzo
 * @since 2020-02-01
 */
public interface FillCreditLogService extends IService<FillCreditLog> {

    /**
     * 分页查询
     * @param page
     * @param fillCreditLog
     * @return
     */
    IPage<FillCreditLog> selectPage(Page<FillCreditLog> page,FillCreditLog fillCreditLog);

    IPage<FillCreditLog> selectPage(Page<FillCreditLog> page,Map<String , Object> params);

    /**
     * 查询所有
     * @param fillCreditLog
     */
    List<FillCreditLog> selectList(FillCreditLog fillCreditLog);

    List<FillCreditLog> selectList(Map<String , Object> params);
}
