package com.xh.blogs.service;

import com.xh.blogs.dao.mapper.UserMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Name UserServiceImplTest
 * @Description
 * @Author wen
 * @Date 2019-07-19
 */
public class UserServiceImplTest extends IArticleServiceTest {

    @Autowired
    private UserMapper userMapper;
    @Test
    public void checkIsAccess() {
        String password = userMapper.selectPasswordByUsername("1000000824935174");
        System.err.println(password);
    }
}
