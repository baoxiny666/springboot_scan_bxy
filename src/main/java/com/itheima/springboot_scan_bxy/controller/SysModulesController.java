package com.itheima.springboot_scan_bxy.controller;

import com.itheima.springboot_scan_bxy.service.SysModulesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sysmoudles")
public class SysModulesController {
    @Autowired
    private SysModulesService sysModulesService;

    @RequestMapping("/select")
    public String  select(){
        String sys_modules_content  = sysModulesService.select();
        return sys_modules_content;
    }

}
