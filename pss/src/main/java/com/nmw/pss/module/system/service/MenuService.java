package com.nmw.pss.module.system.service;

import java.util.List;

import com.nmw.pss.common.base.BaseService;
import com.nmw.pss.module.login.bean.Employee;
import com.nmw.pss.module.system.bean.Menu;

public interface MenuService extends BaseService<Menu> {
	
	/**
	 * 查询当前登录用户的菜单集合
	 * @return
	 */
	public List<Menu> findCurrentEmployeeMenus(Employee employee);
}
