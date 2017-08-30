package com.nmw.pss.module.system.service;

import java.util.List;

import com.nmw.pss.common.base.BaseService;
import com.nmw.pss.common.base.TreeService;
import com.nmw.pss.module.system.bean.Menu;

public interface MenuService extends BaseService<Menu>, TreeService<Menu> {
	
	/**
	 * 查询当前登录用户的菜单集合，按照树结构组织数据
	 * @return 菜单树的根节点
	 */
	public Menu findMenuTreeByCE();
	
	/**
	 * 查询当前登录用户的菜单集合，按照树表组织数据
	 * @return
	 */
	public List<Menu> findMenuTreeTableByCE();
	
}
