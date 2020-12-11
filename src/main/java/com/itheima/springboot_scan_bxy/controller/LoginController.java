package com.itheima.springboot_scan_bxy.controller;

import com.alibaba.fastjson.JSONObject;
import com.itheima.springboot_scan_bxy.annotation.JwtToken;
import com.itheima.springboot_scan_bxy.utils.JwtUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/jwt")
public class LoginController {
    @RequestMapping("/login")
    public String login( String userName, String passWord){
        JSONObject jsonObject=new JSONObject();
        // 检验用户是否存在(为了简单，这里假设用户存在，并制造一个uuid假设为用户id)
        String userId = "25946";
        // 生成签名
        String token= JwtUtil.sign(userId);
        Map<String, String> userInfo = new HashMap<>();
        userInfo.put("userId", userId);
        userInfo.put("userName", userName);
        userInfo.put("passWord", passWord);
        jsonObject.put("token", token);
        jsonObject.put("user", userInfo);
        return jsonObject.toJSONString();
    }

    /**
     * 该接口需要带签名才能访问
     * @return
     */
    @JwtToken
    @RequestMapping("/getMessage")
    public String getMessage(){
        return "你已通过验证";
    }
}
