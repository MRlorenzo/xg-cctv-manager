package com.xg.cctv.mybatis.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.IdType;
import java.io.Serializable;

/**
 * <p>
 * 职位和事件、高额日志对应关系
 * </p>
 *
 * @author lorenzo
 * @since 2020-04-05
 */
@ApiModel(value="职位和事件、高额日志对应关系实体:StaffLog", description="职位和事件、高额日志对应关系")
public class StaffLog extends Model<StaffLog> {


    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "日志ID")
    private Long logId;

    @ApiModelProperty(value = "职员ID")
    private Long staffId;

    @ApiModelProperty(value = "1：高额活动、2：事件报告")
    private Integer type;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getLogId() {
        return logId;
    }

    public void setLogId(Long logId) {
        this.logId = logId;
    }

    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "StaffLog{" +
        "id=" + id +
        ", logId=" + logId +
        ", staffId=" + staffId +
        ", type=" + type +
        "}";
    }
}
