package com.itheima.springboot_scan_bxy.utils;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class CsvUtil {
    //CSV文件分隔符
    private final static String NEW_LINE_SEPARATOR="\n";
    /** CSV文件列分隔符 */
    private static final String CSV_COLUMN_SEPARATOR = ",";
    /** CSV文件列分隔符 */
    private static final String CSV_RN = "\r\n";

    /**写入csv文件
     * @param headers 列头
     * @param data 数据内容
     * @param filePath 创建的csv文件路径
     * @throws IOException **/
    public static void writeCsvWithHeader(String[] headers, List<Object[]> data, String filePath) {
        //初始化csvformat
        CSVFormat format = CSVFormat.DEFAULT.withHeader(headers);
        try {
            //根据路径创建文件，并设置编码格式
            FileOutputStream fos = new FileOutputStream(filePath);
            OutputStreamWriter osw = new OutputStreamWriter(fos, "GBK");
            //创建CSVPrinter对象
            CSVPrinter printer = new CSVPrinter(osw, format);

            if(null!=data){
                //循环写入数据
                for(Object[] lineData:data){
                    printer.printRecord(lineData);
                }
            }
            printer.flush();
            printer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**写入csv文件
     * @param headers 列头
     * @param data 数据内容
     * @param filePath 创建的csv文件路径
     * @throws IOException **/
    public static void writeCsvWithRecordSeparator(Object[] headers, List<Object[]> data, String filePath){
        //初始化csvformat
        CSVFormat format = CSVFormat.DEFAULT.withRecordSeparator(NEW_LINE_SEPARATOR);
        try {
            //根据路径创建文件，并设置编码格式
            FileOutputStream fos = new FileOutputStream(filePath);
            OutputStreamWriter osw = new OutputStreamWriter(fos, "GBK");
            //创建CSVPrinter对象
            CSVPrinter printer = new CSVPrinter(osw,format);
            //写入列头数据
            printer.printRecord(headers);

            if(null!=data){
                //循环写入数据
                for(Object[] lineData:data){
                    printer.printRecord(lineData);
                }
            }
            printer.flush();
            printer.close();
            System.out.println("CSV文件创建成功,文件路径:"+filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @filePath 文件路径
     */
    public static List<CSVRecord> readCsvParse(String filePath){
        List<CSVRecord> records = new ArrayList<>();
        try {
            FileInputStream in = new FileInputStream(filePath);
            BufferedReader reader = new BufferedReader (new InputStreamReader(in,"GBK"));
            CSVParser parser = CSVFormat.EXCEL.parse(reader);
            records = parser.getRecords();
            parser.close();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            return records;
        }
    }

    /**
     * 自定义字段
     * @filePath 文件路径
     */
    public static List<CSVRecord> readCsvParseWithHeader(String filePath,String[] headers){
        List<CSVRecord> records = new ArrayList<>();
        try {
            FileInputStream in = new FileInputStream(filePath);
            BufferedReader  reader = new BufferedReader (new InputStreamReader(in,"GBK"));
            CSVParser parser = CSVFormat.EXCEL.withHeader(headers).parse(reader);
            records = parser.getRecords();
            /*for (CSVRecord record : parser.getRecords()) {
                System.out.println(record.get("id") + ","
                        + record.get("name") + ","
                        + record.get("code"));
            }*/
            parser.close();
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            return records;
        }
    }



    /**
     * @param colNames 表头部数据
     * @param mapKeys 查找的对应数据
     * @param dataList 集合数据
     */
    public static ByteArrayOutputStream doExport(List<String> colNames, List<String> mapKeys, List<HashMap> dataList) {
        try {
            StringBuffer buf = new StringBuffer();

            // 完成数据csv文件的封装
            // 输出列头
            for (int i = 0; i < colNames.size(); i++) {
                buf.append(colNames.get(i)).append(",");
            }
            buf.append(CSV_RN);

            if (null != dataList) { // 输出数据
                for (int i = 0; i < dataList.size(); i++) {
                    for (int j = 0; j < mapKeys.size(); j++) {
                        String   ziduan = dataList.get(i).get(mapKeys.get(j))==null?"":dataList.get(i).get(mapKeys.get(j)).toString();

                            buf.append(
                                    fieldContentFormat(ziduan.replaceAll("\r|\n", ""))
                             ).append(CSV_COLUMN_SEPARATOR);


                    }
                    buf.append(CSV_RN);
                }
            }
            // 写出响应
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            //OutputStream os = new ByteArrayOutputStream();
            os.write(buf.toString().getBytes("GBK"));
            os.flush();
            os.close();
            return os;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    private static String fieldContentFormat(Object content) {

        if (content == null) {
            return "";
        }

        String notNullContent = content.toString();
        if (notNullContent.contains("\"")) {
            // 处理英文双引号
            notNullContent = notNullContent.replaceAll("\"", "\"\"");
        }

        // 处理英文逗号
        return new StringBuffer("\"").append(notNullContent).append("\"").toString();
    }

    public static HttpHeaders setCsvHeader(String fileName) {
        HttpHeaders headers = new HttpHeaders();
        try {
            // 设置文件后缀
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            String filename = new String(fileName.getBytes("gbk"), "iso8859-1") + ".csv";
            headers.add("Pragma", "public");
            headers.add("Cache-Control", "max-age=30");
            headers.add("Content-Disposition", "attachment;filename="+filename);
            headers.setContentType(MediaType.valueOf("application/vnd.ms-excel;charset=UTF-8"));
        }catch (Exception e){
            e.printStackTrace();
        }
        return headers;
    }

}
