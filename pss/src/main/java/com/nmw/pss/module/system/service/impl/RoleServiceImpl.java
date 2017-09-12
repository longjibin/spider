package com.nmw.pss.module.system.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nmw.pss.common.base.BaseServiceImpl;
import com.nmw.pss.common.utils.UserUtils;
import com.nmw.pss.module.login.bean.Employee;
import com.nmw.pss.module.login.bean.EmployeeRole;
import com.nmw.pss.module.login.dao.EmployeeRoleDao;
import com.nmw.pss.module.system.bean.Menu;
import com.nmw.pss.module.system.bean.Role;
import com.nmw.pss.module.system.bean.RoleMenu;
import com.nmw.pss.module.system.dao.RoleDao;
import com.nmw.pss.module.system.dao.RoleMenuDao;
import com.nmw.pss.module.system.service.RoleService;

@Service
public class RoleServiceImpl extends BaseServiceImpl<Role, RoleDao> implements RoleService {

	@Autowired
	private RoleDao roleDao;
	@Autowired
	private RoleMenuDao roleMenuDao;
	@Autowired
	private EmployeeRoleDao employeeRoleDao;
	
	@Transactional
	@Override
	public void save(Role t) {
		//保存角色
		Date date=new Date();
		Employee current=UserUtils.getCurrentUser();
		t.setUpdateTime(date);
		t.setUpdateUserId(current.getId());
		if(StringUtils.isBlank(t.getId())){
			t.setId(UUID.randomUUID().toString().replace("-", ""));
			t.setCreateTime(date);
			t.setCreateUserId(current.getId());
			roleDao.insert(t);
		}else{
			roleDao.updateById(t);
		}
		//删除角色与菜单的关联
		RoleMenu query=new RoleMenu();
		query.setRoleId(t.getId());
		roleMenuDao.deleteByModel(query);
		//重新保存角色与菜单的关联
		for (Menu menu : t.getMenus()) {
			query.setUpdateTime(date);
			query.setUpdateUserId(current.getId());
			query.setId(UUID.randomUUID().toString().replace("-", ""));
			query.setCreateTime(date);
			query.setCreateUserId(current.getId());
			query.setMenuId(menu.getId());
			roleMenuDao.insert(query);
		}
	}

	@Transactional
	@Override
	public void removeById(String id) {
		//删除角色的用户关联
		EmployeeRole employeeRole=new EmployeeRole();
		employeeRole.setRoleId(id);
		employeeRoleDao.deleteByModel(employeeRole);
		//删除角色的菜单关联
		RoleMenu roleMenu=new RoleMenu();
		roleMenu.setRoleId(id);
		roleMenuDao.deleteByModel(roleMenu);
		//删除角色
		roleDao.deleteById(id);
	}

	@Override
	public List<Role> findByCreateUser(String createUserId) {
		Role query=new Role();
		query.setCreateUserId(createUserId);
		return roleDao.selectByModel(query);
	}

	@Override
	public List<Role> findByCE(String employeeId) {
		List<Role> list=new ArrayList<Role>();
		EmployeeRole query=new EmployeeRole();
		query.setEmployeeId(employeeId);
		List<EmployeeRole> employeeRoles=employeeRoleDao.selectByModel(query);
		for (EmployeeRole employeeRole : employeeRoles) {
			list.add(roleDao.selectById(employeeRole.getRoleId()));
		}
		return list;
	}

}
