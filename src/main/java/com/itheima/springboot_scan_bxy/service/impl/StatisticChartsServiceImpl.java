package com.itheima.springboot_scan_bxy.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.itheima.springboot_scan_bxy.entity.ReportRecords;
import com.itheima.springboot_scan_bxy.mapper.StatisticChartsMapper;
import com.itheima.springboot_scan_bxy.service.StatisticChartsService;
import com.itheima.springboot_scan_bxy.utils.AesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    @Override
    public String selectDataCharts(String aesData) {

        String aesDeData = AesUtil.decrypt(aesData,AesUtil.KEY);
        JSONObject aesDeDateObj=JSONObject.parseObject(aesDeData);
        ReportRecords reportRecords = JSON.toJavaObject(aesDeDateObj,ReportRecords.class);
        List<HashMap> departConfigList = statisticChartsMapper.selectDepartConfig();
        List<HashMap> colmnTotal = new ArrayList<>();
        List<HashMap> pieTotal = new ArrayList<>();
        for(HashMap departConfigMap : departConfigList){
            HashMap colmntotalMap = new HashMap();
            HashMap pietotalMap = new HashMap();
            reportRecords.setDepart_id(Integer.valueOf(departConfigMap.get("flag").toString()));
            HashMap DataChartsMap = statisticChartsMapper.selectDataCharts(reportRecords);
            colmntotalMap.put("name",departConfigMap.get("name"));
            colmntotalMap.put("number",DataChartsMap.get("cc"));
            pietotalMap.put("type",departConfigMap.get("name"));
            pietotalMap.put("value",DataChartsMap.get("cc"));
            colmnTotal.add(colmntotalMap);
            pieTotal.add(pietotalMap);
        }
        JSONObject jsonObjectOut = new JSONObject();
        JSONObject jsonObjectInner = new JSONObject();
        JSONArray jsonArrayColumn = JSONArray.parseArray(JSON.toJSONString(colmnTotal));
        JSONArray jsonArrayPie = JSONArray.parseArray(JSON.toJSONString(pieTotal));
        jsonObjectInner.put("pieCharts",jsonArrayPie);
        jsonObjectInner.put("columnCharts",jsonArrayColumn);

        jsonObjectOut.put("code",200);
        jsonObjectOut.put("data",jsonObjectInner);


        return jsonObjectOut.toJSONString();
    }
}
