package com.itheima.subject.application.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class SubjectCategoryDTO implements Serializable {

    private Long id;
    /**
     * 分类名称
     */
    private String categoryName;
    /**
     * 分类的类型
     */
    private Integer categoryType;
    /**
     * 图标链接
     */
    private String imageUrl;
    /**
     * 父级id
     */
    private Long parentId;

    private Integer count;
}
