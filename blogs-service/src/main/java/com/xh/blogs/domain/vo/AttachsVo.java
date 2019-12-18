package com.xh.blogs.domain.vo;

import com.xh.blogs.domain.po.Attachs;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Name AttachsVo
 * @Description
 * @Author wen
 * @Date 2019-06-06
 */
@Data
@AllArgsConstructor
public class AttachsVo {

    private Integer id;

    private String name;

    public AttachsVo(Attachs attachs) {
        this.id = attachs.getId();
        this.name = attachs.getName();
    }

    public AttachsVo() {
    }
}
