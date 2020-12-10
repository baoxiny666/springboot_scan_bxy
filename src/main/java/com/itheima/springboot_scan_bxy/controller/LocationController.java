package com.itheima.springboot_scan_bxy.controller;

import com.itheima.springboot_scan_bxy.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/location")
public class LocationController {
    @Autowired
    private LocationService locationService;

    @RequestMapping("/select")
    public String  select(@RequestBody String location_data){
        String location_content  = locationService.select(location_data);
        return location_content;
    }

}
