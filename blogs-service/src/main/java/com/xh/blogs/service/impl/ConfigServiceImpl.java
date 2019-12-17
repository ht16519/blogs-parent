package com.xh.blogs.service.impl;

import com.xh.blogs.service.IConfigService;
import com.xh.blogs.consts.CommonConst;
import com.xh.blogs.dao.mapper.ConfigMapper;
import com.xh.blogs.domain.po.Config;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletContext;
import java.util.List;

/**
 * @Name SystemServiceImpl
 * @Description
 * @Author wen
 * @Date 2019-06-01
 */
@Service
@Slf4j
public class ConfigServiceImpl implements IConfigService {

    @Autowired
    private ConfigMapper configMapper;
    @Autowired
    private ServletContext servletContext;

    @Override
    public List<Config> getAll(){
        return configMapper.selectByStatus(CommonConst.EFFECTIVE_STATUS);
    }

    @Override
    public void createSystemConfig(){
        log.info("================ 【初始化】START创建系统配置信息 ==================");
        for (Config config : this.getAll()) {
            servletContext.setAttribute(config.getKey(), config.getValue());
            log.info("--------- 加载key:{} <>  value:{}", config.getKey(), config.getValue());
        }
        log.info("================ 【初始化】END创建系统配置信息成功 ==================");
    }



}
