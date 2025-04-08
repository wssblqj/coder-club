package com.itheima.subject.common.enums;


import lombok.Getter;

/**
 * 删除状态
 */
@Getter
public enum IsDeleteFlagEnum {
    DELETED(1, "已删除"),
    UN_DELETED(0, "未删除");

    private int code;

    private String desc;

    IsDeleteFlagEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static IsDeleteFlagEnum getByCode(int code) {
        for (IsDeleteFlagEnum flagEnum : IsDeleteFlagEnum.values()) {
            if(flagEnum.code == code) {
                return flagEnum;
            }
        }
        return null;
    }

}
