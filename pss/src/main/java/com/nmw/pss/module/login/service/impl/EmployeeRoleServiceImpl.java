package com.nmw.pss.module.login.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nmw.pss.common.base.BaseServiceImpl;
import com.nmw.pss.module.login.bean.EmployeeRole;
import com.nmw.pss.module.login.dao.EmployeeRoleDao;
import com.nmw.pss.module.login.service.EmployeeRoleService;

@Service
public class EmployeeRoleServiceImpl extends BaseServiceImpl<EmployeeRole, EmployeeRoleDao> implements EmployeeRoleService {

	@Autowired
	private EmployeeRoleDao employeeRoleDao;
	
	@Override
	public List<EmployeeRole> findByEmployeeId(String employeeId) {
		EmployeeRole query=new EmployeeRole();
		query.setEmployeeId(employeeId);
		return employeeRoleDao.selectByModel(query);
	}

}
