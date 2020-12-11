package com.itheima.springboot_scan_bxy.entity;

import com.alibaba.fastjson.annotation.JSONType;
import lombok.Data;

@Data
@JSONType(orders={"id","fileName","fileSuffix","fileSize","fileTime"})
public class FileList {
    private Integer id;
    private String  fileName;
    private String fileSuffix;
    private String fileSize;
    private String fileTime;
}
