package com.itheima.subject.application.controller;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Preconditions;
import com.itheima.subject.application.convert.SubjectAnswerDTOConverter;
import com.itheima.subject.application.convert.SubjectCategoryDTOConverter;
import com.itheima.subject.application.convert.SubjectInfoDTOConverter;
import com.itheima.subject.application.dto.SubjectCategoryDTO;
import com.itheima.subject.application.dto.SubjectInfoDTO;
import com.itheima.subject.common.entity.Result;
import com.itheima.subject.domain.entity.SubjectAnswerBO;
import com.itheima.subject.domain.entity.SubjectCategoryBO;
import com.itheima.subject.domain.entity.SubjectInfoBO;
import com.itheima.subject.domain.service.SubjectInfoDomainService;
import com.itheima.subject.infra.basic.entity.SubjectCategory;
import com.itheima.subject.infra.basic.service.SubjectCategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 题目controller
 */
@RestController
@Slf4j
@RequestMapping("subject")
public class SubjectController {

    @Resource
    private SubjectInfoDomainService subjectInfoDomainService;

    @PostMapping("/add")
    public Result<Boolean> add(@RequestBody SubjectInfoDTO subjectInfoDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("SubjectCategoryController.add.dto: {}", JSON.toJSONString(subjectInfoDTO));
            }
            Preconditions.checkNotNull(subjectInfoDTO.getSubjectName(), "题目名称不能为空");
            Preconditions.checkNotNull(subjectInfoDTO.getSubjectDifficult(), "题目难度不能为空");
            Preconditions.checkNotNull(subjectInfoDTO.getSubjectType(), "题目类型不能为空");
            Preconditions.checkNotNull(subjectInfoDTO.getSubjectScore(), "题目分数不能为空");
            Preconditions.checkArgument(!CollectionUtils.isEmpty(subjectInfoDTO.getCategoryIds()),
                    "题目分类不能为空");
            Preconditions.checkArgument(!CollectionUtils.isEmpty(subjectInfoDTO.getLabelIds()),
                    "题目标签不能为空");
            SubjectInfoBO subjectInfoBO = SubjectInfoDTOConverter.INSTANCE.convertDtoToBo(subjectInfoDTO);
            List<SubjectAnswerBO> subjectAnswerBOS = SubjectAnswerDTOConverter.INSTANCE.convertDtoToBo(subjectInfoDTO.getOptionList());
            subjectInfoBO.setOptionList(subjectAnswerBOS);
            subjectInfoDomainService.add(subjectInfoBO);
            return Result.ok(true);
        } catch (Exception e) {
            log.error("SubjectCategoryController.add.error: {}", e.getMessage());
            return Result.fail(e.getMessage());

        }
    }


}
