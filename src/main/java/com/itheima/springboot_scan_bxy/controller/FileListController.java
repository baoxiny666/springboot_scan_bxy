package com.itheima.springboot_scan_bxy.controller;

import com.itheima.springboot_scan_bxy.service.ReportRecordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/filelist")
public class FileListController {
    @Autowired
    private ReportRecordsService fileListService;



    @RequestMapping("/select")
    public String select(String aesData){
       String list =  fileListService.select(aesData);
       return list;
    }
}
