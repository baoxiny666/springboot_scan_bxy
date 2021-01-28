package com.itheima.springboot_scan_bxy.mapper;

import com.itheima.springboot_scan_bxy.entity.TreeMenu;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface AdviseMapper {
    @Select(" SELECT f.id,f.func_name,f.func_url,f.redirect_url,f.func_pid,f.func_design " +
            " FROM back_user_role ur " +
            " join back_function_role fr on ur.role_id = fr.role_id " +
            " join back_manage_function f on f.id = fr.func_id " +
            " where  ur.user_id = #{userId} " +
            " order by f.id")
    List<TreeMenu> select(@Param("userId") String userId);
}
