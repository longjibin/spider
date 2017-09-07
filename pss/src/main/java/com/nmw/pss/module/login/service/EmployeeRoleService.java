package com.nmw.pss.module.login.service;

import java.util.List;

import com.nmw.pss.common.base.BaseService;
import com.nmw.pss.module.login.bean.EmployeeRole;

public interface EmployeeRoleService extends BaseService<EmployeeRole> {

	public List<EmployeeRole> findByEmployeeId(String employeeId);
}
