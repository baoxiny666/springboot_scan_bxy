package com.itheima.springboot_scan_bxy.controller;


import com.alibaba.fastjson.JSONObject;
import com.itheima.springboot_scan_bxy.annotation.Excel;
import com.itheima.springboot_scan_bxy.entity.ReportRecords_Excel;
import com.itheima.springboot_scan_bxy.service.ReportRecordsService;
import com.itheima.springboot_scan_bxy.utils.AesUtil;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/report")
public class ReportRecordsController {
    @Autowired
    private ReportRecordsService reportRecordsService;

    //查询部门分厂信息
    @RequestMapping("/departarea")
    public String  submenu(){
        String SubMenuData  = reportRecordsService.submenu();
        return SubMenuData;
    }
    //查询状态
    @RequestMapping("/status")
    public String  status(){
        String StatusData  = reportRecordsService.status();
        return StatusData;
    }

    @RequestMapping("/select")
    public String  select(String aesData){
        String ReportsData  = reportRecordsService.select(aesData);
        return ReportsData;
    }

    @RequestMapping("/selectDetail")
    public String  selectDetail(String aesData){
        String ReportsDetailData  = reportRecordsService.selectDetail(aesData);
        return ReportsDetailData;
    }

    //导出excell
    @RequestMapping("/export")
    public void  export(String aesData, HttpServletResponse response) throws IOException {
        //定义展示excel的标题
        List<String> excelHeader = new ArrayList<>();
        //定义excel标题对应的英文字段
        List<String> excelHeaderEn = new ArrayList<>();

        //获取参数值
        String aesDeData = AesUtil.decrypt(aesData,AesUtil.KEY);
        JSONObject aesDeDateObj=JSONObject.parseObject(aesDeData);

        //展开配置内容
        ReportRecords_Excel reportRecords_excel = new ReportRecords_Excel();
        Class<? extends ReportRecords_Excel> objclass = ReportRecords_Excel.class;
        Field[] at = objclass.getDeclaredFields();
        for (Field fd : at) {
            if (fd.isAnnotationPresent(Excel.class)) {
                String column_en=fd.getName();
                Excel d = fd.getAnnotation(Excel.class);
                String column_zhcn = d.name();

                excelHeader.add(column_zhcn);
                excelHeaderEn.add(column_en);

            }
        }
        //定义POI类
        HSSFWorkbook workbook = new HSSFWorkbook();

        HSSFSheet sheet = workbook.createSheet("隐患上报记录");
        sheet.setDefaultColumnWidth(25);
        List<HashMap> reportRecords_ExcelList = reportRecordsService.selectExport(aesData);

        // 设置要导出的文件的名字
        String fileName = "隐患上报记录"  + new Date().toString() + ".xls";

        // 新增数据行，并且设置单元格数据
        int rowNum = 1;

        //excel表中第一行的表头
        HSSFRow row = sheet.createRow(0);
        for(int i=0;i<excelHeader.size();i++){
            HSSFCell cell = row.createCell(i);
            HSSFRichTextString text = new HSSFRichTextString(excelHeader.get(i));
            cell.setCellValue(text);
        }

        //在表中存放查询到的数据放入对应的列
       for (HashMap item : reportRecords_ExcelList) {
           HSSFRow row1 = sheet.createRow(rowNum);
           for (int i = 0; i < excelHeaderEn.size(); i++) {
               row1.createCell(i).setCellValue(item.get(excelHeaderEn.get(i))==null?"":item.get(excelHeaderEn.get(i)).toString());
           }
           rowNum++;
       }

        OutputStream output;
        output = response.getOutputStream();
        //清空缓存
        response.reset();
        //定义浏览器响应表头，顺带定义下载名，比如students(中文名需要转义)
        response.setHeader("Content-disposition", "attachment;filename="+new String(fileName.getBytes(),"iso-8859-1")+".xls");
        //定义下载的类型，标明是excel文件
        response.setContentType("application/vnd.ms-excel");
        //这时候把创建好的excel写入到输出流
        workbook.write(output);
        //养成好习惯，出门记得随手关门
        output.close();
    }








}
