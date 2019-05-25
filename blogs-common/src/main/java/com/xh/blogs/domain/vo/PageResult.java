package com.xh.blogs.domain.vo;

import lombok.Data;

import java.util.ArrayList;
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

    private int number;        // 当前页

    private int pages;          //所有页

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

    public PageResult(long total, int number, List<T> items) {
        this(total, items);
        this.number = number;
    }

    public PageResult(long total, int number, int pages, List<T> items) {
        this(total, number, items);
        this.pages = pages;
    }

    public static PageResult createNull(){
        return new PageResult(0, 1, 0, new ArrayList<>());
    }

}