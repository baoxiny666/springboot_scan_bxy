package com.itheima.springboot_scan_bxy.entity;

import lombok.Data;

@Data
public class User {
    private Integer id;
    private String userNo;
    private String userName;
    private String passWord;

    private DepartMent departMent;
    private Sys_Post sysPost;

    private String userPhone;
    private Integer jobId;
    private String userPhoto;
    private String createTime;
    private String updateTime;
    private String longTime;
    private Integer userStatus;

}
