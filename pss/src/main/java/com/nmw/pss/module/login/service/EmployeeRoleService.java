package com.nmw.pss.module.login.service;

import java.util.List;

import com.nmw.pss.common.base.BaseService;
import com.nmw.pss.module.login.bean.EmployeeRole;

public interface EmployeeRoleService extends BaseService<EmployeeRole> {

	/**
	 * 查询指定员工id的员工角色关系集合
	 * 员工:角色（1：N）
	 * @param id
	 * @return
	 */
	List<EmployeeRole> findByEmployeeId(String employeeId);
	
}
