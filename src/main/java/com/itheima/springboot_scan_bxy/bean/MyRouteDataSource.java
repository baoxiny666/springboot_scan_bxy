package com.itheima.springboot_scan_bxy.bean;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.lang.Nullable;

public class MyRouteDataSource extends AbstractRoutingDataSource{
    @Nullable
    @Override
    protected Object determineCurrentLookupKey(){
        return DBContextHolder.get();
    }
}
