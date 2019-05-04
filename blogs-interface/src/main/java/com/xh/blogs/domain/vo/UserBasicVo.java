package com.xh.blogs.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.*;

/**
 * @Name UserBasicVo
 * @Description
 * @Author wen
 * @Date 2019-05-04
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserBasicVo {

    private Integer id;

    @NotEmpty(message = "昵称不能为空")
    @Size(max = 9, min = 1, message = "昵称长度应在1到9位之间")
    private String nickName;

    @NotNull(message = "性别不能为空")
    @Range(min = 0, max = 1, message = "性别参数只能为1或0")
    private Integer sex;

    @Size(max = 128, message = "个性签名不能超过128个字符")
    private String signature;
}
