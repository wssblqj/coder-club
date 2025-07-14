package com.itheima.subject.common.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

@Data
public class PageResult<T> implements Serializable {
    private Integer pageNo = 1;

    private Integer pageSize = 20;

    private Integer total;

    private Integer totalPages;

    private List<T> result = Collections.emptyList();

    private Integer start = 1;

    private Integer end = 0;

    public void setRecords(List<T> result) {
        this.result = result;
        if (result.size() % pageSize == 0) {
            setTotal(result.size());
        }
    }

    public void setTotal(int total) {
        this.total = total;
        if (pageSize > 0) {
            this.totalPages = total / pageSize + (total % pageSize == 0 ? 0 : 1);
        } else {
            this.totalPages = 0;
        }
        this.start = (pageSize > 0 ? (pageNo - 1) * pageSize : 0) + 1;
        this.end = start - 1 + this.pageSize * (pageNo > 0 ? 1 : 0);
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

}
