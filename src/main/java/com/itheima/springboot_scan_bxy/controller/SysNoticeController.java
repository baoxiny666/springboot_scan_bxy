package com.itheima.springboot_scan_bxy.controller;

import com.itheima.springboot_scan_bxy.service.SysNoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/sysnotice")
public class SysNoticeController {
    @Autowired
    private SysNoticeService sysNoticeService;

    @RequestMapping("/select")
    public String  select(){
        String sys_notice_content  = sysNoticeService.select();
        return sys_notice_content;
    }

}
