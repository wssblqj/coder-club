package com.itheima.subject.domain.handler.subject;

import com.itheima.subject.common.enums.SubjectInfoTypeEnum;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 题目类型处理器工厂
 */
@Component
public class SubjectTypeHandlerFactory implements InitializingBean {

    @Resource
    private List<SubjectTypeHandler> handlers;

    private Map<SubjectInfoTypeEnum, SubjectTypeHandler> handlerMap = new HashMap<>();

    public SubjectTypeHandler getHandler(int subjectType) {
        SubjectInfoTypeEnum subjectInfoTypeEnum = SubjectInfoTypeEnum.getByCode(subjectType);
        return handlerMap.get(subjectInfoTypeEnum);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        handlers.forEach(handler -> handlerMap.put(handler.getType(), handler));
    }
}
