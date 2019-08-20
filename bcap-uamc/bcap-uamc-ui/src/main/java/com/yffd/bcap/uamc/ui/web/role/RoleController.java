package com.yffd.bcap.uamc.ui.web.role;

import com.alibaba.fastjson.JSON;
import com.yffd.bcap.common.model.exception.CheckException;
import com.yffd.bcap.common.model.page.PageData;
import com.yffd.bcap.common.model.result.DataResult;
import com.yffd.bcap.common.model.utils.BcapStringUtils;
import com.yffd.bcap.common.web.mvc.controller.WebController;
import com.yffd.bcap.uamc.application.role.dto.RoleConditon;
import com.yffd.bcap.uamc.application.role.query.RoleQry;
import com.yffd.bcap.uamc.application.role.service.RoleAppService;
import com.yffd.bcap.uamc.domain.model.group.GroupData;
import com.yffd.bcap.uamc.domain.model.permission.PermissionData;
import com.yffd.bcap.uamc.domain.model.role.data.RoleData;
import com.yffd.bcap.uamc.domain.model.user.UserData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.HashSet;

@Api(tags = "角色管理模块")
@RestController
@RequestMapping("/role")
public class RoleController extends WebController {
    @Autowired
    private RoleAppService roleAppService;
    @Autowired
    private RoleQry roleQry;

    @ApiOperation(value = "查询角色列表（分页）")
    @RequestMapping(value = "/findPage", method = RequestMethod.GET)
    public DataResult findPage(RoleConditon conditon, Integer pageNum, Integer pageSize) {
        PageData<RoleData> page = roleQry.findPage(conditon, pageInfo(pageNum, pageSize));
        return DataResult.ok(page);
    }

    @ApiOperation(value = "查看相关联的组列表（分页）")
    @RequestMapping(value = "/findRltGroup", method = RequestMethod.GET)
    public DataResult findRltGroup(String roleId, Integer pageNum, Integer pageSize) {
        if (BcapStringUtils.isEmpty(roleId)) return DataResult.fail("角色ID为空");
        PageData<GroupData> page = roleQry.findGroupsByRoleId(roleId, pageInfo(pageNum, pageSize));
        return DataResult.ok(page);
    }

    @ApiOperation(value = "查看相关联的权限列表（分页）")
    @RequestMapping(value = "/findRltPms", method = RequestMethod.GET)
    public DataResult findRltPms(String roleId, Integer pageNum, Integer pageSize) {
        if (BcapStringUtils.isEmpty(roleId)) return DataResult.fail("角色ID为空");
        PageData<PermissionData> page = roleQry.findPermissionsByRoleId(roleId, pageInfo(pageNum, pageSize));
        return DataResult.ok(page);
    }

    @ApiOperation(value = "查看相关联的用户列表（分页）")
    @RequestMapping(value = "/findRltUser", method = RequestMethod.GET)
    public DataResult findRltUser(String roleId, Integer pageNum, Integer pageSize) {
        if (BcapStringUtils.isEmpty(roleId)) return DataResult.fail("角色ID为空");
        PageData<UserData> page = roleQry.findUsersByRoleId(roleId, pageInfo(pageNum, pageSize));
        return DataResult.ok(page);
    }



    @ApiOperation(value = "添加新角色")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public DataResult add(@RequestBody RoleData roleData) {
        System.out.println(JSON.toJSONString(roleData));
        roleAppService.addRole(roleData, sysOperator());
        return DataResult.ok();
    }

    @ApiOperation(value = "修改角色")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public DataResult update(@RequestBody RoleData roleData) {
        roleAppService.updateRole(roleData, sysOperator());
        return DataResult.ok();
    }

    @ApiOperation(value = "删除角色")
    @ApiImplicitParam(name = "roleId", value = "角色ID", paramType = "form", dataTypeClass = String.class)
    @RequestMapping(value = "/delById", method = RequestMethod.POST)
    public DataResult delById(String roleId) {
        roleAppService.deleteRole(roleId, sysOperator());
        return DataResult.ok();
    }

    @ApiOperation(value = "删除角色-批量删除")
    @ApiImplicitParam(name = "roleIds", value = "角色ID集合", paramType = "form", dataTypeClass = String[].class)
    @RequestMapping(value = "/delByIds", method = RequestMethod.POST)
    public DataResult delByIds(String[] roleIds) {
        roleAppService.deleteBatch(new HashSet<>(Arrays.asList(roleIds)), sysOperator());
        return DataResult.ok();
    }

    @ApiOperation(value = "激活角色")
    @ApiImplicitParam(name = "roleId", value = "角色ID", paramType = "form", dataTypeClass = String.class)
    @RequestMapping(value = "/active", method = RequestMethod.POST)
    public DataResult active(String roleId) {
        roleAppService.activeRole(roleId, sysOperator());
        return DataResult.ok();
    }

    @ApiOperation(value = "禁用角色")
    @ApiImplicitParam(name = "roleId", value = "角色ID", paramType = "form", dataTypeClass = String.class)
    @RequestMapping(value = "/deactive", method = RequestMethod.POST)
    public DataResult deactive(String roleId) {
        roleAppService.deactiveRole(roleId, sysOperator());
        return DataResult.ok();
    }

    @ApiOperation(value = "指派组")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roleId", value = "角色ID", paramType = "form", dataTypeClass = String.class),
            @ApiImplicitParam(name = "groupIds", value = "组ID集合", paramType = "form", dataTypeClass = String[].class)
    })
    @RequestMapping(value = "/assignToGroups", method = RequestMethod.POST)
    public DataResult assignToGroups(String roleId, String[] groupIds) {
        roleAppService.assignToGroups(roleId, new HashSet<>(Arrays.asList(groupIds)), sysOperator());
        return DataResult.ok();
    }

    @ApiOperation(value = "解除已指派组")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roleId", value = "角色ID", paramType = "form", dataTypeClass = String.class),
            @ApiImplicitParam(name = "groupIds", value = "组ID集合", paramType = "form", dataTypeClass = String[].class)
    })
    @RequestMapping(value = "/deleteRltGroups", method = RequestMethod.POST)
    public DataResult deleteRltGroups(String roleId, String[] groupIds) {
        roleAppService.deleteRltGroups(roleId, new HashSet<>(Arrays.asList(groupIds)), sysOperator());
        return DataResult.ok();
    }

    @ApiOperation(value = "分配权限")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roleId", value = "角色ID", paramType = "form", dataTypeClass = String.class),
            @ApiImplicitParam(name = "pmsIds", value = "权限ID集合", paramType = "form", dataTypeClass = String[].class)
    })
    @RequestMapping(value = "/assignToPermissions", method = RequestMethod.POST)
    public DataResult assignToPermissions(String roleId, String[] pmsIds) {
        roleAppService.assignToPermissions(roleId, new HashSet<>(Arrays.asList(pmsIds)), sysOperator());
        return DataResult.ok();
    }

    @ApiOperation(value = "解除已指派权限")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roleId", value = "角色ID", paramType = "form", dataTypeClass = String.class),
            @ApiImplicitParam(name = "pmsIds", value = "权限ID集合", paramType = "form", dataTypeClass = String[].class)
    })
    @RequestMapping(value = "/deleteRltPermissions", method = RequestMethod.POST)
    public DataResult deleteRltPermissions(String roleId, String[] pmsIds) {
        roleAppService.deleteRltPermissions(roleId, new HashSet<>(Arrays.asList(pmsIds)), sysOperator());
        return DataResult.ok();
    }

}
