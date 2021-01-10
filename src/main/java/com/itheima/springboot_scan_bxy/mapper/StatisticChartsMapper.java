package com.itheima.springboot_scan_bxy.mapper;


import com.itheima.springboot_scan_bxy.entity.ReportRecords;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;


@Repository
public interface StatisticChartsMapper {
    @Select("select   count(1) cc,bsc.enname,  " +
            " bsc.name status, " +
            " sr.record_status " +
            " from scan_records sr, " +
            " back_status_config bsc " +
            " where sr.record_status = bsc.flag " +
            " group by status,record_status,enname " +
            " order by record_status asc ")
    List<HashMap> selectDataCard();

    @Select("select * from back_department order by id")
    List<HashMap> selectDepartConfig();

    HashMap selectDataCharts(ReportRecords reportRecords);
}


