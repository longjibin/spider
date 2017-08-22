package com.nmw.pss.module.system.service.impl;

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
	public Menu findMenuTreeByCurrentEmployee() {
		//查询根节点
		Menu rootMenu=menuDao.selectById("1");
		//递归生成菜单数
		rootMenu=findTree(rootMenu);
		return rootMenu;
	}
	
	@Override
	public Menu findTree(Menu menu) {
		List<Menu> childrens=findChildrenByCurrentEmployee(menu);
		if(childrens.size()>0){
			//设置当前节点的子节点
			menu.setChildren(childrens);
			for (Menu child : childrens) {
				findTree(child);
			}
		}
		return menu;
	}

	@Override
	public Menu findTreeTable(Menu t) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<Menu> findChildren(Menu menu) {
		Menu query=new Menu();
		query.setParentId(menu.getId());
		return menuDao.selectByModel(query);
	}


	@Override
	public List<Menu> findChildrenByCurrentEmployee(Menu menu) {
		//获取当前登录用户
		Subject subject = SecurityUtils.getSubject();
		Employee employee=(Employee) subject.getSession().getAttribute(ShiroConstant.LOGIN_USER);
		//设置当前登录用户
		menu.setEmployee(employee);
		//查询子菜单
		return menuDao.selectChildrenByModel(menu);
	}

}
