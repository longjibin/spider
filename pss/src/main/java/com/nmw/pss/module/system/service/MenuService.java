package com.nmw.pss.module.system.service;

import java.util.List;

import com.nmw.pss.common.base.BaseService;
import com.nmw.pss.module.system.bean.Menu;

public interface MenuService extends BaseService<Menu> {
	
	/**
	 * 查询当前登录用户的菜单集合
	 * @return 菜单树的根节点
	 */
	public Menu findMenuTreeByCurrentEmployee();
	
	/**
	 * 查询当前用户指定的子菜单集合
	 * @param menu
	 * @return
	 */
	public List<Menu> findChildrenByCurrentEmployee(Menu menu);

}
