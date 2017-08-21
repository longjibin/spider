package com.nmw.pss.module.login.service;

import com.nmw.pss.common.base.BaseService;
import com.nmw.pss.module.login.bean.Employee;

public interface EmployeeService extends BaseService<Employee>{

	/**
	 * 查询指定登录名的用户
	 * @param loginName
	 * @return
	 */
	public Employee findEmployeeByLoginName(String loginName);

}
