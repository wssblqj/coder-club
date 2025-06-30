package com.itheima.wx.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Slf4j
public class SubscribeMsgHandler implements WeChatMsgHandler{

    @Override
    public WeChatMsgTypeEnum getType() {
        return WeChatMsgTypeEnum.SUBSCRIBE;
    }

    @Override
    public String handle(Map<String, String> msgMap) {
        log.info("用户关注公众号");
        String fromUserName = msgMap.get("FromUserName");
        String toUserName = msgMap.get("ToUserName");
        String subscribeContent = "感谢您的关注，我是一名小白程序员，一起加油！！！";
        String content = "<xml>\n" +
                "  <ToUserName><![CDATA["+fromUserName+"]]></ToUserName>\n" +
                "  <FromUserName><![CDATA["+ toUserName+"]]></FromUserName>\n" +
                "  <CreateTime>12345678</CreateTime>\n" +
                "  <MsgType><![CDATA[text]]></MsgType>\n" +
                "  <Content><![CDATA["+ subscribeContent +"]]></Content>\n" +
                "</xml>";
        return content;
    }
}
