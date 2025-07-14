package com.itheima.subject.common.entity;

import lombok.Data;

/**
 * 分页请求实体
 */
@Data
public class PageInfo {

    private Integer pageNo;

    private Integer pageSize;

    public int getPageNo() {
        if(pageNo == null || pageNo < 1) {
            pageNo = 1;
        }
        return pageNo;
    }

    public int getPageSize() {
        if(pageSize == null || pageSize < 1 || pageSize > Integer.MAX_VALUE) {
            return 20;
        }
        return pageSize;
    }
}
