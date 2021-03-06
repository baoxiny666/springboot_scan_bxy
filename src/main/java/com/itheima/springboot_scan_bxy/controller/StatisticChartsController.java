package com.itheima.springboot_scan_bxy.controller;

import com.itheima.springboot_scan_bxy.service.StatisticChartsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/statistic")
public class StatisticChartsController {
    @Autowired
    private StatisticChartsService statisticChartsService;

    @RequestMapping("/selectDataCard")
    public String  selectDataCard(){
        String DataCards = statisticChartsService.selectDataCard();
        return DataCards;
    }
    @RequestMapping("/selectDataCharts")
    public String ReportRecordsController(String aesData){
        String DataCards = statisticChartsService.selectDataCharts(aesData);
        return DataCards;
    }
}
