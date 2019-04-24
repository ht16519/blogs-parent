package com.xh.blogs.domain.vo;

import lombok.Data;

import java.util.List;

/**
 * @Name PageResult
 * @Description 分页结果对象
 * @Author wen
 * @Date 2019-03-30
 */
@Data
public class PageResult<T> {

    private long total;         // 总条数

    private long number;        // 当前页

    private List<T> items;      // 当前页数据

    public PageResult() {
    }

    public PageResult(long total, List<T> items) {
        this(items);
        this.total = total;
    }

    public PageResult(List<T> items) {
        this.items = items;
    }

    public PageResult(long total, long number, List<T> items) {
        this(total, items);
        this.number = number;
    }

}