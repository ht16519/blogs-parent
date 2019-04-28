package com.xh.blogs.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xh.blogs.api.ICommentsService;
import com.xh.blogs.consts.CommonConst;
import com.xh.blogs.consts.NotifyConst;
import com.xh.blogs.dao.mapper.ArticleMapper;
import com.xh.blogs.dao.mapper.CommentsMapper;
import com.xh.blogs.dao.mapper.CommentsSublistMapper;
import com.xh.blogs.dao.mapper.NotifyMapper;
import com.xh.blogs.domain.po.Article;
import com.xh.blogs.domain.po.Comments;
import com.xh.blogs.domain.po.CommentsSublist;
import com.xh.blogs.domain.po.Notify;
import com.xh.blogs.domain.vo.CommentsVo;
import com.xh.blogs.domain.vo.PageResult;
import com.xh.blogs.enums.EmError;
import com.xh.blogs.exception.BusinessException;
import com.xh.blogs.utils.BeanValidator;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

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
    private CommentsSublistMapper commentsSublistMapper;
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
        //3.判断是评论还是回复
        int res;
        Integer pid = commentsVo.getPid();
        if(pid == null || pid <= 0){    //评论
            Comments comments = new Comments();
            BeanUtils.copyProperties(commentsVo, comments);
            comments.setContent(commentsVo.getContent());
            comments.setCreateTime(new Date());
            res = commentsMapper.insertSelective(comments);
            res++;
        }else {     //回复
            CommentsSublist sublist = new CommentsSublist();
            BeanUtils.copyProperties(commentsVo, sublist);
            sublist.setId(pid);
            sublist.setCreateTime(new Date());
            res = commentsSublistMapper.insertSelective(sublist);
        }
        if(res > 1){
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
    public PageResult<Comments> getByArticleIdWithPage(int articleId, int number) {
        Page<Comments> page = PageHelper.startPage(number, CommonConst.PAGE_SIZE);
        commentsMapper.selectByArticleId(articleId, CommonConst.EFFECTIVE_STATUS);
        //TODO 实现子评论检索
        return new PageResult(page.getTotal(), page.getResult());
    }

    /**
    * @Name getComments
    * @Description 获取完整的评论
    * @Author wen
    * @Date 2019/4/28
    * @param items
    * @return java.util.List<com.xh.blogs.domain.po.Comments> 
    */
    private List<Comments> getCompleteComments(List<Comments> items) {

        for (Comments item : items) {
            
        }
        return items;
    }

}
