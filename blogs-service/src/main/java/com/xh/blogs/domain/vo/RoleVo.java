package com.xh.blogs.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.util.List;

/**
 * @Name RoleVo
 * @Description
 * @Author wen
 * @Date 2019-05-20
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleVo {

    private Integer id;

    @NotEmpty(message = "角色名称不能为空")
    @Size(max = 8, min = 2, message = "角色名称由2-8个字符组成")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "角色名称只能是英文字符")
    private String name;

    @Size(max = 255, message = "角色备注不能超过255个字符")
    private String remark;

    List<Integer> menus;
}
