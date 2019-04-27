package com.xh.blogs.service.impl;

import com.xh.blogs.api.ICommentsService;
import com.xh.blogs.dao.mapper.CommentsMapper;
import com.xh.blogs.domain.po.Comments;
import com.xh.blogs.domain.vo.CommentsVo;
import com.xh.blogs.domain.vo.PageResult;
import com.xh.blogs.exception.BusinessException;
import com.xh.blogs.utils.BeanValidator;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

import java.util.Date;

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

    @Override
    public int add(CommentsVo commentsVo) throws BusinessException {
        //1.参数校验
        BeanValidator.check(commentsVo);
        //2.组合数据
        Comments comments = new Comments();
        BeanUtils.copyProperties(commentsVo, comments);
        comments.setContent(commentsVo.getContent());
        comments.setCreateTime(new Date());
        return commentsMapper.insertSelective(comments);
    }

    @Override
    public PageResult<Comments> getByArticleIdWithPage(int articleId) {
        //TODO 分页查询评论
        return new PageResult();
    }
}
