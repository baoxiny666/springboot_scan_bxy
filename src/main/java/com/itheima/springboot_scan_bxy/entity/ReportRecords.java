package com.itheima.springboot_scan_bxy.entity;

import lombok.Data;

@Data
public class ReportRecords {
    private Integer id;
    private String area_no;
    private String area_name;
    private Integer depart_id;
    private String depart_name;
    private String manage_user;
    private String manage_user_name;
    private String manage_phone;
    private String desc;
    private String item_ids;
    private String images;
    private StatusConfig statusConfig;
    private String record_user_no;
    private String record_user_name;
    private String record_user_phone;
    private String record_location;
    private String record_location_name;
    private String app_client_id;
    private String create_time;
    private String update_time;

    //开始时间 与 结束时间
    private String start_time;
    private String end_time;
    //搜索内容
    private String search;
    //当前状态
    private String status;
    //序号
    private Integer xh;


}
