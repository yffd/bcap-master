package com.yffd.bcap.uamc.ui.web.role;

import com.yffd.bcap.common.model.result.DataResult;
import com.yffd.bcap.common.web.mvc.controller.WebController;
import com.yffd.bcap.uamc.application.role.service.RoleAppService;
import com.yffd.bcap.uamc.domain.model.role.data.RoleData;
import com.yffd.bcap.uamc.domain.model.role.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "角色管理模块")
@RestController
@RequestMapping("/role")
public class RoleController extends WebController {
    @Autowired
    private RoleAppService roleAppService;

    @ApiOperation(value = "新角色添加")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public DataResult add(RoleData roleData) {
        roleAppService.addRole(roleData, null);
        return DataResult.ok();
    }

    @ApiOperation(value = "根据角色ID修改角色信息")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public DataResult update(RoleData roleData) {

        return DataResult.ok();
    }

    @ApiOperation(value = "根据角色ID删除角色信息")
    @ApiImplicitParam(name = "roleId", value = "角色ID", paramType = "query", dataTypeClass = String.class)
    @RequestMapping(value = "/delById", method = RequestMethod.POST)
    public DataResult delById(String roleId) {

        return DataResult.ok();
    }

    @ApiOperation(value = "根据角色ID删除角色信息-批量删除")
    @ApiImplicitParam(name = "roleIds", value = "角色ID集合", paramType = "query", dataTypeClass = List.class)
    @RequestMapping(value = "/delByIds", method = RequestMethod.POST)
    public DataResult delByIds(List<String> roleIds) {

        return DataResult.ok();
    }
}
