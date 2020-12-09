package com.itheima.springboot_scan_bxy.aop;

import com.itheima.springboot_scan_bxy.bean.DBContextHolder;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DataSourceAop {

    @Pointcut("!@annotation(com.itheima.springboot_scan_bxy.annotation.Master) " +
            "&& (execution(* com.itheima.springboot_scan_bxy.service..*.select*(..)) " +
            "|| execution(* com.itheima.springboot_scan_bxy.service..*.get*(..)))")
    public void readPointcut() {

    }

    @Pointcut("@annotation(com.itheima.springboot_scan_bxy.annotation.Master) " +
            "|| execution(* com.itheima.springboot_scan_bxy.service..*.insert*(..)) " +
            "|| execution(* com.itheima.springboot_scan_bxy..*.add*(..)) " +
            "|| execution(* com.itheima.springboot_scan_bxy..*.update*(..)) " +
            "|| execution(* com.itheima.springboot_scan_bxy..*.edit*(..)) " +
            "|| execution(* com.itheima.springboot_scan_bxy..*.delete*(..)) " +
            "|| execution(* com.itheima.springboot_scan_bxy..*.remove*(..))")
    public void writePointcut() {

    }

    @Before("readPointcut()")
    public void read() {
        DBContextHolder.slave();
    }

    @Before("writePointcut()")
    public void write() {
        DBContextHolder.master();
    }

}
