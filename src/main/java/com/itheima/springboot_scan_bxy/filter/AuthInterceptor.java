package com.itheima.springboot_scan_bxy.filter;

import com.alibaba.fastjson.JSONObject;
import com.itheima.springboot_scan_bxy.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;

@Component
public class AuthInterceptor implements HandlerInterceptor {
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        response.setContentType("text/html;charset=utf-8");
        String authorization = request.getHeader("Authorization");
        System.out.println(">>>AuthInterceptor>>>>>>>在请求处理之前进行调用（Controller方法调用之前)");

        PrintWriter out = null;
        if(authorization == null){
            // 执行认证
                JSONObject res = new JSONObject();
                res.put("code", 407);
                out = response.getWriter();
                out.append(res.toString());
                return false;
        }else {
            String userId = JwtUtil.getUserId(authorization) == null ? "" : JwtUtil.getUserId(authorization);
            String isRedisUserId = redisTemplate.opsForValue().get(userId) == null ? null : redisTemplate.opsForValue().get(userId).toString();
            if (isRedisUserId != null) {
                // 验证 token
                Boolean panduan = JwtUtil.checkSign(isRedisUserId, response);
                if (panduan == false) {
                    JSONObject res = new JSONObject();
                    res.put("code", 407);
                    out = response.getWriter();
                    out.append(res.toJSONString());
                    return false;
                } else {
                    redisTemplate.expire(userId, 30, TimeUnit.SECONDS);
                }
            } else {
                JSONObject res = new JSONObject();
                res.put("code", 407);
                out = response.getWriter();
                out.append(res.toJSONString());

                return false;
            }
        }
        return true;
    }

}
