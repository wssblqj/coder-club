package com.itheima.subject.domain.handler.subject;

import com.itheima.subject.common.enums.SubjectInfoTypeEnum;
import com.itheima.subject.domain.convert.JudgeSubjectConverter;
import com.itheima.subject.domain.entity.SubjectInfoBO;
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
            judgeList.add(subjectJudge);
        });
        subjectJudgeService.batchInsert(judgeList);
    }
}
