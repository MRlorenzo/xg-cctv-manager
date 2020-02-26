package com.xg.cctv.excel;

import java.io.IOException;
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

    /**
     * 导出excel
     * @param serverBaseFilePath 图片等资源存放地址的根路径
     * @param list
     * @return
     */
    String exportExcel(String serverBaseFilePath ,List<T> list) throws IOException;
}
