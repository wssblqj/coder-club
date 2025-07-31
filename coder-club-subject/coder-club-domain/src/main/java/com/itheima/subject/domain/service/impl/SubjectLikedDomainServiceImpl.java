package com.itheima.subject.domain.service.impl;

import com.alibaba.fastjson.JSON;
import com.itheima.subject.common.entity.PageResult;
import com.itheima.subject.common.enums.IsDeleteFlagEnum;
import com.itheima.subject.common.enums.SubjectLikedStatusEnum;
import com.itheima.subject.common.util.LoginUtil;
import com.itheima.subject.domain.convert.SubjectLikedConverter;
import com.itheima.subject.domain.entity.SubjectLikedBO;
import com.itheima.subject.domain.redis.RedisUtil;
import com.itheima.subject.domain.service.SubjectLikedDomainService;
import com.itheima.subject.infra.basic.entity.SubjectInfo;
import com.itheima.subject.infra.basic.entity.SubjectLiked;
import com.itheima.subject.infra.basic.service.SubjectInfoService;
import com.itheima.subject.infra.basic.service.SubjectLikedService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 题目点赞表 领域service实现了
 *
 * @author jingdianjichi
 * @since 2024-01-07 23:08:45
 */
@Service
@Slf4j
public class SubjectLikedDomainServiceImpl implements SubjectLikedDomainService {

    @Resource
    private SubjectLikedService subjectLikedService;

    @Resource
    private SubjectInfoService subjectInfoService;

    @Resource
    private RedisUtil redisUtil;

    private static final String SUBJECT_LIKED_KEY = "subject.liked";

    private static final String SUBJECT_LIKED_COUNT_KEY = "subject.liked.count";

    private static final String SUBJECT_LIKED_DETAIL_KEY = "subject.liked.detail";


    @Override
    public void add(SubjectLikedBO subjectLikedBO) {
        String likeUserId = subjectLikedBO.getLikeUserId();
        Long subjectId = subjectLikedBO.getSubjectId();
        Integer status = subjectLikedBO.getStatus();
        String hashKey = buildSubjectLikedKey(subjectId.toString(), likeUserId);
        redisUtil.putHash(SUBJECT_LIKED_KEY, hashKey, status);
        String countKey = SUBJECT_LIKED_COUNT_KEY + ":" + subjectId;
        String detailKey = SUBJECT_LIKED_DETAIL_KEY + ":" + subjectId + ":" + likeUserId;
        if (SubjectLikedStatusEnum.LIKED.getCode() == status) {
            redisUtil.increment(countKey, 1);
            redisUtil.set(detailKey, "1");
        } else {
            Integer count = redisUtil.getInt(countKey);
            if (!Objects.nonNull(count) || count <= 0) {
                return;
            }
            redisUtil.increment(countKey, -1);
            redisUtil.del(detailKey);
        }
    }

    private String buildSubjectLikedKey(String subjectId, String userId) {
        return subjectId + ":" + userId;
    }

    @Override
    public Boolean isLiked(String subjectId, String userId) {
        String detailKey = SUBJECT_LIKED_DETAIL_KEY + ":" + subjectId + ":" + userId;
        return redisUtil.exist(detailKey);
    }

    @Override
    public Integer getLikedCount(String subjectId) {
        String countKey = SUBJECT_LIKED_COUNT_KEY + ":" + subjectId;
        Integer count = redisUtil.getInt(countKey);
        if (!Objects.nonNull(count) || count <= 0) {
            return 0;
        }
        return count;
    }

    @Override
    public Boolean update(SubjectLikedBO subjectLikedBO) {
        return null;
    }

    @Override
    public Boolean delete(SubjectLikedBO subjectLikedBO) {
        return null;
    }

    @Override
    public void syncLiked() {
        Map<Object, Object> subjectLikedMap = redisUtil.getHashAndDelete(SUBJECT_LIKED_KEY);
        if (log.isInfoEnabled()) {
            log.info("syncLiked:{}", JSON.toJSONString(subjectLikedMap));
        }
        if(MapUtils.isEmpty(subjectLikedMap)) {
            return;
        }
        List<SubjectLiked> subjectLikedList = new LinkedList<>();
        subjectLikedMap.forEach((key, value) -> {
            SubjectLiked subjectLiked = new SubjectLiked();
            subjectLiked.setSubjectId(Long.valueOf(key.toString().split(":")[0]));
            subjectLiked.setLikeUserId(key.toString().split(":")[1]);
            subjectLiked.setStatus(Integer.valueOf(value.toString()));
            subjectLiked.setIsDeleted(IsDeleteFlagEnum.UN_DELETED.getCode());
            subjectLikedList.add(subjectLiked);
        });
        subjectLikedService.batchInsert(subjectLikedList);
    }

    @Override
    public PageResult<SubjectLikedBO> getSubjectLikedPage(SubjectLikedBO subjectLikedBO) {
        PageResult<SubjectLikedBO> pageResult = new PageResult<>();
        pageResult.setPageNo(subjectLikedBO.getPageNo());
        pageResult.setPageSize(subjectLikedBO.getPageSize());
        int start = (subjectLikedBO.getPageNo() - 1) * subjectLikedBO.getPageSize();
        SubjectLiked subjectLiked = SubjectLikedConverter.INSTANCE.convertBOToEntity(subjectLikedBO);
        subjectLiked.setLikeUserId(LoginUtil.getLoginId());
        int count = subjectLikedService.countByCondition(subjectLiked);
        if (count == 0) {
            return pageResult;
        }
        List<SubjectLiked> subjectLikedList = subjectLikedService.queryPage(subjectLiked, start, subjectLikedBO.getPageSize());
        List<SubjectLikedBO> subjectLikedBOList = SubjectLikedConverter.INSTANCE.convertListInfoToBO(subjectLikedList);
        subjectLikedBOList.forEach(bo -> {
            SubjectInfo subjectInfo = subjectInfoService.queryById(bo.getSubjectId());
            bo.setSubjectName(subjectInfo.getSubjectName());
        });
        pageResult.setRecords(subjectLikedBOList);
        pageResult.setTotal(count);
        return pageResult;
    }

    @Override
    public void syncLikedByMsg(SubjectLikedBO subjectLikedBO) {

    }
}
