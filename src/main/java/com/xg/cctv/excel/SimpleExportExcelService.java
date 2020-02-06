package com.xg.cctv.excel;

import com.xg.cctv.common.util.jxls.JxlsMap;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 以简单格式导出excel
 * @param <T>
 */
public abstract class SimpleExportExcelService<T> implements ExportExcelService<T>{


    protected static final String REPORT_TEMPLATE_PATH = "/templates/excel/table_report.xls";
    protected static final NumberFormat NUMBER_FORMAT = new DecimalFormat(",###.##");
    protected static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private List<List<ReportDataItem>> parseTo2DimensionalList(List<T> list){
        List<List<ReportDataItem>> dataList = new ArrayList<>();
        List<String> titleList = getTitle(list);
        List<ReportDataItem> titleRow = getTitleRow(titleList);
        List<List<ReportDataItem>> bodyRows = getBodyRows(list);
        List<ReportDataItem> footerRow = getFooterRow(list);
        if (titleRow != null && titleRow.size()>0){
            dataList.add(titleRow);
        }

        /*
         * 如果getBodyRows方法返回了null,则迭代执行bodyRowItem方法
         */
        if (bodyRows != null && bodyRows.size()>0){
            dataList.addAll(bodyRows);
        }else {
            dataList.addAll(getDefaultBodyRows(list));
        }

        if (footerRow != null && footerRow.size()>0){
            dataList.add(footerRow);
        }
        return dataList;
    }

    private List<List<ReportDataItem>> getDefaultBodyRows(List<T> list){
        List<List<ReportDataItem>> bodyRows = new ArrayList<>();

        for (T item: list) {
            List<ReportDataItem> row = bodyRowItem(item);
            if (row != null){
                bodyRows.add(row);
            }
        }

        return bodyRows;
    }

    protected abstract List<ReportDataItem> bodyRowItem(T item);

    protected abstract List<String> getTitle(List<T> list);

    private List<ReportDataItem> getTitleRow(List<String> titles){
        List<ReportDataItem> items = new ArrayList<>();
        titles.forEach(t ->{
            items.add(cell(t));
        });
        return items;
    }

    protected List<List<ReportDataItem>> getBodyRows(List<T> list){
        return null;
    }

    protected List<ReportDataItem> getFooterRow(List<T> list){
        return null;
    }

    /**
     * 导出excel
     * @param dataList
     * @return
     */
    public String exportExcel(List<T> dataList){
        List<List<ReportDataItem>> list = parseTo2DimensionalList(dataList);
        Map<String, Object> model = new HashMap<>();
        model.put("list", list);

        String uuid = JxlsMap.xls(REPORT_TEMPLATE_PATH , model);
        return uuid;
    }


    protected ReportDataItem numberCell(Object bigDecimal){
        ReportDataItem cellItem = new ReportDataItem();
        cellItem.setVal(NUMBER_FORMAT.format(toBigDecimal(bigDecimal)));
        return cellItem;
    }

    protected ReportDataItem dateCell(Date date){
        ReportDataItem cellItem = new ReportDataItem();
        cellItem.setVal(date != null? DATE_FORMAT.format(date): "-");
        return cellItem;
    }

    /**
     * 转化为BigDecimal对象
     * @param value
     * @return
     */
    protected BigDecimal toBigDecimal(Object value){
        if(value == null){
            return BigDecimal.ZERO;
        }else if(value instanceof BigDecimal){
            return (BigDecimal) value;
        }else{
            return new BigDecimal(value.toString());
        }
    }

    /**
     * 获取封装的单元格
     * @param value
     * @return
     */
    protected ReportDataItem cell(Object value){
        if(value == null || value.toString().length() == 0){
            return ReportDataItem.EMPTY;
        }

        ReportDataItem data = new ReportDataItem();
        data.setVal(value);
        return data;
    }

    protected ReportDataItem cell(Object value , int type){
        return new ReportDataItem(value , type);
    }

}
