package com.nmw.pss.module.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nmw.pss.common.base.BaseServiceImpl;
import com.nmw.pss.module.system.bean.Employee;
import com.nmw.pss.module.system.dao.EmployeeDao;
import com.nmw.pss.module.system.service.EmployeeService;

@Service
public class EmployeeServiceImpl extends BaseServiceImpl<Employee, EmployeeDao> implements EmployeeService {

	@Autowired
	private EmployeeDao employeeDao;
	
	@Override
	public Employee findEmployeeByLoginName(String loginName) {
		Employee query=new Employee();
		query.setLoginName(loginName);
		List<Employee> employees=employeeDao.selectByModel(query);
		return employees.size()==1?employees.get(0):null;
	}

}
