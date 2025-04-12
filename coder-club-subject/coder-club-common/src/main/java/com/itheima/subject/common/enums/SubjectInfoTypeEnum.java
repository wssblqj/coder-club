package com.itheima.subject.common.enums;


import lombok.Getter;

/**
 * 题目类型状态
 */
@Getter
public enum SubjectInfoTypeEnum {
    RADIO(1, "单选"),
    MULTIPLE(2, "多选"),
    JUDGE(3, "判断"),
    BRIEF(4, "简答");

    private int code;

    private String desc;

    SubjectInfoTypeEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static SubjectInfoTypeEnum getByCode(int code) {
        for (SubjectInfoTypeEnum flagEnum : SubjectInfoTypeEnum.values()) {
            if(flagEnum.code == code) {
                return flagEnum;
            }
        }
        return null;
    }

}
