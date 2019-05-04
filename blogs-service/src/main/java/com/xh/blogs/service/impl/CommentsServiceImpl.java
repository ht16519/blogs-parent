package com.xh.blogs.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xh.blogs.api.ICommentsService;
import com.xh.blogs.consts.CommonConst;
import com.xh.blogs.consts.NotifyConst;
import com.xh.blogs.dao.mapper.ArticleMapper;
import com.xh.blogs.dao.mapper.CommentsMapper;
import com.xh.blogs.dao.mapper.NotifyMapper;
import com.xh.blogs.domain.entity.EComments;
import com.xh.blogs.domain.po.Article;
import com.xh.blogs.domain.po.Comments;
import com.xh.blogs.domain.po.Notify;
import com.xh.blogs.domain.vo.CommentsVo;
import com.xh.blogs.domain.vo.PageResult;
import com.xh.blogs.enums.EmError;
import com.xh.blogs.exception.BusinessException;
import com.xh.blogs.utils.BeanValidator;
import com.xh.blogs.utils.PageUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @Name CommentsServiceImpl
 * @Description
 * @Author wen
 * @Date 2019-04-27
 */
@Service
public class CommentsServiceImpl implements ICommentsService {

    @Autowired
    private CommentsMapper commentsMapper;
    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private NotifyMapper notifyMapper;

    @Override
    @Transactional
    public int add(CommentsVo commentsVo) throws BusinessException {
        //1.参数校验
        BeanValidator.check(commentsVo);
        //2.检查文章是否存在
        Article article = articleMapper.selectByPrimaryKey(commentsVo.getArticleId());
        if(article == null){
            throw new BusinessException(EmError.ARTICLE_IS_NOT_EXIST);
        }
        Comments comments = new Comments();
        BeanUtils.copyProperties(commentsVo, comments);
        comments.setCreateTime(new Date());
        int res = commentsMapper.insertSelective(comments);
        if(res > 0 && comments.getPid() != null && comments.getPid() <= 0){
            //3.文章评论数+1
            articleMapper.addComments(article.getId());
            //4.发送文章被评论通知
            Notify notify = new Notify();
            notify.setCreateTime(new Date());
            notify.setEvent(NotifyConst.EVENT_COMMENTS);
            notify.setArticleId(article.getId());
            notify.setFromId(commentsVo.getUserId());
            notify.setToId(article.getAuthorId());
            res = notifyMapper.insertSelective(notify);
        }
        return res;
    }

    @Override
    public PageResult<EComments> getByArticleIdWithPage(int articleId, int number) {
        Page<EComments> page = PageHelper.startPage(number, CommonConst.PAGE_SIZE);
        commentsMapper.selectByArticleId(articleId, CommonConst.EFFECTIVE_STATUS);
        return PageUtil.create(page, this.getItems(page.getResult(), articleId));
    }

    private List<EComments> getItems(List<EComments> items, int articleId) {
        List<EComments> commentlist = commentsMapper.selectSublistByArticleId(articleId, CommonConst.EFFECTIVE_STATUS);
        Map<Integer, List<EComments>> map = new HashMap<>();
        List<EComments> list;
        for (EComments comments : commentlist) {
            Integer pid = comments.getPid();
            if ((list = map.get(pid)) == null) {
                list = new ArrayList<>();
                map.put(pid, list);
            }
//            if (list.size() < ConfigConst.COMMENTS_SUBLIST_SIZE) {
                list.add(comments);
//            }
        }
        for (EComments item : items) {
            item.setChilds(map.get(item.getId()));
        }
        return items;
    }

}
