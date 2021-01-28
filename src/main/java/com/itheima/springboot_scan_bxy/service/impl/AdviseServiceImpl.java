package com.itheima.springboot_scan_bxy.service.impl;

import com.itheima.springboot_scan_bxy.entity.TreeMenu;
import com.itheima.springboot_scan_bxy.mapper.AdviseMapper;
import com.itheima.springboot_scan_bxy.service.AdviseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class AdviseServiceImpl implements AdviseService {
    @Autowired
    private AdviseMapper adviseMapper;

    @Override
    public List<TreeMenu> treeMenu(String userId) {
        // 查询出所有的菜单数据集合
        List<TreeMenu> menus = adviseMapper.select(userId);
        // 生成菜单树
        List<TreeMenu> treeMenu = createTree(-1, menus);

        return treeMenu;
    }

    /**
     * 递归生成菜单树
     */
    private List<TreeMenu> createTree(int pid, List<TreeMenu> menus) {
        List<TreeMenu> treeMenu = new ArrayList<>();
        for (TreeMenu menu : menus) {
            if (pid == menu.getFunc_pid()) {
                treeMenu.add(menu);
                menu.setChildren(createTree(menu.getId(), menus));
            }
        }
        return treeMenu;
    }
}
