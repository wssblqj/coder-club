package com.itheima.subject.domain.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.api.R;
import com.itheima.subject.common.enums.CategoryTypeEnum;
import com.itheima.subject.common.enums.IsDeleteFlagEnum;
import com.itheima.subject.domain.convert.SubjectCategoryConverter;
import com.itheima.subject.domain.convert.SubjectLabelConverter;
import com.itheima.subject.domain.entity.SubjectCategoryBO;
import com.itheima.subject.domain.entity.SubjectLabelBO;
import com.itheima.subject.domain.service.SubjectCategoryDomainService;
import com.itheima.subject.domain.service.SubjectLabelDomainService;
import com.itheima.subject.infra.basic.entity.SubjectCategory;
import com.itheima.subject.infra.basic.entity.SubjectLabel;
import com.itheima.subject.infra.basic.entity.SubjectMapping;
import com.itheima.subject.infra.basic.service.SubjectCategoryService;
import com.itheima.subject.infra.basic.service.SubjectLabelService;
import com.itheima.subject.infra.basic.service.SubjectMappingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class SubjectLabelDomainServiceImpl implements SubjectLabelDomainService {

    @Resource
    private SubjectLabelService subjectlabelService;

    @Resource
    private SubjectMappingService subjectMappingService;

    @Resource
    private SubjectCategoryService subjectCategoryService;

    @Override
    public Boolean add(SubjectLabelBO subjectLabelBO) {
        if (log.isInfoEnabled()) {
            log.info("SubjectLabelDomainServiceImpl.add.bo: {}", JSON.toJSONString(subjectLabelBO));
        }
        SubjectLabel subjectLabel = SubjectLabelConverter.INSTANCE.convertBoToLabel(subjectLabelBO);
        subjectLabel.setIsDeleted(IsDeleteFlagEnum.UN_DELETED.getCode());
        int count = subjectlabelService.insert(subjectLabel);
        return count > 0;
    }

    @Override
    public Boolean update(SubjectLabelBO subjectLabelBO) {
        if (log.isInfoEnabled()) {
            log.info("SubjectLabelDomainServiceImpl.update.bo: {}", JSON.toJSONString(subjectLabelBO));
        }
        SubjectLabel subjectLabel = SubjectLabelConverter.INSTANCE.convertBoToLabel(subjectLabelBO);
        return subjectlabelService.update(subjectLabel) > 0;
    }

    @Override
    public Boolean delete(SubjectLabelBO subjectLabelBO) {
        if (log.isInfoEnabled()) {
            log.info("SubjectLabelDomainServiceImpl.delete.bo: {}", JSON.toJSONString(subjectLabelBO));
        }
        SubjectLabel subjectLabel = SubjectLabelConverter.INSTANCE.convertBoToLabel(subjectLabelBO);
        subjectLabel.setIsDeleted(IsDeleteFlagEnum.DELETED.getCode());
        return subjectlabelService.update(subjectLabel) > 0;
    }

    @Override
    public List<SubjectLabelBO> queryLabelByCategoryId(SubjectLabelBO subjectLabelBO) {
        //如果当前分类是1级分类， 则查询所有标签
        SubjectCategory subjectCategory = subjectCategoryService.queryById(subjectLabelBO.getCategoryId());
        if (CategoryTypeEnum.PRIMARY.getCode() == subjectCategory.getCategoryType()) {
            SubjectLabel subjectLabel = new SubjectLabel();
            subjectLabel.setCategoryId(subjectCategory.getId());
            List<SubjectLabel> labelList = subjectlabelService.queryByCondition(subjectLabel);
            List<SubjectLabelBO> subjectLabelBOS = SubjectLabelConverter.INSTANCE.convertLabelToBo(labelList);
            return subjectLabelBOS;
        }
        Long categoryId = subjectLabelBO.getCategoryId();
        SubjectMapping subjectMapping = new SubjectMapping();
        subjectMapping.setCategoryId(categoryId);
        subjectMapping.setIsDeleted(IsDeleteFlagEnum.UN_DELETED.getCode());
        List<SubjectMapping> mappingList = subjectMappingService.queryLabelId(subjectMapping);
        if (CollectionUtils.isEmpty(mappingList)) {
            return Collections.emptyList();
        }
        List<Long> labelIdList = mappingList.stream().map(SubjectMapping::getLabelId).collect(Collectors.toList());
        List<SubjectLabel> labelList = subjectlabelService.queryByIds(labelIdList);
        List<SubjectLabelBO> subjectLabelBOList = new ArrayList<>();
        labelList.forEach(label -> {
            SubjectLabelBO bo = new SubjectLabelBO();
            bo.setId(label.getId());
            bo.setLabelName(label.getLabelName());
            bo.setSortNum(label.getSortNum());
            bo.setCategoryId(categoryId);
            subjectLabelBOList.add(bo);
        });
        return subjectLabelBOList;
    }
}
