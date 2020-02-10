package com.xg.cctv.excel.impl;

import com.xg.cctv.common.util.jxls.JxlsMap;
import com.xg.cctv.excel.ExportExcelService;
import com.xg.cctv.exception.RRException;
import com.xg.cctv.mybatis.po.Customer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerExcelService implements ExportExcelService<Customer>{
    protected static final String REPORT_TEMPLATE_PATH = "/templates/excel/customer.xlsx";

    @Override
    public String exportExcel(List<Customer> list) {
        if (list == null || list.isEmpty()){
            throw new RRException("数据列表不能为空");
        }
        Map<String, Object> model = new HashMap<>();
        model.put("list", list);

        String uuid = JxlsMap.xlsx(REPORT_TEMPLATE_PATH , model);
        return uuid;
    }
}
