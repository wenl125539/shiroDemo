package com.wenl.config;

import com.wenl.config.realm.UserRealm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;

/**
 * 配置shiro配置类
 */
@Configuration
public class ShiroConfig {

    //1.创建reaml对象
    @Bean
    public UserRealm userRealm(){
        return new UserRealm();
    }

    //2.创建DefaultWebSecurityManager
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager defaultWebSecurityManager(@Qualifier("userRealm")UserRealm userRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //关联realm  UserRealm
        securityManager.setRealm(userRealm);

        return securityManager;
    }
    //3.创建
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager defaultWebSecurityManager){
        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
        //设置安全管理器
        factoryBean.setSecurityManager(defaultWebSecurityManager);

        //添加shiro的内置过滤器
        /*
            anno:无需认证即可访问
            authc:必须认证才可访问
            user:必须拥有 记住我 功能才能访问
            perms:拥有对某个资源的权限才可访问
            role:拥有某角色才可访问
         */
        LinkedHashMap<String, String> filterMap = new LinkedHashMap<>();

       /* filterMap.put("/user/add","authc");//user/add 路径 需认证
        filterMap.put("/user/del","authc");*/
        filterMap.put("/user/*","authc");
        factoryBean.setFilterChainDefinitionMap(filterMap);


        //设置登陆请求
        factoryBean.setLoginUrl("/toLogin");
        return factoryBean;
    }
}
