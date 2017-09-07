package com.nmw.pss.module.login.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nmw.pss.common.base.BaseServiceImpl;
import com.nmw.pss.common.utils.UserUtils;
import com.nmw.pss.module.login.bean.Employee;
import com.nmw.pss.module.login.bean.EmployeeRole;
import com.nmw.pss.module.login.dao.EmployeeDao;
import com.nmw.pss.module.login.dao.EmployeeRoleDao;
import com.nmw.pss.module.login.service.EmployeeService;

@Service
public class EmployeeServiceImpl extends BaseServiceImpl<Employee, EmployeeDao> implements EmployeeService {

	@Autowired
	private EmployeeDao employeeDao;
	@Autowired
	private EmployeeRoleDao employeeRoleDao;
	
	@Override
	public Employee findEmployeeByLoginName(String loginName) {
		Employee query=new Employee();
		query.setLoginName(loginName);
		List<Employee> employees=employeeDao.selectByModel(query);
		return employees.size()==1?employees.get(0):null;
	}

	@Override
	public void save(Employee t) {
		//保存员工信息
		Date date=new Date();
		Employee current=UserUtils.getCurrentUser();
		t.setUpdateTime(date);
		t.setUpdateUserId(current.getId());
		if(StringUtils.isBlank(t.getId())){
			t.setId(UUID.randomUUID().toString().replace("-", ""));
			t.setCreateTime(date);
			t.setCreateUserId(current.getId());
			dao.insert(t);
		}else{
			dao.updateById(t);
		}
		
		//解析选择的角色集合
		String[] roleIdArray=t.getRoleIds().split(",");
		//删除员工的角色关联
		EmployeeRole query=new EmployeeRole();
		query.setEmployeeId(t.getId());
		employeeRoleDao.deleteByModel(query);
		
		//新增员工的角色关联
		for (String roleId : roleIdArray) {
			query.setId(UUID.randomUUID().toString().replace("-", ""));
			query.setUpdateTime(date);
			query.setUpdateUserId(current.getId());
			query.setCreateTime(date);
			query.setCreateUserId(current.getId());
			query.setRoleId(roleId);
			employeeRoleDao.insert(query);
		}
	}
	
}
