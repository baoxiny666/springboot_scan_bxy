package com.itheima.springboot_scan_bxy.mapper;


import com.itheima.springboot_scan_bxy.entity.Sys_Ad;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface SysAdMapper {
   /* @Select("select id," +
            "ad_group adGroup," +
            "ad_image adImage," +
            "DATE_FORMAT(create_time,'%Y-%m-%d %H:%i:%s') createTime," +
            "DATE_FORMAT(update_time,'%Y-%m-%d %H:%i:%s') updateTime" +
            " from sys_ad")*/
    List<Sys_Ad> select();
}
