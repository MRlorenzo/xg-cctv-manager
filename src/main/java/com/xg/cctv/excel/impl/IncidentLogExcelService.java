package com.xg.cctv.excel.impl;

import com.xg.cctv.common.dto.IncidentLogVo;
import com.xg.cctv.common.util.jxls.JxlsMap;
import com.xg.cctv.excel.ExportExcelService;
import com.xg.cctv.mybatis.po.IncidentLog;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IncidentLogExcelService implements ExportExcelService<IncidentLogVo> {
    protected static final String REPORT_TEMPLATE_PATH = "/templates/excel/incidentLog.xlsx";
    protected static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public String exportExcel(List<IncidentLogVo> list) {
        Map<String, Object> model = new HashMap<>();
        model.put("list", list);
        model.put("dFormat" , DATE_FORMAT);

        String uuid = JxlsMap.xlsx(REPORT_TEMPLATE_PATH , model);
        return uuid;
    }
}
