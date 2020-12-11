package com.itheima.springboot_scan_bxy.filter;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSONObject;
import com.itheima.springboot_scan_bxy.annotation.JwtToken;
import com.itheima.springboot_scan_bxy.utils.JwtUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.security.MessageDigest;

@Component
public class AuthInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        response.setContentType("text/html;charset=utf-8");
        System.out.println(">>>AuthInterceptor>>>>>>>在请求处理之前进行调用（Controller方法调用之前)");
        /*数字签名*/
        String sign = request.getHeader("sign");
        String timeStamp = request.getHeader("timeStamp");
        String token = request.getHeader("token");
        String config_key = "__SIGN__ABA0320";
        sign = "c4345b2f090563c6f0a5f8b2478dfc50";
        timeStamp="1607652390471";
        token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIyNTk0NiJ9.Ir8CQy1U4n8p4gEOfBSQNNVaFQzplHGWWM5xOWaOZa4";

        /*关于 JWT 内容*/


        PrintWriter out = null;
        if (StringUtils.isEmpty(sign) || StringUtils.isEmpty(timeStamp)) {
            try {
                JSONObject res = new JSONObject();
                res.put("isSuccess", false);
                res.put("errorCode", "403");
                out = response.getWriter();
                out.append(res.toString());

                return false;
            } catch (Exception e) {
                JSONObject res = new JSONObject();
                res.put("isSuccess", false);
                res.put("errorCode", "500");
                out = response.getWriter();
                out.append(res.toString());

                return false;


            }
        }else{
            String md5Secret = encryption(encryption(config_key+timeStamp).substring(0,10));
            if(sign.equals(md5Secret)){

                // 如果不是映射到方法直接通过
                if(!(handler instanceof HandlerMethod)){
                    return true;
                }
                HandlerMethod handlerMethod=(HandlerMethod)handler;
                Method method=handlerMethod.getMethod();
                //检查有没有需要用户权限的注解
                if (method.isAnnotationPresent(JwtToken.class)) {
                    JwtToken jwtToken = method.getAnnotation(JwtToken.class);
                    if (jwtToken.required()) {
                        // 执行认证
                        if (token == null) {
                            JSONObject res = new JSONObject();
                            res.put("isSuccess", false);
                            res.put("errorCode", 407);
                            out = response.getWriter();
                            out.append(res.toString());

                            return false;
                        }
                        // 获取 token 中的 userId
                        String userId = JwtUtil.getUserId(token);
                        System.out.println("用户id:" + userId);
                        // 验证 token
                        Boolean panduan = JwtUtil.checkSign(token,response);
                        if(panduan == false){
                            JSONObject res = new JSONObject();
                            res.put("isSuccess", false);
                            res.put("errorCode", 407);
                            out = response.getWriter();
                            out.append(res.toJSONString());

                            return false;
                        }
                    }
                }
            }else{
                JSONObject res = new JSONObject();
                res.put("isSuccess", false);
                res.put("errorCode", 403);
                out = response.getWriter();
                out.append(res.toString());
                response.reset();
                return false;
            }
        }
        return true;
    }

    public static void  jwtMethods(String token){
        PrintWriter out = null;
        Object object = null;

    }

    /*
     * md5 32位加密
     */
    public String encryption(String plainText) {
        String re_md5 = new String();
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(plainText.getBytes());
            byte b[] = md.digest();
            int i;
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }

            re_md5 = buf.toString();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return re_md5;
    }


}
