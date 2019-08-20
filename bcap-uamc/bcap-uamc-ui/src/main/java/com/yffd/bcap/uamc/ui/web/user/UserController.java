package com.yffd.bcap.uamc.ui.web.user;

import com.yffd.bcap.common.model.result.DataResult;
import com.yffd.bcap.common.web.mvc.controller.WebController;
import com.yffd.bcap.uamc.application.user.service.UserAppService;
import com.yffd.bcap.uamc.domain.model.user.UserData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "用户管理模块")
@RestController
@RequestMapping("/user")
public class UserController extends WebController {
    @Autowired
    private UserAppService userAppService;

    @ApiOperation("添加新用户")
    @PostMapping("/add")
    public DataResult add(@RequestBody UserData userdata) {
        userAppService.addUser(userdata, sysOperator());
        return DataResult.ok();
    }
}
