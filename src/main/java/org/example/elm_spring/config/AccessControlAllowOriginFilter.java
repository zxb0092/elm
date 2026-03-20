package org.example.elm_spring.config;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 全局跨域配置类。
 * 实现 {@link WebMvcConfigurer} 接口，允许所有来源、所有请求头、所有 HTTP 方法的跨域访问，
 * 预检请求缓存时间为 1800 秒。
 */
@Configuration
public class AccessControlAllowOriginFilter implements WebMvcConfigurer {

    /**
     * 配置跨域映射规则。
     * 对所有路径开放跨域，允许任意请求头、任意请求方法和任意来源。
     *
     * @param registry 跨域注册表，用于注册跨域规则
     */
    @Override
    public void addCorsMappings(CorsRegistry registry){
        registry.addMapping("/*/**")
                .allowedHeaders("*")
                .allowedMethods("*")
                .maxAge(1800)
                .allowedOrigins("*");
    }
}
