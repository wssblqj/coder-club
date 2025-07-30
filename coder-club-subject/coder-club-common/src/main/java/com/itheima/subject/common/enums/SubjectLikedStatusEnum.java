package com.itheima.subject.common.enums;


import lombok.Getter;

/**
 * 赞的状态枚举类
 */

@Getter
public enum SubjectLikedStatusEnum {

    LIKED(1, "已赞"),
    UN_LIKED(0, "未赞");

    private Integer code;

    private String message;


    SubjectLikedStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public static SubjectLikedStatusEnum getByCode(Integer code) {
        for (SubjectLikedStatusEnum result : SubjectLikedStatusEnum.values()) {
            if (result.code == code) {
                return result;
            }
        }
        return null;
    }

}
