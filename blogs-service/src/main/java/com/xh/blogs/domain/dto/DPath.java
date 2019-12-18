package com.xh.blogs.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Name DPath
 * @Description
 * @Author wen
 * @Date 2019-05-07
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DPath {

    /** 保存物理路径*/
    private String savePath;

    /** 数据库保存路径*/
    private String dbPath;
}
