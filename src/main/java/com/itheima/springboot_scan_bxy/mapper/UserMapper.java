package com.itheima.springboot_scan_bxy.mapper;


import com.itheima.springboot_scan_bxy.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;


@Repository
public interface UserMapper {

    @Select("select * from scan_configs where id = #{id}")
    User select(@Param("id") Integer id);
}

