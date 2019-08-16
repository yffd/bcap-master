package com.yffd.bcap.uamc.ui.web.test;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="演示类", description="请求参数类")
public class TestModel {
    @ApiModelProperty(value="主键" ,required=true, example="1122")
    private String id;
    @ApiModelProperty(value="名称" ,required=true, example="张三")
    private String name;
    private String address;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
