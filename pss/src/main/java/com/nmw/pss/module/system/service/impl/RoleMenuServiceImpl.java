package com.nmw.pss.module.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nmw.pss.common.base.BaseServiceImpl;
import com.nmw.pss.module.system.bean.RoleMenu;
import com.nmw.pss.module.system.dao.RoleMenuDao;
import com.nmw.pss.module.system.service.RoleMenuService;

@Service
public class RoleMenuServiceImpl extends BaseServiceImpl<RoleMenu, RoleMenuDao> implements RoleMenuService {
	@Autowired
	private RoleMenuDao roleMenuDao;
	
	@Override
	public List<RoleMenu> findByRoleId(String roleId) {
		RoleMenu query=new RoleMenu();
		query.setRoleId(roleId);
		return roleMenuDao.selectByModel(query);
	}

}
