package com.nmw.pss.module.system.service;

import java.util.List;

import com.nmw.pss.common.base.BaseService;
import com.nmw.pss.module.system.bean.Role;

public interface RoleService extends BaseService<Role>{

	/**
	 * 查询指定用户创建的角色集合
	 * @param createUserId
	 * @return
	 */
	public List<Role> findByCreateUser(String createUserId);

	/**
	 * 查询当前用户的角色集合
	 * @param id
	 * @return
	 */
	public List<Role> findByCE(String employeeId);

}
