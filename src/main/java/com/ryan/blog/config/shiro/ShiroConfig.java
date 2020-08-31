package com.ryan.blog.config.shiro;

import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Author Ryan
 * @Date 2020/7/20 18:16
 * version 1.0
 */
@Configuration
public class ShiroConfig {
    /**
     * 创建Realm
     * @return返回用户自定义realm
     */
    @Bean(name = "getUserRealm")
    public UserRealm getRealm(){
        return new UserRealm();
    }
    /**
     *创教DefaultSecurityManager
     * @return
     */
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultSecurityManager(@Qualifier("getUserRealm") UserRealm userRealm){
        DefaultWebSecurityManager securityManager= new DefaultWebSecurityManager();
        securityManager.setRealm(userRealm);
        return securityManager;
    }

    /**
     * 创建ShiroFilterFactoryBean
     * @param securityManager
     * @return
     */

    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager")DefaultSecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean=new ShiroFilterFactoryBean();
        //设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //添加内置过滤器
        /**
         * anon 无需认证
         * authc 需要认证
         * user 使用rememberMe功能可以访问
         * perms 必须得到该资源的权限才能访问
         * role 必须拿到角色权限才可以访问
         */
        Map<String,String> filterMap=new LinkedHashMap<>();
//        filterMap.put("/admin/upload","perms[all]");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
        return shiroFilterFactoryBean;
    }
}
