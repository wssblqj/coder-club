package com.itheima.auth.common.enums;

import lombok.Getter;

/**
 * 删除状态
 */
@Getter
public enum AuthUserStatusEnum {
    OPEN(1, "启用"),
    COLSE(0, "禁用");

    private int code;

    private String desc;

    AuthUserStatusEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static AuthUserStatusEnum getByCode(int code) {
        for (AuthUserStatusEnum flagEnum : AuthUserStatusEnum.values()) {
            if(flagEnum.code == code) {
                return flagEnum;
            }
        }
        return null;
    }

}
