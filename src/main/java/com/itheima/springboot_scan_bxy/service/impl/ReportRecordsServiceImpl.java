package com.itheima.springboot_scan_bxy.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.itheima.springboot_scan_bxy.mapper.ReportRecordsMapper;
import com.itheima.springboot_scan_bxy.service.ReportRecordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class ReportRecordsServiceImpl implements ReportRecordsService {

        @Autowired
        ReportRecordsMapper reportRecordsMapper;

        @Override
        public String select() {
                JSONArray jsonArray = new JSONArray();
                //查询条件

                return jsonArray.toJSONString();
        }

        @Override
        public String submenu() {
                List<HashMap> submenuDepartListMap= reportRecordsMapper.submenuDepart();
                JSONArray submenuArray = new JSONArray();
                List list = new ArrayList();
                for (HashMap submenuDepartMap:submenuDepartListMap) {
                        JSONObject submenuSubObject = new JSONObject();
                        submenuSubObject.put("value",submenuDepartMap.get("depart_id").toString());
                        submenuSubObject.put("label",submenuDepartMap.get("depart_name").toString());
                        String depart_id = submenuDepartMap.get("depart_id").toString();

                        List<HashMap> submenuAreaListMap = reportRecordsMapper.submenuArea(depart_id);
                        JSONArray subMenuAreaArray = new JSONArray();
                        if(submenuAreaListMap != null){
                                for (HashMap submenuAreaMap:submenuAreaListMap) {
                                        JSONObject subMenuAreaObject = new JSONObject();
                                        subMenuAreaObject.put("value",submenuAreaMap.get("area_no"));
                                        subMenuAreaObject.put("label",submenuAreaMap.get("area_name"));
                                        subMenuAreaArray.add(subMenuAreaObject);
                                }
                                submenuSubObject.put("children",subMenuAreaArray);
                        }
                        submenuArray.add(submenuSubObject);
                }
                return submenuArray.toJSONString();
        }

        public static String transferTime(Object lastModify){
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                return sdf.format(lastModify);
        }

}
