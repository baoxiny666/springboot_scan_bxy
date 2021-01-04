package com.itheima.springboot_scan_bxy.entity;

import lombok.Data;

import java.util.List;

@Data
public class DepartMent {
    private Integer id;
    private String departName;
    private Integer departPid;
    private String createTime;
    private String update_time;
    private Integer departStatus;

    private List<DepartMent> Child;


}
