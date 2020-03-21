package com.xh.blogs.domain.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.Collections;
import java.util.List;

/**
 * @Name PageResult
 * @Description 分页结果对象
 * @Author wen
 * @Date 2019-03-30
 */
@Getter
@Setter
public class PageResult<T> {

    private long total;         // 总条数

    private int number;        // 当前页

    private int pages;          //所有页

    private List<T> items;      // 当前页数据

    private PageResult() {
        items = Collections.emptyList();
    }

    private PageResult(long total, int number, List<T> items) {
        this.total = total;
        this.number = number;
        this.items = items;
    }

    private PageResult(long total, int number, int pages, List<T> items) {
        this(total, number, items);
        this.pages = pages;
    }

    public static <T> PageResult<T> createEmpty() {
        return new PageResult<>();
    }

    public static <T> PageResult<T> create(long totalElements, int number, List<T> content) {
        return new PageResult<>(totalElements, number, content);
    }

    public static <T> PageResult<T> create(long totalElements, int number, int totalPages, List<T> content) {
        return new PageResult<>(totalElements, number, totalPages, content);
    }


}