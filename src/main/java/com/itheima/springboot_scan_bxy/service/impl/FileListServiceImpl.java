package com.itheima.springboot_scan_bxy.service.impl;

import com.itheima.springboot_scan_bxy.mapper.FileListMapper;
import com.itheima.springboot_scan_bxy.service.FileListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FileListServiceImpl implements FileListService {

        @Autowired
        FileListMapper fileListMapper;

        @Override
        public String select() {
                fileListMapper.select();
                return null;
        }




}
