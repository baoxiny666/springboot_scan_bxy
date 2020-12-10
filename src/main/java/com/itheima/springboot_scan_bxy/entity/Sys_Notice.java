package com.itheima.springboot_scan_bxy.entity;

import com.alibaba.fastjson.annotation.JSONType;
import lombok.Data;

@Data
@JSONType(orders={"id","noticeContent","noticeStatus"})
public class Sys_Notice {
    private Integer id;
    private String noticeContent;
    private String noticeStatus;
}
