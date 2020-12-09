package com.itheima.springboot_scan_bxy.controller;

import com.itheima.springboot_scan_bxy.entity.Scan_Area_Items;
import com.itheima.springboot_scan_bxy.service.ScanConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/scanconfig")
public class ScanConfigController {
    @Autowired
    private ScanConfigService scanConfigService;

    @RequestMapping("/add")
    public void add(@RequestBody String canshu){
        scanConfigService.add(canshu);
    }

    @RequestMapping("/get")
    public String get(){
       List<Scan_Area_Items> list =  scanConfigService.get();
       return list.toString();
    }
}
