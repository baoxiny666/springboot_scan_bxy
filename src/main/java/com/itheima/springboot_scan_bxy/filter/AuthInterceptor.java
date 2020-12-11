package com.itheima.springboot_scan_bxy.filter;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.security.MessageDigest;

@Component
public class AuthInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        System.out.println(">>>AuthInterceptor>>>>>>>在请求处理之前进行调用（Controller方法调用之前)");

        String sign = request.getHeader("sign");
        String timeStamp = request.getHeader("timeStamp");
        String config_key = "__SIGN__ABA0320";
        sign = "c4345b2f090563c6f0a5f8b2478dfc50";
        timeStamp="1607652390471";


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
                e.printStackTrace();
                return false;
            }
        }else{
            String md5Secret = encryption(encryption(config_key+timeStamp).substring(0,10));
            if(sign.equals(md5Secret)){
                return true;
            }else{
                JSONObject res = new JSONObject();
                res.put("isSuccess", false);
                res.put("errorCode", "403");
                out = response.getWriter();
                out.append(res.toString());
                return false;
            }
        }
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
