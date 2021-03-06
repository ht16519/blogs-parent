package com.xh.blogs.config;

import com.xh.blogs.auth.AuthRealm;
import com.xh.blogs.auth.CredentialMatcher;
import com.xh.blogs.consts.RequestUrl;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;

@Configuration
public class ShiroConfig {

	@Value("${blogs.accessory.path}")
	private String accessoryPath;

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
		filterChainDefinitionMap.put(RequestUrl.SYSTEM_ROOT, ANON);
		filterChainDefinitionMap.put(RequestUrl.INDEX_URLS, ANON);
		filterChainDefinitionMap.put(RequestUrl.LOGIN_URLS, ANON);
		filterChainDefinitionMap.put(RequestUrl.REG_URL, ANON);
		filterChainDefinitionMap.put(RequestUrl.ANON_API, ANON);
		filterChainDefinitionMap.put(RequestUrl.DRUID_MONITORING_CENTER, ANON);
		filterChainDefinitionMap.put(RequestUrl.BLOGGER_DETAILS, ANON);
		filterChainDefinitionMap.put(RequestUrl.BLOG_AFFICHES, ANON);
		filterChainDefinitionMap.put(RequestUrl.BLOG_TEST, ANON);
		filterChainDefinitionMap.put(RequestUrl.ARTICLE_DETAILS, ANON);
		filterChainDefinitionMap.put(STATIC_RESOURCES, ANON);
		filterChainDefinitionMap.put(accessoryPath, ANON);
		filterChainDefinitionMap.put(ALL_RESOURCES, USER);
		bean.setFilterChainDefinitionMap(filterChainDefinitionMap);
		return bean;
	}

	/**
	 * @Name securityManager 
	 * @Description 定义安全管理
	 * @Date 2019年1月11日
	 * @Author deng.wenqin
	 * @return
	 */
	@Bean
	public SecurityManager securityManager(AuthRealm authRealm, CookieRememberMeManager rememberMeManager, SessionManager sessionManager) {
		DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
		manager.setRealm(authRealm);
		manager.setRememberMeManager(rememberMeManager);
		manager.setSessionManager(sessionManager);
		return manager;
	}

	/**
	 * @Name authRealm
	 * @Description 通过@Qualifier获取上下文中的 credentialMatcher实例
	 * @Date 2019年1月11日
	 * @Author deng.wenqin
	 * @return
	 */
	@Bean
	public AuthRealm authRealm(@Qualifier("credentialMatcher") CredentialMatcher matcher) {
		AuthRealm authRealm = new AuthRealm();
		//开启相关认证缓存
		authRealm.setCacheManager(new MemoryConstrainedCacheManager());
		authRealm.setCredentialsMatcher(matcher);
		return authRealm;
	}

	/**
	 * session管理器(单机环境)
	 */
	@Bean
	public DefaultWebSessionManager defaultWebSessionManager() {
		DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
		sessionManager.setSessionIdUrlRewritingEnabled(false);
		sessionManager.setSessionValidationInterval(5 * 60 * 1000);		//5分钟
		return sessionManager;
	}

	/**
	 * rememberMe管理器, cipherKey生成见{@code Base64Test.java}
	 */
	@Bean
	public CookieRememberMeManager rememberMeManager() {
		CookieRememberMeManager manager = new CookieRememberMeManager();
		manager.setCipherKey(Base64.decode("Z3VucwAAAAAAAAAAAAAAAA=="));
		manager.getCookie().setMaxAge(7 * 24 * 60 * 60); //记住密码Cookie期限（7天）
		return manager;
	}

	/**
	 * @Name credentialMatcher
	 * @Description 将credentialMatcher存入context上下文
	 * @Date 2019年1月11日
	 * @Author deng.wenqin
	 * @return
	 */
	@Bean
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
