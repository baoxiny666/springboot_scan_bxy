package com.itheima.springboot_scan_bxy.service;


import com.itheima.springboot_scan_bxy.entity.User;

import java.util.Map;

public interface LoginService {
    Map loginCheck(User user);
}
