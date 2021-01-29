package com.itheima.springboot_scan_bxy.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.itheima.springboot_scan_bxy.entity.TreeMenu;
import com.itheima.springboot_scan_bxy.service.AdviseService;
import com.itheima.springboot_scan_bxy.utils.AesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/advise")
public class AdviseController {
    @Autowired
    private AdviseService adviseService;

    @RequestMapping("/treemenu")
    public String treeMenu(String aesData){
        String aesDeData = AesUtil.decrypt(aesData,AesUtil.KEY);
        JSONObject aesDeDateObj=JSONObject.parseObject(aesDeData);
        String userId  = aesDeDateObj.get("userId").toString();
        List<TreeMenu> treeMenu=  adviseService.treeMenu(userId);
        JSONArray jsonArray = new JSONArray();
        String s = jsonArray.toJSONString(treeMenu);
        return s;
    }
}
