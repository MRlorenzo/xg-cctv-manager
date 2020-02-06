package com.xg.cctv.excel;

import java.util.List;

/**
 * 导出excel的服务
 * @param <T>
 */
public interface ExportExcelService<T> {

    /**
     * 导出excel
     * @param list
     * @return
     */
    String exportExcel(List<T> list);
}
