package com.itheima.subject.infra.basic.es;

import lombok.Data;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.SortOrder;

@Data
public class EsSearchRequest {


    /**
     * 查询条件
     */
    private BoolQueryBuilder bq;

    /**
     * 查询字段
     */
    private String[] fields;

    /**
     * 查询起始位置
     */
    private int from;

    /**
     * 查询数量
     */
    private int size;

    /**
     * 需要快照
     */
    private boolean needScroll;

    /**
     * 快照缓存时间
     */
    private Long minutes;

    /**
     * 排序字段
     */
    private String sortName;

    /**
     * 排序方式
     */
    private SortOrder sortOrder;

    /**
     * 高亮builder
     */
    private HighlightBuilder highlightBuilder;

}
