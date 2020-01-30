package com.xg.cctv.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xg.cctv.mybatis.po.TitleListItem;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * <p>
 * 事件名称及编号 服务类
 * </p>
 *
 * @author lorenzo
 * @since 2020-01-30
 */
public interface TitleListItemService extends IService<TitleListItem> {

    /**
     * 分页查询
     * @param page
     * @param titleListItem
     * @return
     */
    IPage<TitleListItem> selectPage(Page<TitleListItem> page,TitleListItem titleListItem);

    /**
     * 查询所有
     * @param titleListItem
     */
    List<TitleListItem> selectList(TitleListItem titleListItem);
}
