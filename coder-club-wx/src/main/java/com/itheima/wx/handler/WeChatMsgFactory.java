package com.itheima.wx.handler;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class WeChatMsgFactory implements InitializingBean {

    @Resource
    private List<WeChatMsgHandler> weChatMsgHandlers;

    private Map<WeChatMsgTypeEnum, WeChatMsgHandler> handlerMap = new HashMap<>();
    public WeChatMsgHandler getHandler(String type) {
        WeChatMsgTypeEnum msgTypeEnum = WeChatMsgTypeEnum.getByType(type);
        return handlerMap.get(msgTypeEnum);
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        for (WeChatMsgHandler weChatMsgHandler : weChatMsgHandlers) {
            handlerMap.put(weChatMsgHandler.getType(), weChatMsgHandler);
        }
    }
}
