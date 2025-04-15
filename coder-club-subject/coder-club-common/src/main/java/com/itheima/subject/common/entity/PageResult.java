package com.itheima.subject.common.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

@Data
public class PageResult<T> implements Serializable {
    private Integer pageNo = 1;

    private Integer pageSize = 20;

    private Integer totalRecordsNum;

    private Integer totalPagesNum;

    private List<T> records = Collections.emptyList();

    private Integer start = 1;

    private Integer end = 0;

    public void setRecords(List<T> records) {
        this.records = records;
        if (records.size() % pageSize == 0) {
            setTotalRecoredsNum(records.size());
        }
    }

    public void setTotalRecoredsNum(int size) {
        this.totalRecordsNum = size;
        if (pageSize > 0) {
            this.totalPagesNum = totalRecordsNum / pageSize + (totalRecordsNum % pageSize == 0 ? 0 : 1);
        } else {
            this.totalPagesNum = 0;
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
