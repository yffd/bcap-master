package com.yffd.bcap.common.code.generator.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description  简单描述该类的功能（可选）.
 * @Date		 2018年9月18日 上午10:38:46 <br/>
 * @author       zhangST
 * @version		 1.0
 * @since		 JDK 1.7+
 * @see 	 
 */
public class BaseEntity implements Serializable {
	private static final long serialVersionUID = 1608822556108291211L;
	
	private Integer version;	// 版本号
	private String delFlag;		//逻辑删除标识：0=未删除、1=已删除
	private String createBy;	// 创建人
	private Date createTime;	// 创建时间
	private String updateBy;	// 最后修改人
	private Date updateTime;	// 最后修改时间
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
	public String getDelFlag() {
		return delFlag;
	}
	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
}

