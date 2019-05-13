package com.xh.blogs.service;

import com.xh.blogs.api.IArticleService;
import com.xh.blogs.domain.po.Article;
import com.xh.blogs.domain.vo.PageResult;
import com.xh.blogs.utils.JsonUtil;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Name IArticleServiceTest
 * @Description
 * @Author wen
 * @Date 2019-04-23
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class IArticleServiceTest {

    @Autowired
    private IArticleService articleService;

    @Ignore
    @Test
    public void getByIdWithPageTest(){
        PageResult<Article> page = articleService.getByIdWithPage(1, 1);
        System.err.println(JsonUtil.serialize(page));
    }


}
