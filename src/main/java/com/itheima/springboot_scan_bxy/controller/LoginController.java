package com.itheima.springboot_scan_bxy.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.itheima.springboot_scan_bxy.entity.User;
import com.itheima.springboot_scan_bxy.service.LoginService;
import com.itheima.springboot_scan_bxy.utils.AesUtil;
import com.itheima.springboot_scan_bxy.utils.JwtUtil;
import com.itheima.springboot_scan_bxy.utils.Md5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/jwt")
public class LoginController {
    @Autowired
    private LoginService loginService;

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @RequestMapping("/login")
    public String login( String aesData){
        JSONObject jsonObject=new JSONObject();
        // 检验用户是否存在(为了简单，这里假设用户存在，并制造一个uuid假设为用户id)
        String aesDeData = AesUtil.decrypt(aesData,AesUtil.KEY);
        JSONObject aesDeDateObj=JSONObject.parseObject(aesDeData);
        String userName  = aesDeDateObj.get("userName").toString();
        String passWord  = aesDeDateObj.get("passWord").toString();

        Md5 md5 = new Md5();
        User user = new User();
        user.setUserName(userName);
        user.setPassWord(md5.encrypt32(passWord));
        Map checkUserMap = loginService.loginCheck(user);
        //检查用户名 密码
        if(checkUserMap != null){
            String passwordCx = checkUserMap.get("password").toString();
            String passswordSr = md5.encrypt32(passWord);
            if(passswordSr.equals(passwordCx)) {
                String token= JwtUtil.sign(userName);
                redisTemplate.opsForValue().set(userName,token);
                //设置token有效的时间
                redisTemplate.expire(userName, 30, TimeUnit.SECONDS);

                JSONObject obj = new JSONObject();
                obj.put("token",token);
                obj.put("isSuccess",true);
                return JSON.toJSONString(obj);
            }else {
                JSONObject obj = new JSONObject();
                obj.put("isSuccess", false);
                obj.put("errorCode",407);
                return obj.toJSONString();
            }
        }else{
            JSONObject obj = new JSONObject();
            obj.put("isSuccess", false);
            obj.put("errorCode",407);
            return obj.toJSONString();
        }

    }

    /**
     * 该接口需要带签名才能访问
     * @return
     */
    @RequestMapping("/getMessage")
    public String getMessage(){
        return "你已通过验证";
    }
}
