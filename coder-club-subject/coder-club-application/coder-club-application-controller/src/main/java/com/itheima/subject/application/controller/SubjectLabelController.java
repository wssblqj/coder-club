package com.itheima.subject.application.controller;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Preconditions;
import com.itheima.subject.application.convert.SubjectLabelDTOConverter;
import com.itheima.subject.application.dto.SubjectLabelDTO;
import com.itheima.subject.common.entity.Result;
import com.itheima.subject.domain.convert.SubjectLabelConverter;
import com.itheima.subject.domain.entity.SubjectLabelBO;
import com.itheima.subject.domain.service.SubjectLabelDomainService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 标签controller
 */
@RestController
@RequestMapping("/subject/label")
@Slf4j
public class SubjectLabelController {

    @Resource
    private SubjectLabelDomainService subjectLabelDomainService;

    /**
     * 新增标签
     */
    @PostMapping("/add")
    public Result<Boolean> add(@RequestBody SubjectLabelDTO subjectLabelDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("SubjectLabelController.add.dto: {}", JSON.toJSONString(subjectLabelDTO));
            }

            Preconditions.checkArgument(!StringUtils.isEmpty(subjectLabelDTO.getLabelName()), "标签名称不能为空");
            SubjectLabelBO subjectLabelBO = SubjectLabelDTOConverter.INSTANCE.convertDtoToBo(subjectLabelDTO);
            Boolean res = subjectLabelDomainService.add(subjectLabelBO);
            return Result.ok(res);
        } catch (Exception e) {
            log.error("SubjectCategoryController.add.error: {}", e.getMessage());
            return Result.fail("新增标签失败");

        }
    }

    /**
     * 更新标签
     */
    @PostMapping("/update")
    public Result<Boolean> update(@RequestBody SubjectLabelDTO subjectLabelDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("SubjectLabelController.update.dto: {}", JSON.toJSONString(subjectLabelDTO));
            }
            Preconditions.checkNotNull(subjectLabelDTO.getId(), "标签id不能为空");
            SubjectLabelBO subjectLabelBO = SubjectLabelDTOConverter.INSTANCE.convertDtoToBo(subjectLabelDTO);
            return Result.ok(subjectLabelDomainService.update(subjectLabelBO));
        } catch (Exception e) {
            log.error("SubjectCategoryController.update.error: {}", e.getMessage());
            return Result.fail("更新标签失败");
        }
    }

    @PostMapping("/delete")
    public Result<Boolean> delete(@RequestBody SubjectLabelDTO subjectLabelDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("SubjectLabelController.delete.dto: {}", JSON.toJSONString(subjectLabelDTO));
            }
            Preconditions.checkNotNull(subjectLabelDTO.getId(), "标签id不能为空");
            SubjectLabelBO subjectLabelBO = SubjectLabelDTOConverter.INSTANCE.convertDtoToBo(subjectLabelDTO);
            return Result.ok(subjectLabelDomainService.delete(subjectLabelBO));
        } catch (Exception e) {
            log.error("SubjectCategoryController.update.error: {}", e.getMessage());
            return Result.fail("删除标签失败");
        }
    }

    @PostMapping("/queryLabelByCategoryId")
    public Result<List<SubjectLabelDTO>> queryLabelByCategoryId(@RequestBody SubjectLabelDTO subjectLabelDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("SubjectLabelController.queryLabelByCategoryId.dto: {}",
                        JSON.toJSONString(subjectLabelDTO));
            }
            Preconditions.checkNotNull(subjectLabelDTO.getCategoryId(), "分类id不能为空");
            SubjectLabelBO subjectLabelBO = SubjectLabelDTOConverter.INSTANCE.convertDtoToBo(subjectLabelDTO);
            List<SubjectLabelBO> boList = subjectLabelDomainService.queryLabelByCategoryId(subjectLabelBO);
            List<SubjectLabelDTO> res = SubjectLabelDTOConverter.INSTANCE.convertBoListToDtoList(boList);
            return Result.ok(res);
        } catch (Exception e) {
            log.error("SubjectCategoryController.queryLabelByCategoryId.error: {}", e.getMessage());
            return Result.fail("根据分类查询标签失败");
        }
    }
}
