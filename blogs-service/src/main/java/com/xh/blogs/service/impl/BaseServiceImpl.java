package com.xh.blogs.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @Name BaseServiceImpl
 * @Description
 * @Author wen
 * @Date 2019-05-09
 */
@Service
public abstract class BaseServiceImpl {

    @Value("${blogs.pageSize}")
    protected int pageSize;

}
