package com.xh.blogs.api;

import com.xh.blogs.domain.po.Config;

import java.util.List;

/**
 * @Name ISystemService
 * @Description
 * @Author wen
 * @Date 2019-06-01
 */
public interface IConfigService {

    /**
    * @Name getAll
    * @Description 获取所有配置
    * @Author wen
    * @Date 2019/6/1
    * @param
    * @return java.util.List<com.xh.blogs.domain.po.Config>
    */
    List<Config> getAll();

    /**
    * @Name createSystemConfig
    * @Description 创建系统配置
    * @Author wen
    * @Date 2019/6/1
    * @param
    * @return void
    */
    void createSystemConfig();
}
