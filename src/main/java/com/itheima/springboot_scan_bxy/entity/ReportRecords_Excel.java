package com.itheima.springboot_scan_bxy.entity;

import com.itheima.springboot_scan_bxy.annotation.Excel;
import lombok.Data;

@Data
public class ReportRecords_Excel {
    @Excel(name="区域编号")
    private String area_no;
    @Excel(name="区域名称")
    private String area_name;

    @Excel(name="部门名称")
    private String depart_name;

    @Excel(name="负责人编号")
    private String manage_user;

    @Excel(name="负责人姓名")
    private String manage_user_name;

    @Excel(name="负责人手机号")
    private String manage_phone;

    @Excel(name="隐患描述")
    private String desc;

    @Excel(name="记录状态")
    private String status;


    @Excel(name="添加记录用户")
    private String record_user_no;

    @Excel(name="记录用户名称")
    private String record_user_name;

    @Excel(name="用户手机号")
    private String record_user_phone;

    @Excel(name="记录提交地理坐标")
    private String record_location;

    @Excel(name="记录提交地理坐标名称")
    private String record_location_name;

    @Excel(name="创建时间")
    private String create_time;

    @Excel(name="更新时间")
    private String update_time;





}
