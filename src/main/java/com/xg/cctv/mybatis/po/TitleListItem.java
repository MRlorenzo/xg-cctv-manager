package com.xg.cctv.mybatis.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.IdType;
import java.io.Serializable;

/**
 * <p>
 * 事件名称及编号
 * </p>
 *
 * @author lorenzo
 * @since 2020-01-30
 */
public class TitleListItem extends Model<TitleListItem> {


    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 事件主题名称Id
     */
    private Long subjectId;

    /**
     * 错误内容
     */
    private String text;

    /**
     * 代码
     */
    private String code;

    /**
     * 创建人
     */
    private Long createUid;

    /**
     * 创建时间
     */
    private Date createTime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getCreateUid() {
        return createUid;
    }

    public void setCreateUid(Long createUid) {
        this.createUid = createUid;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "TitleListItem{" +
        "id=" + id +
        ", subjectId=" + subjectId +
        ", text=" + text +
        ", code=" + code +
        ", createUid=" + createUid +
        ", createTime=" + createTime +
        "}";
    }
}
