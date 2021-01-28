package com.itheima.springboot_scan_bxy.service;

import com.itheima.springboot_scan_bxy.entity.TreeMenu;

import java.util.List;

public interface AdviseService {
    public List<TreeMenu> treeMenu(String userId);
}
