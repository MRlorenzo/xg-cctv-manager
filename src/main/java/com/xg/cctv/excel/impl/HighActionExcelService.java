package com.xg.cctv.excel.impl;

import com.xg.cctv.common.util.jxls.JxlsMap;
import com.xg.cctv.excel.ExportExcelService;
import com.xg.cctv.mybatis.po.HighAction;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HighActionExcelService implements ExportExcelService<HighAction>{
    protected static final String REPORT_TEMPLATE_PATH = "/templates/excel/highAction.xlsx";

    @Override
    public String exportExcel(List<HighAction> list) {
        Map<String, Object> model = new HashMap<>();
        model.put("list", list);

        String uuid = JxlsMap.xlsx(REPORT_TEMPLATE_PATH , model);
        return uuid;
    }
}
