package com.xh.blogs.config;

import com.xh.blogs.auth.AuthRealm;
import com.xh.blogs.auth.CredentialMatcher;
import com.xh.blogs.config.property.ShiroRedisProperties;
import com.xh.blogs.consts.RequestUrl;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.MethodInvokingFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.annotation.Order;

import java.util.LinkedHashMap;

/**
 * Shiro配置类
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @website https://www.zhyd.me
 * @date 2018/4/24 14:37
 * @since 1.0
 */
@Configuration
@Order(1)
public class ShiroConfig2 {

    @Value("${blogs.accessory.path}")
    private String accessoryPath;

    private static final String ANON = "anon";

    private static final String USER = "user";

    private static final String STATIC_RESOURCES = "/static/**";

    private static final String ALL_RESOURCES = "/**";

    @Autowired
    private ShiroRedisProperties shiroRedisProperties;

    @Bean(name = "lifecycleBeanPostProcessor")
    public static LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    /**
     * 修复UnavailableSecurityManagerException（详见issues#IK7C3）
     * @return
     */
    @Bean
    public MethodInvokingFactoryBean methodInvokingFactoryBean() {
        MethodInvokingFactoryBean bean = new MethodInvokingFactoryBean();
        bean.setStaticMethod("org.apache.shiro.SecurityUtils.setSecurityManager");
        bean.setArguments(securityManager());
        return bean;
    }

    /**
     * @Name shiroFilterFactoryBean
     * @Description 配置过滤规则
     * @Date 2019年1月11日
     * @Author deng.wenqin
     * @return
     */
    @Bean("shiroFilter")
    public ShiroFilterFactoryBean shiroFilterFactoryBean() {
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        bean.setSecurityManager(securityManager());
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

    @Bean
    @DependsOn("lifecycleBeanPostProcessor")
    public DefaultAdvisorAutoProxyCreator lifecycleBeanPostProcessor() {
        DefaultAdvisorAutoProxyCreator creator = new DefaultAdvisorAutoProxyCreator();
        creator.setProxyTargetClass(true);
        return creator;
    }

    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // 设置realm.
        securityManager.setRealm(authRealm());
        //开启缓存
        securityManager.setCacheManager(redisCacheManager());
        // 自定义session管理 使用redis
        securityManager.setSessionManager(sessionManager());
        // 注入记住我管理器
        securityManager.setRememberMeManager(rememberMeManager());
        return securityManager;
    }

    /**
     * @Name authRealm
     * @Description 通过@Qualifier获取上下文中的 credentialMatcher实例
     * @Date 2019年1月11日
     * @Author deng.wenqin
     * @return
     */
    @Bean
    public AuthRealm authRealm() {
        AuthRealm authRealm = new AuthRealm();
        authRealm.setCredentialsMatcher(credentialMatcher());
        return authRealm;
    }

    /**
     * 凭证匹配器
     * （由于我们的密码校验交给Shiro的SimpleAuthenticationInfo进行处理了
     * 所以我们需要修改下doGetAuthenticationInfo中的代码;
     * ）
     */
    @Bean
    public CredentialMatcher credentialMatcher() {
        return new CredentialMatcher();
    }

    /**
     * 开启shiro aop注解支持.
     * 使用代理方式;所以需要开启代码支持;
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor() {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager());
        return authorizationAttributeSourceAdvisor;
    }

    /**
     * 配置shiro redisManager
     * 使用的是shiro-redis开源插件
     *
     * @return
     */
    public RedisManager redisManager() {
        RedisManager redisManager = new RedisManager();
        redisManager.setHost(shiroRedisProperties.getHost());
        redisManager.setPort(shiroRedisProperties.getPort());
        redisManager.setDatabase(shiroRedisProperties.getDatabase());
//        redisManager.setExpire(shiroRedisProperties.getExpire());
        redisManager.setTimeout(shiroRedisProperties.getTimeout().getNano() * 1000);
        redisManager.setPassword(shiroRedisProperties.getPassword());
        return redisManager;
    }

    /**
     * cacheManager 缓存 redis实现
     * 使用的是shiro-redis开源插件
     *
     * @return
     */
    @Bean
    public RedisCacheManager redisCacheManager() {
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setRedisManager(redisManager());
        return redisCacheManager;
    }


    /**
     * RedisSessionDAO shiro sessionDao层的实现 通过redis
     * 使用的是shiro-redis开源插件
     */
    @Bean
    public RedisSessionDAO redisSessionDAO() {
        RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
        redisSessionDAO.setRedisManager(redisManager());
        return redisSessionDAO;
    }

    /**
     * shiro session的管理
     */
    @Bean
    public DefaultWebSessionManager sessionManager() {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setGlobalSessionTimeout(shiroRedisProperties.getExpire() * 1000L);
        sessionManager.setSessionDAO(redisSessionDAO());
        return sessionManager;
    }

    /**
     * cookie对象;
     *
     * @return
     */
    public SimpleCookie rememberMeCookie() {
        // 这个参数是cookie的名称，对应前端的checkbox的name = rememberMe
        SimpleCookie simpleCookie = new SimpleCookie("ITCLOUD_BLOGS_JSESSIONID");
        simpleCookie.setHttpOnly(true);
        //  path为 / 用于多个系统共享 JSESSIONID
        simpleCookie.setPath("/");
        // 记住我cookie生效时间30天 ,单位秒。 注释掉，默认永久不过期 2018-07-15
        simpleCookie.setMaxAge(shiroRedisProperties.getRemenberMe());
        return simpleCookie;
    }

    /**
     * cookie管理对象;记住我功能
     *
     * @return
     */
    public CookieRememberMeManager rememberMeManager() {
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(rememberMeCookie());
        //rememberMe cookie加密的密钥 建议每个项目都不一样 默认AES算法 密钥长度(128 256 512 位)
        cookieRememberMeManager.setCipherKey(Base64.decode("1QWLxg+NYmxraMoxAXu/Iw=="));
        return cookieRememberMeManager;
    }
}
