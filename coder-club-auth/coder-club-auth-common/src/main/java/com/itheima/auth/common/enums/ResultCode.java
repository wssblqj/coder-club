package com.itheima.auth.common.enums;

import lombok.Data;
import lombok.Getter;

@Getter
public enum ResultCode {
    SUCCESS(200, "成功"),
    FAIL(500, "失败");

    private int code;

    private String desc;

    ResultCode(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static ResultCode getByCode(int code) {
        for (ResultCode resultCode : ResultCode.values()) {
            if(resultCode.code == code) {
                return resultCode;
            }
        }
        return null;
    }

}
