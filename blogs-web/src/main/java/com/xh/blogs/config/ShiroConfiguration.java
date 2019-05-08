package com.xh.blogs.config;

import com.xh.blogs.auth.AuthRealm;
import com.xh.blogs.auth.CredentialMatcher;
import com.xh.blogs.consts.ConfigConst;
import com.xh.blogs.consts.RequestUrl;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;

@Configuration
public class ShiroConfiguration {

	private static final String ANON = "anon";

	private static final String USER = "user";

	private static final String STATIC_RESOURCES = "/static/**";

	private static final String ALL_RESOURCES = "/**";



	/**
	 * @Name shiroFilterFactoryBean
	 * @Description 配置过滤规则 
	 * @Date 2019年1月11日
	 * @Author deng.wenqin
	 * @param manager
	 * @return
	 */
	@Bean("shiroFilter")
	public ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier("securityManager") SecurityManager manager) {
		ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
		bean.setSecurityManager(manager);
		//定义登录url
		bean.setLoginUrl(RequestUrl.LOGIN_URL);
		//定义登陆成功url
		bean.setSuccessUrl(RequestUrl.INDEX_URL);
		//定义没有权限访问的url
		bean.setUnauthorizedUrl(RequestUrl.INDEX_URL);
		//定义请求拦截规则
		LinkedHashMap<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
		filterChainDefinitionMap.put(RequestUrl.INDEX_URLS, ANON);
		filterChainDefinitionMap.put(RequestUrl.LOGIN_URL, ANON);
		filterChainDefinitionMap.put(RequestUrl.REG_URL, ANON);
		filterChainDefinitionMap.put(RequestUrl.ANON_API, ANON);
		filterChainDefinitionMap.put(RequestUrl.BLOGGER_DETAILS, ANON);
		filterChainDefinitionMap.put(RequestUrl.ARTICLE_DETAILS, ANON);
		filterChainDefinitionMap.put(STATIC_RESOURCES, ANON);
		filterChainDefinitionMap.put(ConfigConst.CONFIG_ACCESSORY_PATH, ANON);
		filterChainDefinitionMap.put(ALL_RESOURCES, USER);
		bean.setFilterChainDefinitionMap(filterChainDefinitionMap);
		return bean;
	}
	
	/**
	 * @Name securityManager 
	 * @Description 定义安全管理
	 * @Date 2019年1月11日
	 * @Author deng.wenqin
	 * @param authRealm
	 * @return
	 */
	@Bean("securityManager")
	public SecurityManager securityManager(@Qualifier("authRealm") AuthRealm authRealm) {
		DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
		manager.setRealm(authRealm);
		return manager;
	}
	
	/**
	 * @Name authRealm
	 * @Description 通过@Qualifier获取上下文中的 credentialMatcher实例
	 * @Date 2019年1月11日
	 * @Author deng.wenqin
	 * @return
	 */
	@Bean("authRealm")
	public AuthRealm authRealm(@Qualifier("credentialMatcher") CredentialMatcher matcher) {
		AuthRealm authRealm = new AuthRealm();
		//开启相关认证缓存
		authRealm.setCacheManager(new MemoryConstrainedCacheManager());
		authRealm.setCredentialsMatcher(matcher);
		return authRealm;
	}
	
	/**
	 * @Name credentialMatcher
	 * @Description 将credentialMatcher存入context上下文
	 * @Date 2019年1月11日
	 * @Author deng.wenqin
	 * @return
	 */
	@Bean("credentialMatcher")
	public CredentialMatcher credentialMatcher() {
		return new CredentialMatcher();
	}
	
	/**
	 * @Name authorizationAttributeSourceAdvisor
	 * @Description 配置shiro与spring之间的关联1
	 * @Date 2019年1月11日
	 * @Author deng.wenqin
	 * @param manager
	 * @return
	 */
	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(@Qualifier("securityManager") SecurityManager manager) {
		AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
		advisor.setSecurityManager(manager);
		return advisor;
	}
	
	/**
	 * @Name defaultAdvisorAutoProxyCreator
	 * @Description 配置shiro与spring之间的关联2
	 * @Date 2019年1月12日
	 * @Author deng.wenqin
	 * @return
	 */
	@Bean
	public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
		DefaultAdvisorAutoProxyCreator creator = new DefaultAdvisorAutoProxyCreator();
		creator.setProxyTargetClass(true);
		return creator;
	}
}
