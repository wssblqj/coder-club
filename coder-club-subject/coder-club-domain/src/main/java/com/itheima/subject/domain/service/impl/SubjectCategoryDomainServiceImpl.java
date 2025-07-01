package com.itheima.subject.domain.service.impl;

import com.alibaba.fastjson.JSON;
import com.itheima.subject.common.enums.IsDeleteFlagEnum;
import com.itheima.subject.domain.convert.SubjectCategoryConverter;
import com.itheima.subject.domain.entity.SubjectCategoryBO;
import com.itheima.subject.domain.entity.SubjectLabelBO;
import com.itheima.subject.domain.service.SubjectCategoryDomainService;
import com.itheima.subject.infra.basic.entity.SubjectCategory;
import com.itheima.subject.infra.basic.entity.SubjectLabel;
import com.itheima.subject.infra.basic.entity.SubjectMapping;
import com.itheima.subject.infra.basic.service.SubjectCategoryService;
import com.itheima.subject.infra.basic.service.SubjectLabelService;
import com.itheima.subject.infra.basic.service.SubjectMappingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.stream.Collectors;

@Service
@Slf4j
public class SubjectCategoryDomainServiceImpl implements SubjectCategoryDomainService {

    @Resource
    private SubjectCategoryService subjectCategoryService;

    @Autowired
    private SubjectMappingService subjectMappingService;

    @Autowired
    private SubjectLabelService subjectLabelService;

    @Autowired
    private ThreadPoolExecutor labelThreadPool;


    @Override
    public void insert(SubjectCategoryBO subjectCategoryBO) {
        if (log.isInfoEnabled()) {
            log.info("SubjectCategoryDomainServiceImpl.add.bo: {}", JSON.toJSONString(subjectCategoryBO));
        }
        SubjectCategory subjectCategory = SubjectCategoryConverter.INSTANCE.convertBoToCategory(subjectCategoryBO);
        subjectCategory.setIsDeleted(IsDeleteFlagEnum.UN_DELETED.getCode());
        subjectCategoryService.insert(subjectCategory);
    }

    @Override
    public List<SubjectCategoryBO> queryCategory(SubjectCategoryBO subjectCategoryBO) {
        SubjectCategory subjectCategory = SubjectCategoryConverter.INSTANCE
                .convertBoToCategory(subjectCategoryBO);
        subjectCategory.setIsDeleted(IsDeleteFlagEnum.UN_DELETED.getCode());
        List<SubjectCategory> subjectCategoryList = subjectCategoryService.queryCategory(subjectCategory);
        List<SubjectCategoryBO> subjectCategoryBOList = SubjectCategoryConverter.INSTANCE
                .convertCategoryToBo(subjectCategoryList);
        if (log.isInfoEnabled()) {
            log.info("SubjectCategoryDomainServiceImpl.queryPrimaryCategory.bolist: {}",
                    JSON.toJSONString(subjectCategoryBOList));
        }
        subjectCategoryBOList.forEach(bo -> {
            Integer subjectCount = subjectCategoryService.querySubjectCount(bo.getId());
            bo.setCount(subjectCount);
        });
        return subjectCategoryBOList;
    }

    @Override
    public Boolean update(SubjectCategoryBO subjectCategoryBO) {
        SubjectCategory subjectCategory = SubjectCategoryConverter.INSTANCE
                .convertBoToCategory(subjectCategoryBO);
        int count = subjectCategoryService.update(subjectCategory);
        return count > 0;
    }

    @Override
    public Boolean delete(SubjectCategoryBO subjectCategoryBO) {
        SubjectCategory subjectCategory = SubjectCategoryConverter.INSTANCE
                .convertBoToCategory(subjectCategoryBO);
        subjectCategory.setIsDeleted(IsDeleteFlagEnum.DELETED.getCode());
        int count = subjectCategoryService.update(subjectCategory);
        return count > 0;
    }

    @Override
    public List<SubjectCategoryBO> queryCategoryAndLabel(SubjectCategoryBO subjectCategoryBO) {
        SubjectCategory subjectCategory = new SubjectCategory();
        subjectCategory.setIsDeleted(IsDeleteFlagEnum.UN_DELETED.getCode());
        subjectCategory.setParentId(subjectCategoryBO.getId());
        List<SubjectCategory> subjectCategories = subjectCategoryService.queryCategory(subjectCategory);
        if(log.isInfoEnabled()) {
            log.info("SubjectCategoryDomainServiceImpl.queryCategoryAndLabel.subjectCategories: {}"
                    , JSON.toJSONString(subjectCategories));
        }
        List<FutureTask<Map<Long, List<SubjectLabelBO>>>> futureTaskList = new ArrayList<>();
        List<SubjectCategoryBO> subjectCategoryBOList = SubjectCategoryConverter.INSTANCE.convertCategoryToBo(subjectCategories);
        Map<Long, List<SubjectLabelBO>> map = new HashMap<>();
        List<CompletableFuture<Map<Long, List<SubjectLabelBO>>>> completableFutureList = subjectCategoryBOList
                .stream().map(bo -> CompletableFuture.supplyAsync(() ->
                        getSubjectLabelBOList(bo),
                        labelThreadPool
                    )).collect(Collectors.toList());
        completableFutureList.forEach(completableFuture -> {
            try {
                Map<Long, List<SubjectLabelBO>> resultMap = completableFuture.get();
                if (CollectionUtils.isEmpty(resultMap)) {
                    return;
                }
                map.putAll(resultMap);
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });
        subjectCategoryBOList.forEach(bo -> bo.setLabelBOList(map.get(bo.getId())));
        return subjectCategoryBOList;
    }

    private Map<Long, List<SubjectLabelBO>> getSubjectLabelBOList(SubjectCategoryBO bo) {
        if (log.isInfoEnabled()) {
            log.info("getSubjectLabelBOList.bo: {}", JSON.toJSONString(bo));
        }
        Map<Long, List<SubjectLabelBO>> labelMap = new HashMap<>();
        SubjectMapping subjectMapping = new SubjectMapping();
        subjectMapping.setCategoryId(bo.getId());
        List<SubjectMapping> mappingList = subjectMappingService.queryLabelId(subjectMapping);
        if (CollectionUtils.isEmpty(mappingList)) {
            return null;
        }
        List<Long> labelIdList = mappingList.stream().map(SubjectMapping::getLabelId).collect(Collectors.toList());
        List<SubjectLabel> subjectLabels = subjectLabelService.queryByIds(labelIdList);
        List<SubjectLabelBO> subjectLabelBOList = new ArrayList<>();
        subjectLabels.forEach(label -> {
            SubjectLabelBO subjectLabelBO = new SubjectLabelBO();
            subjectLabelBO.setId(label.getId());
            subjectLabelBO.setLabelName(label.getLabelName());
            subjectLabelBO.setSortNum(label.getSortNum());
            subjectLabelBO.setCategoryId(label.getCategoryId());
            subjectLabelBOList.add(subjectLabelBO);
        });
        labelMap.put(bo.getId(), subjectLabelBOList);
        return labelMap;
    }

}
