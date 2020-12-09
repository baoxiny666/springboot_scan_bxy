package com.itheima.springboot_scan_bxy.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Scan_Area_Items {
    private Integer id;
    private String area_no;
    private String item_name;
    private Date create_time;
    private Date update_time;
}
