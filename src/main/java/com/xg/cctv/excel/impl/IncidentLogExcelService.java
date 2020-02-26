package com.xg.cctv.excel.impl;

import com.xg.cctv.common.dto.IncidentLogVo;
import com.xg.cctv.common.util.jxls.JxlsMap;
import com.xg.cctv.excel.ExportExcelService;
import com.xg.cctv.exception.RRException;
import com.xg.cctv.mybatis.po.IncidentLog;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IncidentLogExcelService implements ExportExcelService<IncidentLogVo> {
    protected static final String REPORT_TEMPLATE_PATH = "/templates/excel/incidentLog.xlsx";
    protected static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public String exportExcel(List<IncidentLogVo> list) {
        if (list == null || list.isEmpty()){
            throw new RRException("数据列表不能为空");
        }
        Map<String, Object> model = new HashMap<>();
        model.put("list", list);
        model.put("dFormat" , DATE_FORMAT);

        String uuid = JxlsMap.xlsx(REPORT_TEMPLATE_PATH , model);
        return uuid;
    }

    @Override
    public String exportExcel(String serverBaseFilePath, List<IncidentLogVo> list) throws IOException {
        for (IncidentLogVo d: list) {
            d.initImages(serverBaseFilePath);
        }
        return exportExcel(list);
    }
}
