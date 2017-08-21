package com.nmw.pss.module.system.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nmw.pss.common.base.BaseServiceImpl;
import com.nmw.pss.common.config.Global;
import com.nmw.pss.module.login.bean.Employee;
import com.nmw.pss.module.login.bean.EmployeeRole;
import com.nmw.pss.module.login.dao.EmployeeRoleDao;
import com.nmw.pss.module.system.bean.Menu;
import com.nmw.pss.module.system.bean.RoleMenu;
import com.nmw.pss.module.system.dao.MenuDao;
import com.nmw.pss.module.system.dao.RoleMenuDao;
import com.nmw.pss.module.system.service.MenuService;

@Service
public class MenuServiceImpl extends BaseServiceImpl<Menu, MenuDao> implements MenuService {

	@Autowired
	private MenuDao menuDao;
	@Autowired
	private EmployeeRoleDao employeeRoleDao;
	@Autowired
	private RoleMenuDao roleMenuDao;
	
	@Override
	public List<Menu> findCurrentEmployeeMenus(Employee employee) {
		List<Menu> menus=new ArrayList<Menu>();
		
		if(employee.getLoginName().equals(Global.getAdminAccount())){
			/**
			 * 当前登录的是系统管理员
			 */
			menus=menuDao.selectAll();
		}else{
			/**
			 * 当前用户不是管理员
			 */
			Menu menu=null;
			//查询当前用户的角色集合
			EmployeeRole employeeRoleQuery=new EmployeeRole();
			employeeRoleQuery.setEmployeeId(employee.getId());
			List<EmployeeRole> employeeRoles=employeeRoleDao.selectByModel(employeeRoleQuery);
			
			for (EmployeeRole employeeRole : employeeRoles) {
				
				//查询角色关联的菜单集合
				RoleMenu roleMenuQuery=new RoleMenu();
				roleMenuQuery.setRoleId(employeeRole.getRoleId());
				List<RoleMenu> roleMenus=roleMenuDao.selectByModel(roleMenuQuery);
				
				for (RoleMenu roleMenu : roleMenus) {
					menu=menuDao.selectById(roleMenu.getMenuId());
					menus.add(menu);
				}
			}
		}
		return menus;
	}

}
