package com.xh.blogs.service;

import com.xh.blogs.domain.po.Article;
import com.xh.blogs.domain.vo.PageResult;
import com.xh.blogs.utils.JsonUtil;
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

    @Test
    public void getByIdWithPageTest(){
        PageResult<Article> page = articleService.getByIdWithPage(1, 1);
        System.err.println(JsonUtil.serialize(page));
    }

    public static void main(String[] args) {
        String s = "9---http://images2015.cnblogs.com/blog/816787/201702/816787-20170215103829269-1581468016.png---http://images2015.cnblogs.com/blog/816787/201702/816787-20170215103829269-1581468016.png---1";
        String s1 = "5---//upload-images.jianshu.io/upload_images/2802381-cc07ccad833e1043.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/381/format/webp---//upload-images.jianshu.io/upload_images/2802381-cc07ccad833e1043.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/381/format/webp---2===6---//upload-images.jianshu.io/upload_images/2802381-b3196d0dba87c926.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/384/format/webp---//upload-images.jianshu.io/upload_images/2802381-b3196d0dba87c926.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/384/format/webp---2===7---//upload-images.jianshu.io/upload_images/2802381-74c0e75da2cadeac.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/611/format/webp---//upload-images.jianshu.io/upload_images/2802381-74c0e75da2cadeac.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/611/format/webp---2===8---//upload-images.jianshu.io/upload_images/2802381-39992ccbb433560e.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/654/format/webp---//upload-images.jianshu.io/upload_images/2802381-39992ccbb";
        System.out.println(s1.length());
//        String[] split = s1.split("===");
//        for (String s2 : split) {
//            System.out.println(s2);
//            for (String s3 : s2.split("---")) {
//                System.err.println(s3);
//            }
//        }


    }


}
