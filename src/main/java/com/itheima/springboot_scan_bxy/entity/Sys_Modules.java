package com.itheima.springboot_scan_bxy.entity;

import com.alibaba.fastjson.annotation.JSONType;
import lombok.Data;

@Data
@JSONType(orders={"id","moduleNo","moduleName","moduleDesc","moduleIcon","moduleBg","moduleRouter","isPermit","moduleStatus","showOrder","createTime","updateTime"})
public class Sys_Modules {
    private Integer id;
    private String moduleNo;
    private String moduleName;
    private String moduleDesc;
    private String moduleIcon;
    private String moduleBg;
    private String moduleRouter;
    private String isPermit;

    private String moduleStatus;
    private String showOrder;
    private String createTime;
    private String updateTime;
}
