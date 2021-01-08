package com.itheima.springboot_scan_bxy.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.itheima.springboot_scan_bxy.mapper.StatisticChartsMapper;
import com.itheima.springboot_scan_bxy.service.StatisticChartsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class StatisticChartsServiceImpl implements StatisticChartsService {
    @Autowired
    private StatisticChartsMapper statisticChartsMapper;
    @Override
    public String selectDataCard() {
        List<HashMap> list = statisticChartsMapper.selectDataCard();
        HashMap xinMap = new HashMap();
        Integer total = 0;
        for(HashMap map : list){
            xinMap.put(map.get("enname"),map.get("cc"));
            total+=Integer.valueOf(map.get("cc").toString());
        }
        xinMap.put("total",total);
        JSONObject obj = new JSONObject();
        if(list != null) {
            obj.put("code",200);
            obj.put("data",JSONObject.parseObject(JSONObject.toJSONString(xinMap)));
        }
        return obj.toJSONString();
    }
}
