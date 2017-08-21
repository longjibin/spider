package com.nmw.pss.module.login.bean;

import com.nmw.pss.common.base.BaseEntity;

/**
 * 员工角色实体
 * 
 * @author Administrator
 *
 */
public class EmployeeRole extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//员工id
	private String employeeId;

	//角色id
	private String roleId;

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	@Override
	public String toString() {
		return "EmployeeRole [employeeId=" + employeeId + ", roleId=" + roleId + ", id=" + id + ", createTime="
				+ createTime + ", updateTime=" + updateTime + ", createUserId=" + createUserId + ", updateUserId="
				+ updateUserId + ", remark=" + remark + ", delSign=" + delSign + "]";
	}

}
