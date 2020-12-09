package com.itheima.springboot_scan_bxy.mapper;


import com.itheima.springboot_scan_bxy.entity.Scan_Area_Items;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


@Repository
public interface ScanConfigMapper {

    @Insert("insert into scan_area_items(id,area_no,item_name) value(#{id},#{area_no},#{item_name})")
    void add(Map dataMap);

    @Select("select * from scan_area_items")
    List<Scan_Area_Items> select();
}

