package com.itheima.springboot_scan_bxy.entity;

import lombok.Data;

@Data
public class TreeMenu {
    private Integer id;
    private String func_name;
    private String func_url;
    private String redirect_url;
    private Integer func_pid;
    private String func_design;
    private Object children;
}
