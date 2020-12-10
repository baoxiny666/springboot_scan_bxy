package com.itheima.springboot_scan_bxy.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.itheima.springboot_scan_bxy.entity.Sys_Ad;
import com.itheima.springboot_scan_bxy.mapper.SysAdMapper;
import com.itheima.springboot_scan_bxy.service.SysAdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysAdServiceImpl implements SysAdService {

        @Autowired
        SysAdMapper sysAdMapper;

        @Override
        public String select() {
                List<Sys_Ad> object_sys_ads =  sysAdMapper.select();
                String json = JSON.toJSONString(object_sys_ads, SerializerFeature.WriteNullStringAsEmpty);
                return json;
        }
}
