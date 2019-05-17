package com.yffd.bcap.common.domain.model.login;

import com.yffd.bcap.common.domain.model.valobj.ValueObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginInfo implements ValueObject {
    private static final long serialVersionUID = 3779269730581980785L;
    private String userId;
    private String userName;
    private String orgId;
    private String orgName;
    private Set<String> roles;
    private Set<String> resources;
}
