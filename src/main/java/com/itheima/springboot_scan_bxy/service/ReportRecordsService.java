package com.itheima.springboot_scan_bxy.service;


import java.util.HashMap;
import java.util.List;

public interface ReportRecordsService {
    String select(String aesData);
    String  selectDetail(String aesData);
    List<HashMap> selectExport(String aesData);

    String submenu();

    String status();
}
