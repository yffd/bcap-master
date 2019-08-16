package com.yffd.bcap.uamc.ui.web.test;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "swagger测试模块")
@RestController
@RequestMapping("/test")
public class SwaggerController {

    @RequestMapping(value = "/swagger", method = RequestMethod.POST)
    public String swagger() {
        return "swagger";
    }

    @ApiOperation(value = "swagger参数测试")
    @RequestMapping(value = "/swagger/param", method = RequestMethod.GET)
    public TestModel swaggerParam(TestModel model) {
        return model;
    }
}
