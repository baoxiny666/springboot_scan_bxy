package com.itheima.springboot_scan_bxy.controller;


import com.itheima.springboot_scan_bxy.service.ReportRecordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/report")
public class ReportRecordsController {
    @Autowired
    private ReportRecordsService reportRecordsService;

    @RequestMapping("/select")
    public String  select(){
        String ReportsData  = reportRecordsService.select();
        return ReportsData;
    }

}
