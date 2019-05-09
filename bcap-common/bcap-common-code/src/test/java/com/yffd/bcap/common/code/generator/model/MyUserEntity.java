package com.yffd.bcap.common.code.generator.model;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2018年9月18日 下午4:14:03 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public class MyUserEntity extends BaseEntity {
	private static final long serialVersionUID = -3129761659732564146L;
	private String id;
	private String userCode;		//用户编号
	private String userName;		//用户名称
	private String userStatus;		//用户状态
	private String orgCode;			//机构编号
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserStatus() {
		return userStatus;
	}
	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}
	public String getOrgCode() {
		return orgCode;
	}
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
}

