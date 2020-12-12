package com.itheima.springboot_scan_bxy.mapper;


import com.itheima.springboot_scan_bxy.entity.User;
import org.springframework.stereotype.Repository;

import java.util.HashMap;


@Repository
public interface LoginMapper {

    HashMap loginCheck(User user);
}
