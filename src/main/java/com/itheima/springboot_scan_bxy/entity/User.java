package com.itheima.springboot_scan_bxy.entity;

import lombok.Data;

@Data
public class User {
    private Integer id;
    private String userName;
    private String passWord;
    private String name;

    private DepartMent departMent;
    private Sys_Post sysPost;

    private String userPhone;
    private String postId;
    private String userPhoto;
    private String createTime;
    private String updateTime;
    private String loginTime;
    private Integer userStatus;

}
