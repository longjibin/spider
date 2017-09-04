package com.nmw.pss.module.system.service;

import java.util.List;

import com.nmw.pss.common.base.BaseService;
import com.nmw.pss.module.system.bean.RoleMenu;

public interface RoleMenuService extends BaseService<RoleMenu> {

	public List<RoleMenu> findByRoleId(String roleId);

}
