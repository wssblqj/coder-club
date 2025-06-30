package com.itheima.wx.controller;

import com.itheima.wx.handler.WeChatMsgFactory;
import com.itheima.wx.handler.WeChatMsgHandler;
import com.itheima.wx.utils.MessageUtil;
import com.itheima.wx.utils.SHA1;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

@RestController
@RequestMapping("/callback")
@Slf4j
public class CallBackController {

    private static final String TOKEN = "itheima";

    @Resource
    private WeChatMsgFactory weChatMsgFactory;

    @RequestMapping("/test")
    public String test() {
        return "success";
    }

    /**
     * 微信公众号回调接口
     * @param signature
     * @param timestamp
     * @param nonce
     * @param echostr
     * @return
     */
    @GetMapping("/wx")
    public String callback(@RequestParam(name="signature") String signature,
                           @RequestParam(name="timestamp") String timestamp,
                           @RequestParam(name="nonce") String nonce,
                           @RequestParam(name="echostr") String echostr) {
        log.info("微信回调参数:{},{},{},{}", signature, timestamp, nonce, echostr);
        String shaStr = SHA1.getSHA1(TOKEN, timestamp, nonce, "");
        if (shaStr.equals(signature)) {
            return echostr;
        } else {
            return "error";
        }
    }

    @PostMapping(value = "/wx", produces = "application/xml;charset=UTF-8")
    public String callback(@RequestBody String xml,
                           @RequestParam(name="signature") String signature,
                           @RequestParam(name="timestamp") String timestamp,
                           @RequestParam(name="nonce") String nonce,
                           @RequestParam(value = "msg_signature", required = false) String msgSignature) {
        log.info("微信回调参数:{},{},{},{}", xml, signature, timestamp, nonce);
        Map<String, String> msgMap = MessageUtil.parseXml(xml);
        String toUserName = msgMap.get("ToUserName");
        String fromUserName = msgMap.get("FromUserName");
        String msgType = msgMap.get("MsgType");
        String event = msgMap.get("Event") == null ? "" : msgMap.get("Event");
        String eventKey = msgType;
        if(!StringUtils.isBlank(event)) {
            eventKey = msgType + "." + event;
        }
        WeChatMsgHandler handler = weChatMsgFactory.getHandler(eventKey);
        if(handler == null) {
            return "";
        }
        String replyContent = handler.handle(msgMap);
        return replyContent;
    }

//    public String buildKey(String... strObjs) {
//        ret
//
//    }
}
