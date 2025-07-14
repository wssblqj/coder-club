package com.itheima.subject.infra.basic.entity;

import com.itheima.subject.common.entity.PageInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.lang.annotation.Documented;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class SubjectInfoEs extends PageInfo implements Serializable{

    private Long subjectId;

    private Long docId;

    private String subjectName;

    private String subjectAnswer;

    private String createUser;

    private Long createTime;

    private Integer subjectType;

    private String keyWord;

    private BigDecimal score;
}
