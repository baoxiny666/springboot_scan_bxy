package com.itheima.springboot_scan_bxy.service.impl;

import com.itheima.springboot_scan_bxy.entity.User;
import com.itheima.springboot_scan_bxy.mapper.UserMapper;
import com.itheima.springboot_scan_bxy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
        @Autowired
        UserMapper userMapper;

        @Override
        public User select(Integer id){
            return userMapper.select(id);
        }
}
