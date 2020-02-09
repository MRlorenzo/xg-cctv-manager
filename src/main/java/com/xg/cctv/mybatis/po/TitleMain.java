package com.xg.cctv.mybatis.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.IdType;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * <p>
 * 事件记录主题名称表
 * </p>
 *
 * @author lorenzo
 * @since 2020-01-30
 */
public class TitleMain extends Model<TitleMain> {


    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 事件名称
     */
    @NotBlank
    private String subject;

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

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
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
        return "TitleMain{" +
        "id=" + id +
        ", subject=" + subject +
        ", createUid=" + createUid +
        ", createTime=" + createTime +
        "}";
    }
}
