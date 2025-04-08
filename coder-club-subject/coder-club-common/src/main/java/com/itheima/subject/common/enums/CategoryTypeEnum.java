package com.itheima.subject.common.enums;


import lombok.Getter;

/**
 * 删除状态
 */
@Getter
public enum CategoryTypeEnum {
    PRIMARY(1, "岗位大类"),
    SECOND(0, "二级分类");

    private int code;

    private String desc;

    CategoryTypeEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static CategoryTypeEnum getByCode(int code) {
        for (CategoryTypeEnum flagEnum : CategoryTypeEnum.values()) {
            if(flagEnum.code == code) {
                return flagEnum;
            }
        }
        return null;
    }

}
