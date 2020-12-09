package com.itheima.springboot_scan_bxy.service;


import com.itheima.springboot_scan_bxy.entity.Scan_Area_Items;

import java.util.List;

public interface ScanConfigService {
    void add(String canshu);

    List<Scan_Area_Items> get();
}
