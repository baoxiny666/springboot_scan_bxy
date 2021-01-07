package com.itheima.springboot_scan_bxy.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.itheima.springboot_scan_bxy.entity.ReportRecords;
import com.itheima.springboot_scan_bxy.entity.StatusConfig;
import com.itheima.springboot_scan_bxy.mapper.ReportRecordsMapper;
import com.itheima.springboot_scan_bxy.service.ReportRecordsService;
import com.itheima.springboot_scan_bxy.utils.AesUtil;
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
        public String select(String aesData) {

                String aesDeData = AesUtil.decrypt(aesData,AesUtil.KEY);
                JSONObject aesDeDateObj=JSONObject.parseObject(aesDeData);
                ReportRecords reportRecords = JSON.toJavaObject(aesDeDateObj,ReportRecords.class);

                List<ReportRecords> list = reportRecordsMapper.select(reportRecords);
                JSONObject obj = new JSONObject();
                if(list != null) {
                        JSONArray jsonArray = JSONArray.parseArray(JSON.toJSONString(list));
                        System.out.println(jsonArray);
                        obj.put("code",200);
                        obj.put("data",jsonArray);
                }
                return obj.toJSONString();
        }

        @Override
        public String selectDetail(String aesData) {
                String aesDeData = AesUtil.decrypt(aesData,AesUtil.KEY);
                JSONObject aesDeDateObj=JSONObject.parseObject(aesDeData);
                ReportRecords reportRecords = JSON.toJavaObject(aesDeDateObj,ReportRecords.class);
                List<ReportRecords> list = reportRecordsMapper.selectDetail(reportRecords);
                JSONObject obj = new JSONObject();
                if(list != null) {
                        JSONArray jsonArray = JSONArray.parseArray(JSON.toJSONString(list));
                        System.out.println(jsonArray);
                        obj.put("code",200);
                        obj.put("data",jsonArray);
                }
                return obj.toJSONString();
        }

        @Override
        public List<HashMap> selectExport(String aesData) {
                String aesDeData = AesUtil.decrypt(aesData,AesUtil.KEY);
                JSONObject aesDeDateObj=JSONObject.parseObject(aesDeData);
                ReportRecords reportRecords = JSON.toJavaObject(aesDeDateObj,ReportRecords.class);

                List<HashMap> list = reportRecordsMapper.selectExport(reportRecords);

                return list;
        }

        @Override
        public String submenu() {
                List<HashMap> submenuDepartListMap= reportRecordsMapper.submenuDepart();
                JSONArray submenuArray = new JSONArray();
                List list = new ArrayList();
                int flag = 0;
                JSONObject wholeObj = new JSONObject();
                wholeObj.put("value","-1");
                wholeObj.put("label","全部");

                submenuArray.add(wholeObj);
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

        @Override
        public String status() {
                List<StatusConfig> statusListMap= reportRecordsMapper.status();
                String statusData = JSON.toJSONString(statusListMap);
                return statusData;
        }

        public static String transferTime(Object lastModify){
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                return sdf.format(lastModify);
        }

}
