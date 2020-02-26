package com.xg.cctv.excel.impl;

import com.xg.cctv.excel.ReportDataItem;
import com.xg.cctv.excel.SimpleExportExcelService;
import com.xg.cctv.mybatis.po.DailyLog;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DailyLogSimpleExcelService extends SimpleExportExcelService<DailyLog>{

    @Override
    protected List<ReportDataItem> bodyRowItem(DailyLog item) {
        return new ArrayList<>();
    }

    @Override
    protected List<String> getTitle(List<DailyLog> list) {
        return Arrays.asList(
                "序号",
                "日期",
                "时间",
                "台号",
                "主题",
                "相关信息",
                "通知人",
                "部门",
                "监控部",
                "图片"
        );
    }

    @Override
    public String exportExcel(String serverBaseFilePath, List<DailyLog> list) throws IOException{
        return exportExcel(list);
    }
}
