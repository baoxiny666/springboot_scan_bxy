package com.itheima.springboot_scan_bxy.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.itheima.springboot_scan_bxy.entity.Scan_Area_Items;
import com.itheima.springboot_scan_bxy.mapper.ScanConfigMapper;
import com.itheima.springboot_scan_bxy.service.ScanConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ScanConfigServiceImpl implements ScanConfigService {
        @Autowired
        ScanConfigMapper scanConfigMapper;

        @Override
        public void add(String canshu) {

                // 字符串中数据提取
                JSONObject jo=new JSONObject();
                Map dataMap = jo.parseObject(canshu, Map.class);
                scanConfigMapper.add(dataMap);

        }

        @Override
        public List<Scan_Area_Items> get() {
                List<Scan_Area_Items> list = scanConfigMapper.select();
                return list;
        }
}
