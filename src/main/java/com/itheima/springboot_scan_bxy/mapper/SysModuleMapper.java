package com.itheima.springboot_scan_bxy.mapper;


import com.itheima.springboot_scan_bxy.entity.Sys_Modules;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface SysModuleMapper {
    @Select("select id," +
            "module_no moduleNo," +
            "module_name moduleName," +
            "module_desc moduleDesc," +
            "module_icon moduleIcon," +
            "module_bg moduleBg," +
            "module_router moduleRouter," +
            "is_permit isPermit," +
            "module_status moduleStatus," +
            "show_order showOrder," +
            "DATE_FORMAT(create_time,'%Y-%m-%d %H:%i:%s') createTime," +
            "DATE_FORMAT(update_time,'%Y-%m-%d %H:%i:%s') updateTime" +
            " from sys_modules")
    List<Sys_Modules> select();
}
