package com.nmw.pss.module.system.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nmw.pss.common.base.BaseServiceImpl;
import com.nmw.pss.common.base.TreeService;
import com.nmw.pss.common.constant.ShiroConstant;
import com.nmw.pss.module.login.bean.Employee;
import com.nmw.pss.module.system.bean.Menu;
import com.nmw.pss.module.system.dao.MenuDao;
import com.nmw.pss.module.system.service.MenuService;

@Service
public class MenuServiceImpl extends BaseServiceImpl<Menu, MenuDao> implements MenuService, TreeService<Menu> {

	@Autowired
	private MenuDao menuDao;
	
	@Override
	public Menu findMenuTreeByCE() {
		//查询根节点
		Menu rootMenu=menuDao.selectById("1");
		//递归生成菜单数
		rootMenu=findTreeByCurrentEmployee(rootMenu);
		return rootMenu;
	}
	
	private Menu findTreeByCurrentEmployee(Menu menu) {
		//获取当前登录用户
		Subject subject = SecurityUtils.getSubject();
		Employee employee=(Employee) subject.getSession().getAttribute(ShiroConstant.LOGIN_USER);
		//设置当前登录用户
		Menu query=new Menu();
		query.setEmployee(employee);
		query.setId(menu.getId());
		query.setVisible(Menu.VISIBLE);//菜单可见
		List<Menu> childrens=menuDao.selectByCurrentEmployee(query);
		if(childrens.size()>0){
			//设置当前节点的子节点
			menu.setChildren(childrens);
			for (Menu child : childrens) {
				findTreeByCurrentEmployee(child);
			}
		}
		return menu;
	}
	
	@Override
	public List<Menu> findTreeTable() {
		List<Menu> menus=new ArrayList<Menu>();
		//查询根节点
		Menu rootMenu=menuDao.selectById("1");
		findTree(rootMenu, menus);
		return menus;
	}
	
	/**
	 * 
	 * @param menu
	 * @param list
	 */
	private void findTree(Menu menu,  List<Menu> list) {
		Menu query=new Menu();
		query.setpId(menu.getId());
		List<Menu> childrens=menuDao.selectByModel(query);
		if(childrens.size()>0){
			for (Menu child : childrens) {
				list.add(child);
				findTree(child, list);
			}
		}
	}

	@Override
	public List<Menu> findMenuTreeTableByCE() {
		List<Menu> list=new ArrayList<Menu>();
		//查询根节点
		Menu rootMenu=menuDao.selectById("1");
		findTreeTableByCE(rootMenu, list);
		return list;
	}
	
	private void findTreeTableByCE(Menu menu, List<Menu> list) {
		//获取当前登录用户
		Subject subject = SecurityUtils.getSubject();
		Employee employee=(Employee) subject.getSession().getAttribute(ShiroConstant.LOGIN_USER);
		//设置当前登录用户
		Menu query=new Menu();
		query.setEmployee(employee);
		query.setId(menu.getId());
		List<Menu> childrens=menuDao.selectByCurrentEmployee(query);
		if(childrens.size()>0){
			for (Menu child : childrens) {
				list.add(child);
				findTreeTableByCE(child, list);
			}
		}
	}

}
