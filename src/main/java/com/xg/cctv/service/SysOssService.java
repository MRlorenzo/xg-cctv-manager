package com.xg.cctv.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xg.cctv.mybatis.po.SysOss;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * <p>
 * 文件上传 服务类
 * </p>
 *
 * @author lorenzo
 * @since 2020-02-04
 */
public interface SysOssService extends IService<SysOss> {

    /**
     * 分页查询
     * @param page
     * @param sysOss
     * @return
     */
    IPage<SysOss> selectPage(Page<SysOss> page,SysOss sysOss);

    /**
     * 查询所有
     * @param sysOss
     */
    List<SysOss> selectList(SysOss sysOss);
}
