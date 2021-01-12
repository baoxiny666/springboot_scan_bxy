package com.itheima.springboot_scan_bxy.config;

import com.itheima.springboot_scan_bxy.filter.AuthInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
           registry.addResourceHandler("/admin/**").addResourceLocations("classpath:/static/");
//        registry.addResourceHandler("/img/**").addResourceLocations("classpath:/img/");
//        registry.addResourceHandler("/css/**").addResourceLocations("classpath:/css/");
//        registry.addResourceHandler("/js/**").addResourceLocations("classpath:/js/");
    }
    @Bean
    public AuthInterceptor getAuthInterceptor() {
        return new AuthInterceptor();
    }

    //配置拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getAuthInterceptor()).addPathPatterns("/api/**").excludePathPatterns("/api/jwt/login").excludePathPatterns("/api/report/export");/*.excludePathPatterns("/jwt/login")
                .excludePathPatterns(Arrays.asList("/css/**", "/js/**","/img/**"))
                .excludePathPatterns("")
                .excludePathPatterns("/login")
                .excludePathPatterns("/static/**")
                .excludePathPatterns("/admin/index.html")
                .excludePathPatterns("/report/export")
                .excludePathPatterns("/login")
                .excludePathPatterns("favicon.ico")
                .excludePathPatterns("index.html");*/
    }
}
