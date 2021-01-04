package com.itheima.springboot_scan_bxy.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.itheima.springboot_scan_bxy.mapper.ReportRecordsMapper;
import com.itheima.springboot_scan_bxy.service.ReportRecordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;

@Service
public class ReportRecordsServiceImpl implements ReportRecordsService {

        @Autowired
        ReportRecordsMapper reportRecordsMapper;

        @Override
        public String select() {
                JSONArray jsonArray = new JSONArray();
                return jsonArray.toJSONString();
        }

        public static String transferTime(Object lastModify){
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                return sdf.format(lastModify);
        }

}
