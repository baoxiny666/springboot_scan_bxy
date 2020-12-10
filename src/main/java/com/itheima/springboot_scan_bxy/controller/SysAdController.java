package com.itheima.springboot_scan_bxy.controller;

import com.itheima.springboot_scan_bxy.service.SysAdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sysad")
public class SysAdController {
    @Autowired
    private SysAdService sysAdService;

    @RequestMapping("/select")
    public String  select(){
        String sys_ad_content  = sysAdService.select();
        return sys_ad_content;
    }

}
