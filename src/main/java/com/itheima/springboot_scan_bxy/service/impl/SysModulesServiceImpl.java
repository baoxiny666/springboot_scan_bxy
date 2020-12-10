package com.itheima.springboot_scan_bxy.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.itheima.springboot_scan_bxy.entity.Sys_Modules;
import com.itheima.springboot_scan_bxy.mapper.SysModuleMapper;
import com.itheima.springboot_scan_bxy.service.SysModulesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysModulesServiceImpl implements SysModulesService {

        @Autowired
        SysModuleMapper sysModuleMapper;

        @Override
        public String select() {
                List<Sys_Modules> list =  sysModuleMapper.select();
                String json = JSON.toJSONString(list, SerializerFeature.WriteNullStringAsEmpty);
                return json;
        }
}
