package com.xh.blogs.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xh.blogs.api.IFavorsService;
import com.xh.blogs.consts.CommonConst;
import com.xh.blogs.dao.mapper.ArticleMapper;
import com.xh.blogs.dao.mapper.FavorsMapper;
import com.xh.blogs.domain.po.Article;
import com.xh.blogs.domain.po.Favors;
import com.xh.blogs.domain.vo.PageResult;
import com.xh.blogs.utils.ArticleUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Name FavorsServiceImpl
 * @Description
 * @Author wen
 * @Date 2019-04-25
 */
@Service
public class FavorsServiceImpl implements IFavorsService {

    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private FavorsMapper favorsMapper;

    @Override
    public int addFavor(int ownId, int articleId) {
        Favors favors = new Favors();
        favors.setArticleId(articleId);
        favors.setCreateTime(new Date());
        favors.setOwnId(ownId);
        try {
            return favorsMapper.insertSelective(favors);
        } catch (DataIntegrityViolationException e) {
            return -1;
        }
    }

    @Override
    public PageResult<Article> getByUserIdWithPage(int userId, int number) {
        Page<Article> page = PageHelper.startPage(number, CommonConst.PAGE_SIZE);
        favorsMapper.selectByUserIdWithPage(userId);
        return new PageResult<>(page.getTotal(), ArticleUtil.getArticles(page.getResult()));
    }

    @Override
    public int unfavor(int id, int userId) {
        //1.取消收藏
        Favors favors = new Favors();
        favors.setOwnId(userId);
        favors.setArticleId(id);
        int res = favorsMapper.delete(favors);
        //2.文章收藏数-1
        if(res > 0){
            articleMapper.minusFavors(id);
        }
        return res;
    }
}
