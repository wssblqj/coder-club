package com.itheima.wx.handler;

public enum WeChatMsgTypeEnum {

    SUBSCRIBE("event.subscribe", "用户关注"),
    TEXT("text", "文本消息"),
    IMAGE("image", "图片消息"),
    VOICE("voice", "语音消息"),
    VIDEO("video", "视频消息"),
    LOCATION("location", "地理位置消息"),
    LINK("link", "链接消息"),
    FILE("file", "文件消息"),
    UNKNOWN("unknown", "未知消息");

    private String type;
    private String desc;
    WeChatMsgTypeEnum(String type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    public static WeChatMsgTypeEnum getByType(String type) {
        for (WeChatMsgTypeEnum value : WeChatMsgTypeEnum.values()) {
            if (value.type.equals(type)) {
                return value;
            }
        }
        return UNKNOWN;
    }

}
