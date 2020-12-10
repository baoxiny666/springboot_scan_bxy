package com.itheima.springboot_scan_bxy.mapper;


import com.itheima.springboot_scan_bxy.entity.Sys_Notice;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;


@Repository
public interface SysNoticeMapper {
    @Select("select id," +
            "notice_content noticeContent," +
            "notice_status noticeStatus" +
            " from sys_notice")
    Sys_Notice select();
}
