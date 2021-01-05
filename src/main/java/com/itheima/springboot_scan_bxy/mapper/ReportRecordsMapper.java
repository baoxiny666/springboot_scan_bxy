package com.itheima.springboot_scan_bxy.mapper;


import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;


@Repository
public interface ReportRecordsMapper {
    @Select("select * from scan_records")
    List<HashMap> select();

    @Select("select area_no,area_name,depart_id " +
            "from scan_area area where  depart_id = #{depart_id}")
    List<HashMap> submenuArea(@Param("depart_id") String depart_id);

    @Select("select area.depart_id," +
                    "depart.depart_name " +
            "from scan_area area " +
            "left join sys_department  depart " +
            "on depart.id = area.depart_id " +
            "group by area.depart_id " +
            "order by area.depart_id asc ")
    List<HashMap> submenuDepart();
}


