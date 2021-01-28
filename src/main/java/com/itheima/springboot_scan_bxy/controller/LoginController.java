package com.itheima.springboot_scan_bxy.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.itheima.springboot_scan_bxy.entity.TreeMenu;
import com.itheima.springboot_scan_bxy.entity.User;
import com.itheima.springboot_scan_bxy.service.AdviseService;
import com.itheima.springboot_scan_bxy.service.LoginService;
import com.itheima.springboot_scan_bxy.utils.AesUtil;
import com.itheima.springboot_scan_bxy.utils.JwtUtil;
import com.itheima.springboot_scan_bxy.utils.Md5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/api/jwt")
public class LoginController {
    @Autowired
    private LoginService loginService;

    @Autowired
    private AdviseService adviseService;

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @RequestMapping("/login")
    public String login( String aesData){
        JSONObject jsonObject=new JSONObject();

        String aesDeData = AesUtil.decrypt(aesData,AesUtil.KEY);
        JSONObject aesDeDateObj=JSONObject.parseObject(aesDeData);
        String userName  = aesDeDateObj.get("userName").toString();
        String passWord  = aesDeDateObj.get("passWord").toString();

        Md5 md5 = new Md5();
        User user = new User();
        user.setUserName(userName);
        user.setPassWord(md5.encrypt32(passWord));
        Map checkUserMap = loginService.loginCheck(user);

        String lhtg_redis_flag = "back:manage:";
        //检查用户名 密码
        if(checkUserMap != null){
            String passwordCx = checkUserMap.get("user_pwd").toString();
            String userId = checkUserMap.get("id").toString();
            //拼接jwt秘钥
            String totalSecret =  lhtg_redis_flag+""+userId;
            String passswordSr = md5.encrypt32(passWord);

            if(passswordSr.equals(passwordCx)) {
                String token= JwtUtil.sign(totalSecret);
                redisTemplate.opsForValue().set(totalSecret,token);
                //设置token有效的时间
                redisTemplate.expire(totalSecret, 300000, TimeUnit.SECONDS);
                //求当前登录用户拥有的菜单结构
                List<TreeMenu> treeMenus = adviseService.treeMenu(userId);
                JSONArray tmJsonArray = new JSONArray();

                JSONObject obj = new JSONObject();
                JSONObject obj_inner = new JSONObject();
                obj_inner.put("token",token);
                obj_inner.put("obj","flag");
                obj_inner.put("treeMenu", tmJsonArray.toJSONString(treeMenus));
                obj.put("code",200);
                obj.put("content",obj_inner);
                obj.put("isSuccess",true);
                return JSON.toJSONString(obj);
            }else {
                JSONObject obj = new JSONObject();
                obj.put("isSuccess", false);
                obj.put("code",407);
                return obj.toJSONString();
            }
        }else{
            JSONObject obj = new JSONObject();
            obj.put("isSuccess", false);
            obj.put("code",407);
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
