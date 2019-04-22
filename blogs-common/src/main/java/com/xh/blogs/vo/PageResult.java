package com.xh.blogs.vo;

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

    private long total;          // 总条数
    private List<T> items;      // 当前页数据

    public PageResult() {
    }

    public PageResult(long total, List<T> items) {
        this.total = total;
        this.items = items;
    }

    public PageResult(List<T> items) {
        this.total = total;
        this.items = items;
    }


}