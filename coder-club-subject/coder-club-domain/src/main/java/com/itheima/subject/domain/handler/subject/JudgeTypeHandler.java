package com.itheima.subject.domain.handler.subject;

import com.itheima.subject.common.enums.IsDeleteFlagEnum;
import com.itheima.subject.common.enums.SubjectInfoTypeEnum;
import com.itheima.subject.domain.convert.JudgeSubjectConverter;
import com.itheima.subject.domain.entity.SubjectAnswerBO;
import com.itheima.subject.domain.entity.SubjectInfoBO;
import com.itheima.subject.domain.entity.SubjectOptionBO;
import com.itheima.subject.infra.basic.entity.SubjectJudge;
import com.itheima.subject.infra.basic.service.SubjectJudgeService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 但选题目的策略类
 */
@Component
public class JudgeTypeHandler implements SubjectTypeHandler{
    @Override
    public SubjectInfoTypeEnum getType() {
        return SubjectInfoTypeEnum.JUDGE;
    }

    @Resource
    private SubjectJudgeService subjectJudgeService;

    @Override
    public void add(SubjectInfoBO subjectInfoBO) {
        List<SubjectJudge> judgeList = new ArrayList<>();
        subjectInfoBO.getOptionList().forEach(option -> {
            SubjectJudge subjectJudge = JudgeSubjectConverter.INSTANCE.convertBoToEntity(option);
            subjectJudge.setSubjectId(subjectInfoBO.getId());
            subjectJudge.setIsDeleted(IsDeleteFlagEnum.UN_DELETED.getCode());
            judgeList.add(subjectJudge);
        });
        subjectJudgeService.batchInsert(judgeList);
    }

    @Override
    public SubjectOptionBO querySubjectInfo(int subjectId) {
        SubjectJudge subjectJudge = subjectJudgeService.queryBySubjectId(Long.valueOf(subjectId));
        SubjectOptionBO subjectOptionBO = new SubjectOptionBO();
        List<SubjectAnswerBO> list = new ArrayList<>();
        SubjectAnswerBO subjectAnswerBO = new SubjectAnswerBO();
        subjectAnswerBO.setIsCorrect(subjectJudge.getIsCorrect());
        list.add(subjectAnswerBO);
        subjectOptionBO.setOptionList(list);
        return subjectOptionBO;
    }
}
