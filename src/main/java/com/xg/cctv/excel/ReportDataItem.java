package com.xg.cctv.excel;

import java.io.Serializable;

public class ReportDataItem implements Serializable{

    public static final ReportDataItem EMPTY = new ReportDataItem();

    /**
     * 源对象
     */
    private Object val;

    /**
     * 显示类型 1.正常 ，2.绿色 , -1.红色
     */
    private int type = 1;

    /**
     * 合并单元格的相关信息，对象必须要有的属性，cols，rows
     */
    private Object merge;

    public ReportDataItem(){}

    public ReportDataItem(Object value , int type){
        this.val = value;
        this.type = type;
    }



    public Object getVal() {
        return val;
    }

    public void setVal(Object val) {
        this.val = val;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Object getMerge() {
        return merge;
    }

    public void setMerge(Object merge) {
        this.merge = merge;
    }
}
