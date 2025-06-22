package org.example.elm_spring.config;

import org.example.elm_spring.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    private LoginInterceptor loginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor).excludePathPatterns("/login","/register","/business","/businessList","/business/orderTypeId/{orderTypeId}"
        ,"/business/search/{name}","/business/businessId/{businessId}","/food/{businessId}/{userId}","/food/businessId/{businessId}");
    }
}
