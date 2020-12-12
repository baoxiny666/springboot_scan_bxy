package com.itheima.springboot_scan_bxy.entity;

import lombok.Data;

@Data
public class DepartMent {
    private Integer id;
    private String departNo;
    private String departName;
    private String departParentNo;
    private String createTime;
    private String update_time;
    private Integer departStatus;
}
