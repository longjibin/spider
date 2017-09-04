package com.nmw.pss.module.system.bean;

import java.util.ArrayList;
import java.util.List;

import com.nmw.pss.common.base.BaseEntity;

/**
 * 角色实体
 * 
 * @author Administrator
 *
 */
public class Role extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// 角色名
	private String name;
	
	/**
	 * 辅助属性
	 * @return
	 */
	List<Menu> menus=new ArrayList<Menu>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Menu> getMenus() {
		return menus;
	}

	public void setMenus(List<Menu> menus) {
		this.menus = menus;
	}

	@Override
	public String toString() {
		return "Role [name=" + name + ", id=" + id + ", createTime=" + createTime + ", updateTime=" + updateTime
				+ ", createUserId=" + createUserId + ", updateUserId=" + updateUserId + ", remark=" + remark
				+ ", delSign=" + delSign + "]";
	}

}
