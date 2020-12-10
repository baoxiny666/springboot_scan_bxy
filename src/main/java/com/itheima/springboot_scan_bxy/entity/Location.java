package com.itheima.springboot_scan_bxy.entity;

import lombok.Data;

@Data
public class Location {
    private Integer id;
    private String lon;
    private String lat;
    private String radius;
    private String placename;
}
