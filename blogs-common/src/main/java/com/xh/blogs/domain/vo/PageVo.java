package com.xh.blogs.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Name PageVo
 * @Description
 * @Author wen
 * @Date 2019-04-03
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageVo {

    protected int pageNum;

    protected int pageSize;

}
