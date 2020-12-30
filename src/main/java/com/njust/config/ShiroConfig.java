package com.njust.config;

import com.njust.shiro.CustomAccessControlFilter;
import com.njust.shiro.CustomHashedCredentialsMatcher;
import com.njust.shiro.CustomRealm;

import com.njust.shiro.RedisCacheManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @ Description   :  java类作用描述
 * @ Author        :  景色分明
 * @ CreateDate    :  2020/12/28$ 15:44$
 * @ UpdateUser    :
 * @ UpdateDate    :  2020/12/28$ 15:44$
 * @ Version       :  1.0
 */
@Configuration
public class ShiroConfig {

    @Bean
    public RedisCacheManager redisCacheManager(){
        return new RedisCacheManager();
    }

    @Bean
    public CustomHashedCredentialsMatcher customHashedCredentialsMatcher(){
        return new CustomHashedCredentialsMatcher();
    }

    @Bean
    public CustomRealm customRealm(){
        CustomRealm customRealm = new CustomRealm();
        customRealm.setCredentialsMatcher(customHashedCredentialsMatcher());
        customRealm.setCacheManager(redisCacheManager());
        return customRealm;
    }

    @Bean
    public DefaultWebSecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(customRealm());
        return securityManager;
    }

    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean() {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager());

        //自定义拦截器限制并发人数
        LinkedHashMap<String, Filter> filterMap = new LinkedHashMap<>();
        //用来校验token
        filterMap.put("token", new CustomAccessControlFilter());
        shiroFilterFactoryBean.setFilters(filterMap);

        Map<String, String> map = new LinkedHashMap<>();
        //判断不会被拦截的链接 顺序判断
        map.put("/user/login", "anon");
        map.put("/index/**", "anon");
        map.put("/images/**", "anon");
        map.put("/js/**", "anon");
        map.put("/layui/**", "anon");
        map.put("/treetable-lay/**", "anon");
        //放开swagger-ui地址
        map.put("/swagger/**", "anon");
        map.put("/v2/api-docs", "anon");
        map.put("/swagger-ui.html", "anon");
        map.put("/swagger-resources/**", "anon");
        map.put("/webjars/**", "anon");
        map.put("/favicon.ico", "anon");
        map.put("/captcha.jpg", "anon");
        //druid sql监控配置
        map.put("/druid/**", "anon");
        map.put("/**","token,authc"); //其他均需要认证
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        return shiroFilterFactoryBean;
    }

    //开启shiro注解支持
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultWebSecurityManager
                                                                                           securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new
                AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

}
