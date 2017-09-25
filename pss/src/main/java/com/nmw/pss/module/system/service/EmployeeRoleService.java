package com.nmw.pss.module.system.service;

import java.util.List;

import com.nmw.pss.common.base.BaseService;
import com.nmw.pss.module.system.bean.EmployeeRole;

public interface EmployeeRoleService extends BaseService<EmployeeRole> {

	public List<EmployeeRole> findByEmployeeId(String employeeId);

	/**
	 * 根据员工id和角色id删除员工和角色的关联
	 * @param employeeRole
	 */
	public void removeByEidAndRid(EmployeeRole employeeRole);
}
