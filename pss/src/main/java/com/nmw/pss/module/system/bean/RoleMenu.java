package com.nmw.pss.module.system.bean;

import com.nmw.pss.common.base.BaseEntity;

/**
 * 角色菜单关系实体
 * 
 * @author Administrator
 *
 */
public class RoleMenu extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// 角色id
	private String roleId;

	// 菜单id
	private String menuId;

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	@Override
	public String toString() {
		return "RoleMenu [roleId=" + roleId + ", menuId=" + menuId + ", id=" + id + ", createTime=" + createTime
				+ ", updateTime=" + updateTime + ", createUserId=" + createUserId + ", updateUserId=" + updateUserId
				+ ", remark=" + remark + ", delSign=" + delSign + "]";
	}

}
