package com.xh.blogs.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xh.blogs.api.IFavorsService;
import com.xh.blogs.consts.NotifyConst;
import com.xh.blogs.dao.mapper.ArticleMapper;
import com.xh.blogs.dao.mapper.FavorsMapper;
import com.xh.blogs.dao.mapper.NotifyMapper;
import com.xh.blogs.domain.po.Article;
import com.xh.blogs.domain.po.Favors;
import com.xh.blogs.domain.po.Notify;
import com.xh.blogs.domain.vo.PageResult;
import com.xh.blogs.utils.ArticleUtil;
import com.xh.blogs.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @Name FavorsServiceImpl
 * @Description
 * @Author wen
 * @Date 2019-04-25
 */
@Service
public class FavorsServiceImpl extends BaseServiceImpl implements IFavorsService {

    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private NotifyMapper notifyMapper;
    @Autowired
    private FavorsMapper favorsMapper;

    @Override
    @Transactional
    public int addFavor(int userId, int ownId, int articleId) {
        Favors favors = new Favors();
        favors.setArticleId(articleId);
        favors.setCreateTime(new Date());
        favors.setOwnId(ownId);
        try {
            int res = favorsMapper.insertSelective(favors);
            if(res > 0){
                //2.文章收藏量增加
                articleMapper.addFavors(articleId);
                //3.发送通知
                Notify notify = new Notify();
                notify.setCreateTime(new Date());
                notify.setEvent(NotifyConst.EVENT_FAVORS);
                notify.setFromId(ownId);
                notify.setToId(userId);
                res = notifyMapper.insertSelective(notify);
            }
            return res;
        } catch (DataIntegrityViolationException e) {
            return -1;
        }
    }

    @Override
    public PageResult<Article> getByUserIdWithPage(int userId, int number) {
        Page<Article> page = PageHelper.startPage(number, pageSize);
        favorsMapper.selectByUserIdWithPage(userId);
        return PageUtil.create(page, ArticleUtil.getArticles(page.getResult()));
    }

    @Override
    @Transactional
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
