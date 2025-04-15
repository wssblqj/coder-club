package com.itheima.subject.infra.basic.entity;

import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * 单选题信息表(SubjectRadio)实体类
 *
 * @author makejava
 * @since 2025-04-10 12:18:59
 */
@Data
public class SubjectRadio implements Serializable {
    private static final long serialVersionUID = -41581631522785415L;
    /**
     * 主键
     */
    private Long id;
    /**
     * 题目id
     */
    private Long subjectId;
    /**
     * a,b,c,d,e
     */
    private Long optionType;
    /**
     * 选项内容
     */
    private String optionContent;
    /**
     * 是否正确
     */
    private String isCorrect;
    /**
     * 创建人
     */
    private String createdBy;
    /**
     * 创建时间
     */
    private Date createdTime;
    /**
     * 更新人
     */
    private String updateBy;
    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 删除标识
     */
    private Integer isDeleted;
}

