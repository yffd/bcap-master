package com.yffd.bcap.uamc.ui.web.user;

import com.yffd.bcap.common.model.page.PageData;
import com.yffd.bcap.common.model.result.Result;
import com.yffd.bcap.common.web.mvc.controller.WebController;
import com.yffd.bcap.uamc.application.user.dto.UserCondition;
import com.yffd.bcap.uamc.application.user.query.UserQry;
import com.yffd.bcap.uamc.application.user.service.UserAppService;
import com.yffd.bcap.uamc.domain.model.permission.PermissionData;
import com.yffd.bcap.uamc.domain.model.role.data.RoleData;
import com.yffd.bcap.uamc.domain.model.user.UserData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashSet;

@Api(tags = "用户管理模块")
@RestController
@RequestMapping("/user")
public class UserController extends WebController {
    @Autowired
    private UserAppService userAppService;
    @Autowired
    private UserQry userQry;

    @ApiOperation(value = "查询用户列表（分页）")
    @GetMapping(value = "/findPage")
    public Result findPage(UserCondition condition, Integer pageNum, Integer pageSize) {
        PageData<UserData> page = userQry.findPage(condition, pageInfo(pageNum, pageSize));
        return Result.ok(page);
    }

    @ApiOperation(value = "查看相关联的角色列表（分页）")
    @GetMapping(value = "/findRltRole")
    public Result findRltRolePage(String userId, Integer pageNum, Integer pageSize) {
        PageData<RoleData> page = userQry.findRolesByUserId(userId, pageInfo(pageNum, pageSize));
        return Result.ok(page);
    }

    @ApiOperation(value = "查看相关联的角色（扩展组）列表（分页）")
    @GetMapping(value = "/findRltSecondRole")
    public Result findRltSecondRolePage(String userId, Integer pageNum, Integer pageSize) {
        PageData<RoleData> page = userQry.findSecondRolesByUserId(userId, pageInfo(pageNum, pageSize));
        return Result.ok(page);
    }

    @ApiOperation(value = "查看相关联的权限列表（分页）")
    @GetMapping(value = "/findRltPms")
    public Result findRltPmsPage(String userId, Integer pageNum, Integer pageSize) {
        PageData<PermissionData> page = userQry.findPermissionsByUserId(userId, pageInfo(pageNum, pageSize));
        return Result.ok(page);
    }

    @ApiOperation(value = "查看相关联的权限（扩展组）列表（分页）")
    @GetMapping(value = "/findRltSecondPms")
    public Result findRltSecondPmsPage(String userId, Integer pageNum, Integer pageSize) {
        PageData<PermissionData> page = userQry.findSecondPermissionsByUserId(userId, pageInfo(pageNum, pageSize));
        return Result.ok(page);
    }



    @ApiOperation("添加新用户")
    @PostMapping("/add")
    public Result add(@RequestBody UserData userdata) {
        userAppService.addUser(userdata, sysOperator());
        return Result.ok();
    }

    @ApiOperation("修改角色")
    @PostMapping("/update")
    public Result update(@RequestBody UserData userdata) {
        userAppService.updateUser(userdata, sysOperator());
        return Result.ok();
    }

    @ApiOperation("删除角色")
    @ApiImplicitParam(name = "userId", value = "用户ID", paramType = "form", dataTypeClass = String.class)
    @PostMapping("/delById")
    public Result delById(String userId) {
        userAppService.deleteUser(userId, sysOperator());
        return Result.ok();
    }

    @ApiOperation("删除角色-批量")
    @ApiImplicitParam(name = "userIds", value = "用户ID集合", paramType = "form", dataTypeClass = String[].class)
    @PostMapping("/delByIds")
    public Result delById(String[] userIds) {
        userAppService.deleteBatch(new HashSet<>(Arrays.asList(userIds)), sysOperator());
        return Result.ok();
    }

    @ApiOperation("指派组")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户ID", paramType = "form", dataTypeClass = String.class),
            @ApiImplicitParam(name = "groupIds", value = "组ID集合", paramType = "form", dataTypeClass = String[].class)
    })
    @PostMapping("/assignToGroup")
    public Result assignToGroup(String userId, String[] groupIds) {
        UserData userData = new UserData();
        userData.setUserId(userId);
        userAppService.assignToGroups(new HashSet<>(Arrays.asList(groupIds)), userData, sysOperator());
        return Result.ok();
    }

    @ApiOperation("解除已指派组")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户ID", paramType = "form", dataTypeClass = String.class),
            @ApiImplicitParam(name = "groupIds", value = "组ID集合", paramType = "form", dataTypeClass = String[].class)
    })
    @PostMapping("/deleteRltGroup")
    public Result deleteRltGroup(String userId, String[] groupIds) {
        UserData userData = new UserData();
        userData.setUserId(userId);
        userAppService.deleteRltGroups(new HashSet<>(Arrays.asList(groupIds)), userData, sysOperator());
        return Result.ok();
    }

    @ApiOperation("指派角色")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户ID", paramType = "form", dataTypeClass = String.class),
            @ApiImplicitParam(name = "roleIds", value = "角色ID集合", paramType = "form", dataTypeClass = String[].class)
    })
    @PostMapping("/assignToRole")
    public Result assignToRole(String userId, String[] roleIds) {
        UserData userData = new UserData();
        userData.setUserId(userId);
        userAppService.assignToRoles(new HashSet<>(Arrays.asList(roleIds)), userData, sysOperator());
        return Result.ok();
    }

    @ApiOperation("解除已指派角色")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户ID", paramType = "form", dataTypeClass = String.class),
            @ApiImplicitParam(name = "roleIds", value = "角色ID集合", paramType = "form", dataTypeClass = String[].class)
    })
    @PostMapping("/deleteRltRole")
    public Result deleteRltRole(String userId, String[] roleIds) {
        UserData userData = new UserData();
        userData.setUserId(userId);
        userAppService.deleteRltRoles(new HashSet<>(Arrays.asList(roleIds)), userData, sysOperator());
        return Result.ok();
    }

    @ApiOperation("指派权限")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户ID", paramType = "form", dataTypeClass = String.class),
            @ApiImplicitParam(name = "pmsIds", value = "权限ID集合", paramType = "form", dataTypeClass = String[].class)
    })
    @PostMapping("/assignToPms")
    public Result assignToPms(String userId, String[] pmsIds) {
        UserData userData = new UserData();
        userData.setUserId(userId);
        userAppService.assignToPermissions(new HashSet<>(Arrays.asList(pmsIds)), userData, sysOperator());
        return Result.ok();
    }

    @ApiOperation("解除已指派权限")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户ID", paramType = "form", dataTypeClass = String.class),
            @ApiImplicitParam(name = "pmsIds", value = "权限ID集合", paramType = "form", dataTypeClass = String[].class)
    })
    @PostMapping("/deleteRltPms")
    public Result deleteRltPms(String userId, String[] pmsIds) {
        UserData userData = new UserData();
        userData.setUserId(userId);
        userAppService.deleteRltPermissions(new HashSet<>(Arrays.asList(pmsIds)), userData, sysOperator());
        return Result.ok();
    }

}
