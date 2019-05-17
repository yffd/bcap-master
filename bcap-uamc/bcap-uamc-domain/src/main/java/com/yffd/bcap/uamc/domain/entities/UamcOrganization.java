package com.yffd.bcap.uamc.domain.entities;

import com.yffd.bcap.common.domain.model.entity.EntityObject;
import lombok.Data;

@Data
public class UamcOrganization extends EntityObject {
    private static final long serialVersionUID = 2689173809583888360L;
    private String orgId;//机构ID
    private String orgName;//机构名称
    private String shotName;//机构简称
    private String parentId;//父机构ID
    private String orgType;//类型，企业=company、分公司=branch、部门=dept
    private String num;//排序号
    private String orgPath;//机构路径
    private String remark;//描述
}
