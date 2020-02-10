package com.xg.cctv.common.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.xg.cctv.mybatis.po.DailyLog;
import org.jxls.util.Util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DailyLogVo extends DailyLog{

    /********************************部门相关字段*********************************/
    /**
     * 部门中文名称
     */
    private String name;


    /**
     * 部门编号
     */
    private String departmentCode;
    /********************************部门相关字段*********************************/

    @TableField(exist=false)
    private List<byte[]> imgs;

    public List<byte[]> getImgs() {
        return imgs;
    }

    public void setImgs(List<byte[]> imgs) {
        this.imgs = imgs;
    }

    public void initImages(String basePath) throws IOException {
        String urls = getUrls();
        String[] urlArr = urls.split(",");
        List<byte[]> imgs = new ArrayList<>();
        for (int i = 0; i < urlArr.length; i++) {
            String url = urlArr[i];
            FileInputStream inputStream = new FileInputStream(basePath + url);
            byte[] bytes = Util.toByteArray(inputStream);
            imgs.add(bytes);
        }
        setImgs(imgs);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartmentCode() {
        return departmentCode;
    }

    public void setDepartmentCode(String departmentCode) {
        this.departmentCode = departmentCode;
    }
}
