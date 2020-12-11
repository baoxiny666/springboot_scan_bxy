package com.itheima.springboot_scan_bxy.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.itheima.springboot_scan_bxy.entity.FileList;
import com.itheima.springboot_scan_bxy.mapper.FileListMapper;
import com.itheima.springboot_scan_bxy.service.FileListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.text.SimpleDateFormat;

@Service
public class FileListServiceImpl implements FileListService {

        @Autowired
        FileListMapper fileListMapper;

        @Override
        public String select() {
                JSONArray jsonArray = getFileName();
                return jsonArray.toJSONString();
        }

        public static JSONArray getFileName() {
                String path = "E:/TemplateWenjianjia";
                File f = new File(path);
                if (!f.exists()) {
                        System.out.println(path + " not exists");
                        return null;
                }
                JSONArray fileArray = new JSONArray();
                File fa[] = f.listFiles();
                for (int i = 0; i < fa.length; i++) {
                        FileList fileList = new FileList();
                        File fs = fa[i];
                        if (fs.isDirectory()) {
                                System.out.println(fs.getName() + " [目录]");
                        } else {
                                fileList.setId(i+1);
                                fileList.setFileName(fa[i].getName().substring(0,fa[i].getName().lastIndexOf(".")));
                                fileList.setFileSuffix(fa[i].getName().substring(fa[i].getName().lastIndexOf(".")+1));
                                fileList.setFileSize((fa[i].length()/1024)+"k");
                                fileList.setFileTime(transferTime(fa[i].lastModified()));
                        }
                        fileArray.add(fileList);
                }

                return fileArray;

        }

        public static String transferTime(Object lastModify){
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                return sdf.format(lastModify);
        }

}
