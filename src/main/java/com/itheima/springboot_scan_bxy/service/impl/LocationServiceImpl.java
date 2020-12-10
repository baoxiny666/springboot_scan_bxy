package com.itheima.springboot_scan_bxy.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.itheima.springboot_scan_bxy.entity.Location;
import com.itheima.springboot_scan_bxy.mapper.LocationMapper;
import com.itheima.springboot_scan_bxy.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class LocationServiceImpl implements LocationService {
        private static double EARTH_RADIUS = 6378137;

        @Autowired
        LocationMapper locationMapper;

        @Override
        public String select(String location_data) {

                JSONObject jo=new JSONObject();
                JSONObject location_content_obj = new JSONObject();
                JSONArray location_correct_location_array = new JSONArray();

                Map dataMap = jo.parseObject(location_data, Map.class);

                Boolean flag = dataMap.containsValue(null);
                if(flag == true){
                        location_content_obj.put("flag",false);
                        location_content_obj.put("content","位置不存在！！！");
                }else{
                        Double lonDouble =  Double.parseDouble(dataMap.get("lon").toString());
                        Double latDouble =  Double.parseDouble(dataMap.get("lat").toString());

                        List<Location> centerlocations = locationMapper.select();

                        for(Location loc : centerlocations){
                               Double lonCenter = Double.parseDouble(loc.getLon());
                               Double latCenter = Double.parseDouble(loc.getLat());
                               Double radius = Double.parseDouble(loc.getRadius());
                               String placeName = loc.getPlacename();
                               boolean inCircleBoolean = isInCircle(latDouble,lonDouble,latCenter,lonCenter,radius);
                               if(inCircleBoolean == true) {
                                       location_correct_location_array.add(placeName);
                               }
                       }
                        if(location_correct_location_array.size() != 0){
                                location_content_obj.put("flag",true);
                                location_content_obj.put("content",location_correct_location_array);
                        }else{
                                location_content_obj.put("flag",false);
                                location_content_obj.put("content","位置不存在！！！");
                        }

                }

                return location_content_obj.toJSONString();

        }

        private static double rad(double d) {
                return d * Math.PI / 180.0;
        }



        private static double getDistance(double lat1, double lng1, double lat2,double lng2) {
                double radLat1 = rad(lat1);
                double radLat2 = rad(lat2);
                double a = radLat1 - radLat2;
                double b = rad(lng1) - rad(lng2);
                double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) +
                        Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
                s = s * EARTH_RADIUS;
                s = Math.round(s * 10000d) / 10000d;
                System.out.println(s);
                return s;
        }


        private static boolean isInCircle(double lng1, double lat1, double lng2, double lat2, double radius) {
                double distance = getDistance(lat1, lng1, lat2, lng2);
                if (distance > radius) {
                        return false;
                } else {
                        return true;
                }
        }
}
