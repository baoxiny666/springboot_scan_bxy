package com.itheima.springboot_scan_bxy.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

@Component
public class AuthInterceptor implements HandlerInterceptor {
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        response.setContentType("text/html;charset=utf-8");
        System.out.println(">>>AuthInterceptor>>>>>>>在请求处理之前进行调用（Controller方法调用之前)");


        String token = request.getHeader("token");

        token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIyNTk0NiJ9.Ir8CQy1U4n8p4gEOfBSQNNVaFQzplHGWWM5xOWaOZa4";
        /*关于 AES加密内容*/
        PrintWriter out = null;
        return true;
    }

}
