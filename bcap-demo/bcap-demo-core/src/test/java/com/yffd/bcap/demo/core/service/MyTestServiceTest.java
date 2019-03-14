package com.yffd.bcap.demo.core.service;

import com.yffd.bcap.demo.core.SpringJunitJavaConfig;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


public class MyTestServiceTest extends SpringJunitJavaConfig {

    /*public static void main(String[] args) {
        System.out.println("ggg");
        System.out.println();
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.scan("com.yffd.bcap.bpm.service");
        ctx.refresh();

        MyTestService myTestService = ctx.getBean(MyTestService.class);
        myTestService.myTest();
    }*/

    @Autowired
    private MyTestService myTestService;

    @Test
    public void myTest() {
        myTestService.myTest();
    }
}
