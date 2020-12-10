package com.itheima.springboot_scan_bxy.entity;

import com.alibaba.fastjson.annotation.JSONType;
import lombok.Data;

@Data
@JSONType(orders={"id","adGroup","adImage","createTime","updateTime"})
public class Sys_Ad {
    private Integer id;
    private String adGroup;
    private String adImage;
    private String createTime;
    private String  updateTime;
}
