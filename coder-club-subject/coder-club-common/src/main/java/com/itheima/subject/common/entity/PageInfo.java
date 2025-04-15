package com.itheima.subject.common.entity;

/**
 * 分页请求实体
 */
public class PageInfo {

    private Integer pageNo;

    private Integer pageSize;

    public int getPageNo() {
        if(pageNo == null || pageNo < 1) {
            pageNo = 1;
        }
        return pageNo;
    }
    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }
    public int getPageSize() {
        if(pageSize == null || pageSize < 1 || pageSize > Integer.MAX_VALUE) {
            return 20;
        }
        return pageSize;
    }
}
