package com.itheima.springboot_scan_bxy.utils;

import com.github.pagehelper.PageInfo;
import com.itheima.springboot_scan_bxy.entity.PageResult;
import com.itheima.springboot_scan_bxy.entity.ReportRecords;

public class PageUtils {
    public static PageResult getPageResult(ReportRecords reportRecords, PageInfo<?> pageInfo) {
        PageResult pageResult = new PageResult();
        pageResult.setPageNum(pageInfo.getPageNum());
        pageResult.setPageSize(pageInfo.getPageSize());
        pageResult.setTotalSize(pageInfo.getTotal());
        pageResult.setTotalPages(pageInfo.getPages());
        pageResult.setContent(pageInfo.getList());
        return pageResult;
    }
}
