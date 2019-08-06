package com.yffd.bcap.uamc.ui.web.test;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {

    @RequestMapping("/index")
    public String index() {
        System.out.println("index>>>>>>>>>");
        return "index";
    }

    @RequestMapping("/list")
    public List<TestModel> list() {
        List<TestModel> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            TestModel model = new TestModel();
            model.setId(i + "");
            model.setName("名称-" + i);
            list.add(model);
        }
        return list;
    }

}
