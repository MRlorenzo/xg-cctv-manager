package com.xg.cctv.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xg.cctv.mybatis.po.TitleMain;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * <p>
 * 事件记录主题名称表 服务类
 * </p>
 *
 * @author lorenzo
 * @since 2020-01-30
 */
public interface TitleMainService extends IService<TitleMain> {

    /**
     * 分页查询
     * @param page
     * @param titleMain
     * @return
     */
    IPage<TitleMain> selectPage(Page<TitleMain> page,TitleMain titleMain);

    /**
     * 查询所有
     * @param titleMain
     */
    List<TitleMain> selectList(TitleMain titleMain);
}
