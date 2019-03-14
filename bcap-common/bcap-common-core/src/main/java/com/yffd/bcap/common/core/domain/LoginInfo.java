package com.yffd.bcap.common.core.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Set;

@Data
public class LoginInfo implements Serializable {
    private static final long serialVersionUID = 955376471798344564L;
    private String userId;
    private String userName;
    private String orgId;
    private String orgName;
    private Set<String> roles;
    private Set<String> pms;

}
