package com.xh.blogs.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Name EMenuNode
 * @Description
 * @Author wen
 * @Date 2019-05-19
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EMenuNode {

    private Integer id;

    private Integer pid;

    private String name;

    private boolean open = true;

    private boolean checked = false;

}
