package com.yffd.bcap.uamc.ui.web.org;

import com.yffd.bcap.common.model.result.Result;
import com.yffd.bcap.common.model.result.Result;
import com.yffd.bcap.common.model.utils.BcapStringUtils;
import com.yffd.bcap.common.web.mvc.controller.WebController;
import com.yffd.bcap.uamc.application.organization.dto.OrganizationNode;
import com.yffd.bcap.uamc.application.organization.service.OrganizationAppService;
import com.yffd.bcap.uamc.domain.model.organization.OrgData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "机构管理模块")
@RestController
@RequestMapping("/org")
public class OrgController extends WebController {
    @Autowired
    private OrganizationAppService organizationAppService;

    @ApiOperation(value = "查询组织机构树")
    @RequestMapping(value = "/findTree", method = RequestMethod.GET)
    public Result findTree() {
        OrganizationNode tree = organizationAppService.buildTree();
        return Result.ok(tree);
    }

    @ApiOperation(value = "添加新机构")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Result add(@RequestBody OrgData orgData) {
        if (BcapStringUtils.isEmpty(orgData.getParentId()) || "root".equalsIgnoreCase(orgData.getParentId()))
            organizationAppService.addRoot(orgData, sysOperator());
        else
            organizationAppService.addChild(orgData, sysOperator());
        return Result.ok();
    }

    @ApiOperation(value = "修改机构")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Result update(@RequestBody OrgData orgData) {
        organizationAppService.updateOrg(orgData, sysOperator());
        return Result.ok();
    }

    @ApiOperation(value = "删除机构")
    @ApiImplicitParam(name = "orgId", value = "机构ID", paramType = "form", dataTypeClass = String.class)
    @RequestMapping(value = "/delById", method = RequestMethod.POST)
    public Result delById(String orgId) {
        OrgData orgData = new OrgData();
        orgData.setOrgId(orgId);
        organizationAppService.deleteOrg(orgData, sysOperator());
        return Result.ok();
    }

}
