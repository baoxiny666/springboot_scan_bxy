package com.itheima.springboot_scan_bxy.mapper;


import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


@Repository
public interface ReportRecordsMapper {
    @Select("")
    List<Map> select();
}
