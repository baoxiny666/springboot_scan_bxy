package com.itheima.springboot_scan_bxy;

import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = MybatisAutoConfiguration.class)
@MapperScan("com.itheima.springboot_scan_bxy.mapper")
public class SpringbootScanBxyApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringbootScanBxyApplication.class, args);

    }

}
