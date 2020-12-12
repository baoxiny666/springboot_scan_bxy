package com.itheima.springboot_scan_bxy.entity;

import lombok.Data;

@Data
public class Sys_Post {
    private Integer id;
    private String postNo;
    private String postName;
    private String postLevel;
    private String createTime;
    private String update_time;
}
