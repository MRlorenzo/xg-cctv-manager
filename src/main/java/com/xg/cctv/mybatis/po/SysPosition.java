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
 * 职位表
 * </p>
 *
 * @author lorenzo
 * @since 2020-04-04
 */
@ApiModel(value="职位表实体:SysPosition", description="职位表")
public class SysPosition extends Model<SysPosition> {


    @ApiModelProperty(value = "职位id")
    @TableId(value = "position_id", type = IdType.AUTO)
    private Long positionId;

    @ApiModelProperty(value = "职位名称")
    private String name;

    @ApiModelProperty(value = "描述")
    private String description;

    @ApiModelProperty(value = "职位编号")
    private String positionCode;

    @ApiModelProperty(value = "排序字段")
    private Integer sort;


    public Long getPositionId() {
        return positionId;
    }

    public void setPositionId(Long positionId) {
        this.positionId = positionId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPositionCode() {
        return positionCode;
    }

    public void setPositionCode(String positionCode) {
        this.positionCode = positionCode;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    @Override
    protected Serializable pkVal() {
        return this.positionId;
    }

    @Override
    public String toString() {
        return "SysPosition{" +
        "positionId=" + positionId +
        ", name=" + name +
        ", description=" + description +
        ", positionCode=" + positionCode +
        ", sort=" + sort +
        "}";
    }
}
