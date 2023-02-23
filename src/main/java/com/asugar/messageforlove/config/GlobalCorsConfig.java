package com.asugar.messageforlove.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class GlobalCorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // 设置允许跨域的路由
        registry.addMapping("/**")
                // 是否允许cookies
                .allowCredentials(true)
                // 设置允许跨域请求的域名
                .allowedOriginPatterns("*")
                // 放行哪些请求方式 * 代表允许所有方式
                .allowedMethods("*")
                //放行哪些原始请求头部信息
                .allowedHeaders("*")
                // 跨域允许时间
                .maxAge(3600);
    }
}
