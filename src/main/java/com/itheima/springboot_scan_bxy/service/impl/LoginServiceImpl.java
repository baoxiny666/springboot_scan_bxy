package com.itheima.springboot_scan_bxy.service.impl;

import com.itheima.springboot_scan_bxy.entity.User;
import com.itheima.springboot_scan_bxy.mapper.LoginMapper;
import com.itheima.springboot_scan_bxy.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class LoginServiceImpl implements LoginService {

        @Autowired
        LoginMapper loginMapper;

        @Override
        public Map loginCheck(User user) {
               Map map =  loginMapper.loginCheck(user);
               return map;
        }
}
