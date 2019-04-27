package com.xh.blogs.api;

/**
 * @Name IFavorsService
 * @Description
 * @Author wen
 * @Date 2019-04-25
 */
public interface IFavorsService {

    /**
    * @Name addFavor
    * @Description 新增文章收藏
    * @Author wen
    * @Date 2019/4/27
    * @param ownId
    * @param articleId
    * @return int
    */
    int addFavor(int ownId, int articleId);

}
