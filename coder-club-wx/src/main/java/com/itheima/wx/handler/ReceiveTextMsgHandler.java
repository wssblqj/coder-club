package com.itheima.wx.handler;


import com.itheima.wx.redis.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class ReceiveTextMsgHandler implements WeChatMsgHandler{


    private static final String KEY_WORD = "验证码";

    private static final String Login_PREFIX = "loginCode";

    @Resource
    private RedisUtil  redisUtil;
    @Override
    public WeChatMsgTypeEnum getType() {
        return WeChatMsgTypeEnum.TEXT;
    }

    @Override
    public String handle(Map<String, String> msgMap) {
        log.info("接收到文本消息");
        String content = msgMap.get("Content");
        if(!KEY_WORD.equals(content)) {
            return "";
        }
        String fromUserName = msgMap.get("FromUserName");
        String toUserName = msgMap.get("ToUserName");
        Random random = new Random();
        int num = random.nextInt(1000);
        String numKey = redisUtil.buildKey(Login_PREFIX, String.valueOf(num));
        redisUtil.setNx(numKey, fromUserName, 5L, TimeUnit.MINUTES);
        String numContent = "您的验证码是：" + num + "五分钟内有效";
        String replyContent = "<xml>\n" +
                "  <ToUserName><![CDATA["+fromUserName+"]]></ToUserName>\n" +
                "  <FromUserName><![CDATA["+ toUserName+"]]></FromUserName>\n" +
                "  <CreateTime>12345678</CreateTime>\n" +
                "  <MsgType><![CDATA[text]]></MsgType>\n" +
                "  <Content><![CDATA["+  numContent +"]]></Content>\n" +
                "</xml>";

        return replyContent;
    }
}
