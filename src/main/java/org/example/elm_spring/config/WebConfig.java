package org.example.elm_spring.config;

import org.example.elm_spring.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Web MVC 配置类。
 * 注册登录拦截器 {@link LoginInterceptor}，并指定不需要登录验证的白名单路径，
 * 包括登录、注册及商家/食品的公开查询接口。
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    private LoginInterceptor loginInterceptor;

    /**
     * 注册拦截器并配置放行路径。
     * 以下路径不经过登录拦截：/login、/register、/business、/businessList、
     * /business/orderTypeId/{orderTypeId}、/business/search/{name}、
     * /business/businessId/{businessId}、/food/{businessId}/{userId}、
     * /food/businessId/{businessId}。
     *
     * @param registry 拦截器注册表
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor).excludePathPatterns("/login","/register","/business","/businessList","/business/orderTypeId/{orderTypeId}"
        ,"/business/search/{name}","/business/businessId/{businessId}","/food/{businessId}/{userId}","/food/businessId/{businessId}");
    }
}
