package com.xh.blogs.service.impl;

import com.xh.blogs.api.IFavorsService;
import com.xh.blogs.dao.mapper.FavorsMapper;
import com.xh.blogs.domain.po.Favors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @Name FavorsServiceImpl
 * @Description
 * @Author wen
 * @Date 2019-04-25
 */
@Service
public class FavorsServiceImpl implements IFavorsService {

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
}
