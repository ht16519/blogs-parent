package com.xh.blogs.dao.base;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @baseMapper
 * //这里实现一个自己的接口,继承通用的mapper，关键点就是这个接口不能被扫描到(不加注解就不会被扫描到)
 * @author DWQin
 *
 * @param <T>
 */
public interface IBaseMapper<T> extends
        Mapper<T>,
        MySqlMapper<T>
		{
}
