package com.itheima.springboot_scan_bxy.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.itheima.springboot_scan_bxy.entity.Sys_Notice;
import com.itheima.springboot_scan_bxy.mapper.SysNoticeMapper;
import com.itheima.springboot_scan_bxy.service.SysNoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysNoticeServiceImpl implements SysNoticeService {

        @Autowired
        SysNoticeMapper sysNoticeMapper;

        @Override
        public String select() {
                Sys_Notice object_sys_modules =  sysNoticeMapper.select();
                String json = JSON.toJSONString(object_sys_modules, SerializerFeature.WriteNullStringAsEmpty);
                return json;
        }
}
