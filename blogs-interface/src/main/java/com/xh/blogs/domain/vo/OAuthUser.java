package com.xh.blogs.domain.vo;

import com.xh.blogs.domain.po.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @Name UserOpenIdVo
 * @Description
 * @Author wen
 * @Date 2019-07-10
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class OAuthUser {

    private String token;

    private User user;

}
