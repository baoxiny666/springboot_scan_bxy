package com.itheima.springboot_scan_bxy.mapper;


import com.itheima.springboot_scan_bxy.entity.FileList;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface FileListMapper {
    @Select("")
    List<FileList> select();
}
