package com.yffd.bcap.demo.core;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

//指定测试类的运行者
@RunWith(SpringJUnit4ClassRunner.class)
//指定spring配置类
@ContextConfiguration(classes={ApplicationContextJavaConfig.class})
public class SpringJunitJavaConfig {
}
