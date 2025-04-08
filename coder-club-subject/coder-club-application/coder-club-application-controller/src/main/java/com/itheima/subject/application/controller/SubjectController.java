package com.itheima.subject.application.controller;

import com.itheima.subject.infra.basic.entity.SubjectCategory;
import com.itheima.subject.infra.basic.service.SubjectCategoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 刷题controller
 */
@RestController
public class SubjectController {

    @Resource
    private SubjectCategoryService subjectCategoryService;
    @GetMapping(value = "/hello")
    public String test() {
        SubjectCategory subjectCategory = subjectCategoryService.queryById(1L);
        return subjectCategory.getCategoryName();
    }

}
