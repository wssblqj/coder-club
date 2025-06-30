package com.itheima.wx.handler;

import java.util.Map;

public interface WeChatMsgHandler {

    WeChatMsgTypeEnum getType();

    String handle(Map<String, String> msgMap);
}
