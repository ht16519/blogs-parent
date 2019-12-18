package com.xh.blogs.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @Name ApplicationContextHelper
 * @Description 获取上下文的类 
 * @Date 2019年1月24日
 * @Author wen
 */
@Component("applicationContextHelper")
public class ApplicationContextHelper implements ApplicationContextAware{

	private static ApplicationContext applicationContext;
	
	@Override
	public void setApplicationContext(ApplicationContext context) throws BeansException {
		applicationContext = context;
	}

	/**
	 * @Name popBean
	 * @Description 获取上下文的类
	 * @Date 2019年1月24日
	 * @Author wen
	 * @param clazz
	 * @return
	 */
	public static <T> T popBean(Class<T> clazz) {
		if(applicationContext == null) {
			return null;
		}
		return applicationContext.getBean(clazz);
	}
	
	/**
	 * @Name popBean
	 * @Description 获取上下文的类
	 * @Date 2019年1月24日
	 * @Author wen
	 * @param name
	 * @param clazz
	 * @return
	 */
	public static <T> T popBean(String name, Class<T> clazz) {
		if(applicationContext == null) {
			return null;
		}
		return applicationContext.getBean(name, clazz);
	}
	
}
