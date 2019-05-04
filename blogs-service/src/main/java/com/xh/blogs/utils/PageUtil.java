package com.xh.blogs.utils;

import com.github.pagehelper.Page;
import com.xh.blogs.domain.vo.PageResult;

import java.util.List;

/**
 * @Name PageUtil
 * @Description
 * @Author wen
 * @Date 2019-05-01
 */
public class PageUtil {

    public static <T> PageResult<T> create(Page<T> page){
        return new PageResult(page.getTotal(), page.getPageNum(), page.getPages(), page.getResult());
    }

    public static <T> PageResult<T> create(Page<T> page, List<T> list){
        return new PageResult(page.getTotal(), page.getPageNum(), page.getPages(), list);
    }

}
