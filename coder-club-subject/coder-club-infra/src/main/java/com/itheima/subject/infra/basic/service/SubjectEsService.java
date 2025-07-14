package com.itheima.subject.infra.basic.service;


import com.itheima.subject.common.entity.PageResult;
import com.itheima.subject.infra.basic.entity.SubjectInfoEs;

public interface SubjectEsService {

    boolean insert(SubjectInfoEs subjectInfoEs);

    PageResult<SubjectInfoEs> querySubjectList(SubjectInfoEs subjectInfoEs);
}
