package com.itheima.subject.domain.service.impl;

import com.alibaba.fastjson.JSON;
import com.itheima.subject.common.entity.PageResult;
import com.itheima.subject.common.enums.IsDeleteFlagEnum;
import com.itheima.subject.domain.convert.SubjectInfoConverter;
import com.itheima.subject.domain.convert.SubjectLabelConverter;
import com.itheima.subject.domain.entity.SubjectInfoBO;
import com.itheima.subject.domain.entity.SubjectLabelBO;
import com.itheima.subject.domain.entity.SubjectOptionBO;
import com.itheima.subject.domain.handler.subject.SubjectTypeHandler;
import com.itheima.subject.domain.handler.subject.SubjectTypeHandlerFactory;
import com.itheima.subject.domain.service.SubjectInfoDomainService;
import com.itheima.subject.domain.service.SubjectLabelDomainService;
import com.itheima.subject.infra.basic.entity.SubjectInfo;
import com.itheima.subject.infra.basic.entity.SubjectLabel;
import com.itheima.subject.infra.basic.entity.SubjectMapping;
import com.itheima.subject.infra.basic.service.SubjectInfoService;
import com.itheima.subject.infra.basic.service.SubjectLabelService;
import com.itheima.subject.infra.basic.service.SubjectMappingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class SubjectInfoDomainServiceImpl implements SubjectInfoDomainService {

    @Resource
    private SubjectInfoService subjectInfoService;

    @Resource
    private SubjectTypeHandlerFactory subjectTypeHandlerFactory;

    @Resource
    private SubjectMappingService subjectMappingService;

    @Resource
    private SubjectLabelService subjectLabelService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(SubjectInfoBO subjectInfoBO) {
        if (log.isInfoEnabled()) {
            log.info("SubjectInfoDomainServiceImpl.add.bo: {}", JSON.toJSONString(subjectInfoBO));
        }
        SubjectInfo subjectInfo = SubjectInfoConverter.INSTANCE.convertBoToInfo(subjectInfoBO);
        subjectInfo.setIsDeleted(IsDeleteFlagEnum.UN_DELETED.getCode());
        subjectInfoService.insert(subjectInfo);
        //工厂和策略模式
        SubjectTypeHandler handler = subjectTypeHandlerFactory.getHandler(subjectInfo.getSubjectType());
        subjectInfoBO.setId(subjectInfo.getId());
        handler.add(subjectInfoBO);
        List<Integer> categoryIds = subjectInfoBO.getCategoryIds();
        List<Integer> labelIds = subjectInfoBO.getLabelIds();
        List<SubjectMapping> mappingList = new ArrayList<>();
        categoryIds.forEach(categoryId -> {
           labelIds.forEach(labelId -> {
               SubjectMapping subjectMapping = new SubjectMapping();
               subjectMapping.setSubjectId(subjectInfo.getId());
               subjectMapping.setCategoryId(Long.valueOf(categoryId));
               subjectMapping.setLabelId(Long.valueOf(labelId));
               subjectMapping.setIsDeleted(IsDeleteFlagEnum.UN_DELETED.getCode());
               mappingList.add(subjectMapping);
           });
        });
        subjectMappingService.batchInsert(mappingList);
    }

    @Override
    public PageResult<SubjectInfoBO> getSubjectPage(SubjectInfoBO subjectInfoBO) {
        PageResult<SubjectInfoBO> pageResult = new PageResult<>();
        pageResult.setPageNo(subjectInfoBO.getPageNo());
        pageResult.setPageSize(subjectInfoBO.getPageSize());
        int start = (subjectInfoBO.getPageNo() - 1) * subjectInfoBO.getPageSize();
        SubjectInfo subjectInfo = SubjectInfoConverter.INSTANCE.convertBoToInfo(subjectInfoBO);
        int count = subjectInfoService.countByCondition(subjectInfo, subjectInfoBO.getCategoryId(),
                subjectInfoBO.getLabelId());
        if(count == 0) {
            return pageResult;
        }
        List<SubjectInfo> subjectInfoList = subjectInfoService.queryPage(subjectInfo, subjectInfoBO.getCategoryId(),
                subjectInfoBO.getLabelId(), start, subjectInfoBO.getPageSize());
        List<SubjectInfoBO> subjectInfoBOS = SubjectInfoConverter.INSTANCE.convertInfoListToBoInfo(subjectInfoList);
        pageResult.setRecords(subjectInfoBOS);
        pageResult.setTotalRecoredsNum(count);
        return pageResult;
    }

    @Override
    public SubjectInfoBO querySubjectInfo(SubjectInfoBO subjectInfoBO) {
        SubjectInfo subjectInfo = subjectInfoService.queryById(subjectInfoBO.getId());
        SubjectTypeHandler handler = subjectTypeHandlerFactory.getHandler(subjectInfo.getSubjectType());
        SubjectOptionBO subjectOptionBO = handler.querySubjectInfo(subjectInfo.getId().intValue());
        SubjectInfoBO bo = SubjectInfoConverter.INSTANCE.convertOptionToBo(subjectOptionBO, subjectInfo);
        List<String> labelName = new ArrayList<>();
        SubjectMapping subjectMapping = new SubjectMapping();
        subjectMapping.setSubjectId(subjectInfo.getId());
        subjectMapping.setIsDeleted(IsDeleteFlagEnum.UN_DELETED.getCode());
        List<SubjectMapping> mappingList = subjectMappingService.queryLabelId(subjectMapping);
        mappingList.forEach(mapping -> {
            Long labelId = mapping.getLabelId();
            SubjectLabel subjectLabel = subjectLabelService.queryById(Long.valueOf(labelId));
            labelName.add(subjectLabel.getLabelName());
        });
        bo.setLabelName(labelName);
        return bo;
    }

//    @Override
//    public Boolean update(SubjectLabelBO subjectLabelBO) {
//        if (log.isInfoEnabled()) {
//            log.info("SubjectLabelDomainServiceImpl.update.bo: {}", JSON.toJSONString(subjectLabelBO));
//        }
//        SubjectLabel subjectLabel = SubjectLabelConverter.INSTANCE.convertBoToLabel(subjectLabelBO);
//        return subjectlabelService.update(subjectLabel) > 0;
//    }
//
//    @Override
//    public Boolean delete(SubjectLabelBO subjectLabelBO) {
//        if (log.isInfoEnabled()) {
//            log.info("SubjectLabelDomainServiceImpl.delete.bo: {}", JSON.toJSONString(subjectLabelBO));
//        }
//        SubjectLabel subjectLabel = SubjectLabelConverter.INSTANCE.convertBoToLabel(subjectLabelBO);
//        subjectLabel.setIsDeleted(IsDeleteFlagEnum.DELETED.getCode());
//        return subjectlabelService.update(subjectLabel) > 0;
//    }
//
//    @Override
//    public List<SubjectLabelBO> queryLabelByCategoryId(SubjectLabelBO subjectLabelBO) {
//        Long categoryId = subjectLabelBO.getCategoryId();
//        SubjectMapping subjectMapping = new SubjectMapping();
//        subjectMapping.setCategoryId(categoryId);
//        subjectMapping.setIsDeleted(IsDeleteFlagEnum.UN_DELETED.getCode());
//        List<SubjectMapping> mappingList = subjectMappingService.queryLabelId(subjectMapping);
//        if (CollectionUtils.isEmpty(mappingList)) {
//            return Collections.emptyList();
//        }
//        List<Long> labelIdList = mappingList.stream().map(SubjectMapping::getLabelId).collect(Collectors.toList());
//        List<SubjectLabel> labelList = subjectlabelService.queryByIds(labelIdList);
//        List<SubjectLabelBO> subjectLabelBOList = new ArrayList<>();
//        labelList.forEach(label -> {
//            SubjectLabelBO bo = new SubjectLabelBO();
//            bo.setId(label.getId());
//            bo.setLabelName(label.getLabelName());
//            bo.setSortNum(label.getSortNum());
//            bo.setCategoryId(categoryId);
//            subjectLabelBOList.add(bo);
//        });
//        return subjectLabelBOList;
//    }
}
