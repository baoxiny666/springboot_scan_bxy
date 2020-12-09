package com.itheima.springboot_scan_bxy.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Scan_Configs {
    private Integer id;
    private String key;
    private String value;
    private Date create_time;
    private Date update_time;
}
